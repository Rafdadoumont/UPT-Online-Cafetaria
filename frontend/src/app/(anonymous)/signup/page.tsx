"use client"
import Image from "next/image"

import Facade from "root/public/facade.png"
import { cn } from "@/lib/utils"
import {Button} from "@/components/ui/button"
import {useRouter} from "next/navigation";
import {UserSignUpForm} from "@/app/(anonymous)/signup/components/user-sign-up-form";

export default function SignupPage() {
    const router = useRouter();

    return (
        <div className="container relative hidden h-screen flex-col items-center justify-center md:grid lg:max-w-none lg:grid-cols-2 lg:px-0">
            <Button
                onClick={() => router.push('/login')}
                variant={"ghost"}
                className={cn(
                    "absolute right-4 top-4 md:right-8 md:top-8"
                )}
            >
                Log in
            </Button>
            <div className="relative hidden h-full flex-col bg-muted text-white dark:border-r lg:flex">
                <div className="relative mt-auto h-full">
                    <Image
                        src={Facade}
                        layout="fill"
                        style={{objectFit:"cover"}}
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
                        <h1 className="text-2xl font-semibold tracking-tight p-2">
                            Sign up
                        </h1>
                        <p className="text-sm text-muted-foreground">
                            Create an account for the UPT Online Cafeteria
                        </p>
                    </div>
                    <UserSignUpForm />
                    <p className="px-8 text-center text-sm text-muted-foreground">
                        By clicking continue, you agree to our terms and conditions.
                    </p>
                </div>
            </div>
        </div>
    )
}
