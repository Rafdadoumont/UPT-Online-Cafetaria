import type {NextRequest} from 'next/server'
import {NextResponse} from 'next/server'
import {RequestCookie} from "next/dist/compiled/@edge-runtime/cookies";

const anonymousPrefixes: string[] = ['/login', '/register']
const userPrefixes: string[] = ['/product']
const employeePrefixes: string[] = []
const adminPrefixes: string[] = []

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

        if (res.ok) {
            const data = await res.json();
            const role = data.role;

            // Filter private routes based on role
            switch (role) {
                case 'USER':
                    if (userPrefixes.some(prefix => pathname.startsWith(prefix))) {
                        return NextResponse.next();
                    }
                    break;
                case 'EMPLOYEE':
                    if (userPrefixes.some(prefix => pathname.startsWith(prefix)) ||
                        employeePrefixes.some(prefix => pathname.startsWith(prefix))) {
                        return NextResponse.next();
                    }
                    break;
                case 'ADMIN':
                    return NextResponse.next();
                default:
                    return NextResponse.redirect(new URL('/login', request.url));
            }
        }
    }
}

async function validateToken(token: string): Promise<Response> {
    return await fetch(process.env.BACKEND_URL + '/auth/validate', {
        method: 'GET',
        headers: {
            'Authorization': 'Bearer ' + token,
        },
    });
}
