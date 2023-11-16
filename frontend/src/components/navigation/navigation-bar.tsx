"use client"
import React from "react";
import {
    NavigationMenu,
    NavigationMenuLink,
    navigationMenuTriggerStyle,
} from "@/components/ui/navigation-menu"
import Link from "next/link";

export function NavigationBar() {
    return (
        <NavigationMenu>
            <Link href="/" legacyBehavior passHref>
                <NavigationMenuLink className={navigationMenuTriggerStyle()}>
                    Home
                </NavigationMenuLink>
            </Link>
            <Link href="/product" legacyBehavior passHref>
                <NavigationMenuLink className={navigationMenuTriggerStyle()}>
                    Product
                </NavigationMenuLink>
            </Link>
        </NavigationMenu>
    )
}
