"use client"
import { Button } from '@/components/ui/button';
import { useRouter } from 'next/navigation';
import Image from "next/image";

export default function Custom404() {
    const router = useRouter();

    const goToHome = () => {
        router.push('/home');
    };

    return (
        <div className="container flex flex-col items-center justify-center h-screen">
            <div className="absolute top-4 left-4 z-50">
                <a className="flex gap-2 items-center">
                    <Image
                        src="/portucalense.png"
                        alt="logo"
                        width={40}
                        height={40}
                        className="z-10"
                    />
                    <div className="text-lg font-medium">
                        Online Cafeteria
                    </div>
                    </a>
            </div>
            <div className="text-center">
                <h1 className="text-4xl font-bold mb-4">404 - Page Not Found</h1>
                <h2 className="text-lg">Sorry, this page does not exist.</h2>
                <Button onClick={goToHome} className="mt-4 text-white font-bold py-2 px-4 rounded">
                    Go Home
                </Button>
            </div>
        </div>
    );
}
