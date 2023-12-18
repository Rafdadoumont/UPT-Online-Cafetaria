"use client"
import { useEffect, useState } from "react";
import { useRouter } from "next/navigation";
import { Button } from "@/components/ui/button";
import { ReservationTable } from "@/app/(user)/reservation/all/components/reservation-table";
import Cookies from "js-cookie";

export default function HomePage() {
    const router = useRouter();

    const reservationRedirect = () => {
        router.push('/reservation/new');
    };

    return (
        <div className="container p-10 flex flex-col items-center justify-center">
            <div className="mb-4">
                <h2 className="text-2xl font-semibold mb-2">Welcome to the Platform!</h2>
                <p className="text-gray-600 max-w-2xl">
                    Reserve Your Favorite Meal Hassle-Free

                    Are you ready to savor delightful meals hassle-free? Our platform lets you reserve your favorite dishes at the university cafeteria with ease. Discover a variety of options and secure your meal reservation at your convenience. Say goodbye to long queues and ensure your dining experience fits your schedule.
                </p>
            </div>
            <Button onClick={reservationRedirect} className="mt-4 text-white font-bold py-2 px-4 rounded">
                New reservation
            </Button>
        </div>
    );
}
