"use client"
import React, {useEffect} from "react";
import {
    NavigationMenu,
    NavigationMenuLink,
    navigationMenuTriggerStyle,
} from "@/components/ui/navigation-menu"
import Link from "next/link";
import Image from "next/image";
import {Button} from "@/components/ui/button";
import Cookies from "js-cookie";
import {useRouter} from "next/navigation";

export function NavigationBar() {
    const Router = useRouter();

    const logout = () => {
        Cookies.remove("access-token")
        Router.push("/login")
    }

    return (
        <NavigationMenu className="justify-between p-2 text-white">
            <Link href="/home" legacyBehavior passHref>
                <div className="flex gap-2 items-center">
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
            </Link>
            <div className="flex items-center">
                <Link href="/home" legacyBehavior passHref className="bg-primary">
                    <NavigationMenuLink className={navigationMenuTriggerStyle()}>
                        Home
                    </NavigationMenuLink>
                </Link>

                <Link href="/dashboard" legacyBehavior passHref>
                    <NavigationMenuLink className={navigationMenuTriggerStyle()}>
                        Dashboard
                    </NavigationMenuLink>
                </Link>

                <Link href="/products" legacyBehavior passHref>
                    <NavigationMenuLink className={navigationMenuTriggerStyle()}>
                        Products
                    </NavigationMenuLink>
                </Link>
            </div>
            <div className="flex items-center">
                <Button onClick={logout} className="bg-red-600 text-white font-bold py-2 px-4 rounded">
                    Log Out
                </Button>
            </div>
        </NavigationMenu>
    )
}
