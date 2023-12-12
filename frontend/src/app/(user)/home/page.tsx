"use client"
import { useEffect } from "react";

export default function HomePage() {

    useEffect(() => {}, []);

    return (
        <div className="container relative hidden h-max p-10 flex-col items-center justify-center md:grid lg:max-w-none lg:grid-cols-2 lg:px-0">
            <div>Home</div>
        </div>
    );
}
