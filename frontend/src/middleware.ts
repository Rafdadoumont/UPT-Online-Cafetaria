import type {NextRequest} from 'next/server'
import {NextResponse} from 'next/server'
import {RequestCookie} from "next/dist/compiled/@edge-runtime/cookies";

const anonymousPrefixes: string[] = ['/login', '/register', '/forbidden']
const userPrefixes: string[] = ['/home', '/reservation', '/user']
const employeePrefixes: string[] = ['/dashboard']
const adminPrefixes: string[] = ['/products']


// Do not apply middleware
export const config = {
    matcher: ['/((?!api|_next/static|_next/image|_next/public|assets|favicon.ico|sw.js).*)']
};

export async function middleware(request: NextRequest) {
    const {pathname} = request.nextUrl
    let accessTokenCookie: RequestCookie | undefined = request.cookies.get('access-token')
    let refreshTokenCookie: RequestCookie | undefined = request.cookies.get('refresh-token')

    // Allow authentication paths
    if (anonymousPrefixes.some((prefix: string) => pathname.startsWith(prefix))) {
        return NextResponse.next()
    }

    // If there is an access token
    if (accessTokenCookie) {
        const res: Response = await validateToken(accessTokenCookie.value);
        // Valid access token
        if (res.ok) {
            const data = await res.json();
            const role = data.role;

            // Filter private routes based on role
            switch (role) {
                case 'USER':
                    if (userPrefixes.some(prefix => pathname.startsWith(prefix))) {
                        return NextResponse.next();
                    } else {
                        return NextResponse.redirect(new URL('/forbidden', request.url));
                    }
                case 'EMPLOYEE':
                    if (userPrefixes.some(prefix => pathname.startsWith(prefix)) ||
                        employeePrefixes.some(prefix => pathname.startsWith(prefix))) {
                        return NextResponse.next();
                    } else {
                        return NextResponse.redirect(new URL('/forbidden', request.url));
                    }
                case 'ADMIN':
                    return NextResponse.next();
            }
        }
    }

    // Invalid token, try generate new
    else if (refreshTokenCookie) {
        const res: Response = await refreshToken(refreshTokenCookie.value);
        // Valid refresh token
        if (res.ok) {
            const data = await res.json();
            const accessToken = data.access_token;
            const response = NextResponse.next()
            response.cookies.set({name: 'access-token', value: accessToken, path: '/', expires: 86400});
            return response;
        }
    }

    // No tokens found, redirect to log in
    return NextResponse.redirect(new URL('/login', request.url));
}

async function validateToken(token: string): Promise<Response> {
    return await fetch(process.env.BACKEND_URL + '/auth/validate', {
        method: 'GET',
        headers: {
            'Authorization': 'Bearer ' + token,
        },
    });
}

async function refreshToken(refreshToken: string): Promise<Response> {
    const requestBody = {
        "refreshToken": refreshToken
    };

    return await fetch(process.env.BACKEND_URL + '/auth/refresh-token', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(requestBody)
    });
}
