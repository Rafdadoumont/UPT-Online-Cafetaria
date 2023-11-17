"use client"
import React from "react";
import {
    NavigationMenu,
    NavigationMenuLink,
    navigationMenuTriggerStyle,
} from "@/components/ui/navigation-menu"
import Link from "next/link";
import Image from "next/image";

export function NavigationBar() {
    return (
        <NavigationMenu className="justify-between p-2 text-white">
            <Link href="/" legacyBehavior passHref>
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
            <div>
                <Link href="/" legacyBehavior passHref className="bg-primary">
                    <NavigationMenuLink className={navigationMenuTriggerStyle()}>
                        Home
                    </NavigationMenuLink>
                </Link>

                <Link href="/product" legacyBehavior passHref>
                    <NavigationMenuLink className={navigationMenuTriggerStyle()}>
                        Product
                    </NavigationMenuLink>
                </Link>
            </div>
            <div>
                <Link href="/me" legacyBehavior passHref>
                    <NavigationMenuLink className={navigationMenuTriggerStyle()}>
                        My account
                    </NavigationMenuLink>
                </Link>
            </div>
        </NavigationMenu>
    )
}
