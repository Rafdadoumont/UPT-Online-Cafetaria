"use client"
import React, {useEffect} from "react";
import {
    NavigationMenu, NavigationMenuContent, NavigationMenuItem,
    NavigationMenuLink, NavigationMenuList, NavigationMenuTrigger,
    navigationMenuTriggerStyle,
} from "@/components/ui/navigation-menu"
import Link from "next/link";
import Image from "next/image";
import {Button} from "@/components/ui/button";
import Cookies from "js-cookie";
import {useRouter} from "next/navigation";
import {cn} from "@/lib/utils";

export function NavigationBar() {
    const router = useRouter();

    const logout = () => {
        console.log("Logging out...")
        Cookies.remove("access-token");
        Cookies.remove("refresh-token");
        Cookies.remove("user-id");
        router.push("/login")
    }

    return (
        <div className="justify-between p-2 text-white flex bg-primary">
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
                <NavigationMenu>
                    <NavigationMenuList>

                        <NavigationMenuItem>
                            <Link href="/home" legacyBehavior passHref>
                                <NavigationMenuLink className={navigationMenuTriggerStyle()}>
                                    Home
                                </NavigationMenuLink>
                            </Link>
                        </NavigationMenuItem>

                        <NavigationMenuItem>
                            <NavigationMenuTrigger>Reservation</NavigationMenuTrigger>
                            <NavigationMenuContent>
                                <ul className="grid gap-3 p-6 md:w-[400px] lg:w-[500px] lg:grid-cols-[.75fr_1fr]">
                                    <ListItem href="/reservation/all" title="All Reservations">
                                        View all reservations.
                                    </ListItem>
                                    <ListItem href="/reservation/new" title="New Reservation">
                                        Create a new reservation.
                                    </ListItem>
                                </ul>
                            </NavigationMenuContent>
                        </NavigationMenuItem>

                        <NavigationMenuItem>
                            <Link href="/dashboard/all" legacyBehavior passHref>
                                <NavigationMenuLink className={navigationMenuTriggerStyle()}>
                                    Dashboard
                                </NavigationMenuLink>
                            </Link>
                        </NavigationMenuItem>

                        <NavigationMenuItem>
                            <NavigationMenuTrigger>Product</NavigationMenuTrigger>
                            <NavigationMenuContent>
                                <ul className="grid gap-3 p-6 md:w-[400px] lg:w-[500px] lg:grid-cols-[.75fr_1fr]">
                                    <ListItem href="/product/all" title="All Products">
                                        View all products.
                                    </ListItem>
                                    <ListItem href="/product/new" title="New Product">
                                        Create a new product.
                                    </ListItem>
                                </ul>
                            </NavigationMenuContent>
                        </NavigationMenuItem>
                    </NavigationMenuList>
                </NavigationMenu>
            </div>
            <div className="flex items-center">
                <Button onClick={logout} className="bg-red-600 hover:bg-red-500 text-white font-bold py-2 px-4 rounded">
                    Log Out
                </Button>
            </div>
        </div>
    )
}

const ListItem = React.forwardRef<
    React.ElementRef<"a">,
    React.ComponentPropsWithoutRef<"a">
>(({ className, title, children, ...props }, ref) => {
    return (
        <li>
            <NavigationMenuLink asChild>
                <a
                    ref={ref}
                    className={cn(
                        "block select-none space-y-1 rounded-md p-3 leading-none no-underline outline-none transition-colors hover:bg-accent hover:text-accent-foreground focus:bg-accent focus:text-accent-foreground",
                        className
                    )}
                    {...props}
                >
                    <div className="text-sm font-medium leading-none">{title}</div>
                    <p className="line-clamp-2 text-sm leading-snug text-muted-foreground">
                        {children}
                    </p>
                </a>
            </NavigationMenuLink>
        </li>
    )
})
ListItem.displayName = "ListItem"
