import { Metadata } from "next"
import Image from "next/image"
import Link from "next/link"

import { cn } from "@/lib/utils"
import { buttonVariants } from "@/components/ui/button"
import { UserLoginForm } from "@/app/login/components/user-login-form";

export const metadata: Metadata = {
    title: "Log in",
}

export default function LoginPage() {
    return (
        <div className="container relative hidden h-screen flex-col items-center justify-center md:grid lg:max-w-none lg:grid-cols-2 lg:px-0">
            <Link
                href="/signup"
                className={cn(
                    buttonVariants({ variant: "ghost" }),
                    "absolute right-4 top-4 md:right-8 md:top-8"
                )}
            >
                Sign up
            </Link>
            <div className="relative hidden h-full flex-col bg-muted text-white dark:border-r lg:flex">
                <div className="relative mt-auto h-full">
                    <Image
                        src="/facade.png"
                        layout="fill"
                        objectFit="cover"
                        alt="Authentication"
                        className="object-cover relative"
                    />
                    <div className="absolute top-10 left-10 flex gap-2 items-center">
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
                    </div>
                </div>
            </div>
            <div className="lg:p-8">
                <div className="mx-auto flex w-full flex-col justify-center space-y-6 sm:w-[350px]">
                    <div className="flex flex-col space-y-2 text-center">
                        <h1 className="text-2xl font-semibold tracking-tight">
                            Log in to UPT
                        </h1>
                        <p className="text-sm text-muted-foreground">
                            Enter your email and password below to log in to your account
                        </p>
                    </div>
                    <UserLoginForm />
                    <p className="px-8 text-center text-sm text-muted-foreground">
                        By clicking continue, you agree to our terms and conditions.
                    </p>
                </div>
            </div>
        </div>
    )
}
