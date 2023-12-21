"use client"
import { useEffect, useState } from "react";
import Cookies from 'js-cookie';
import {Product as ProductType, Reservation} from "@/types";
import {ProductTable} from "@/app/(admin)/product/components/product-table";
import {ProductForm} from "@/app/(admin)/product/components/product-form";
import {Button} from "@/components/ui/button";
import {ReservationTable} from "@/app/(user)/reservation/all/components/reservation-table";

export default function ReservationPage() {
    const [unfulfilledReservations, setUnfulfilledReservations] = useState([]);
    const [fulfilledReservations, setFulfilledReservations] = useState([]);

    useEffect(() => {
        async function fetchData() {
            try {
                const accessToken = Cookies.get('access-token');
                const userId = Cookies.get('user-id');

                const unfulfilledRes = await fetch(`http://localhost:8080/api/reservation/user/${userId}/unfulfilled`, {
                    headers: {
                        'Authorization': `Bearer ${accessToken}`,
                    },
                });
                const unfulfilledData = await unfulfilledRes.json();
                console.log(unfulfilledData)
                setUnfulfilledReservations(unfulfilledData);

                const fulfilledRes = await fetch(`http://localhost:8080/api/reservation/user/${userId}/fulfilled`, {
                    headers: {
                        'Authorization': `Bearer ${accessToken}`,
                    },
                });
                const fulfilledData = await fulfilledRes.json();
                setFulfilledReservations(fulfilledData);
            } catch (error) {
                console.error("Error fetching reservations:", error);
            }
        }

        fetchData();
    }, []);


    return (
        <div className="container mx-auto px-4 py-8 lg:py-16">
            <div className="flex flex-col">
                <div>
                    <h1 className="text-3xl font-bold mb-1">My reservations</h1>
                    <p className="text-sm text-muted-foreground mb-6">An overview of active reservations and history.</p>
                    <ReservationTable reservations={unfulfilledReservations} />
                </div>
                {/*<div>*/}
                {/*    <h2 className="text-2xl font-semibold mb-4">Fulfilled Reservations</h2>*/}
                {/*    <ReservationTable reservations={fulfilledReservations} />*/}
                {/*</div>*/}
            </div>
        </div>
    );
}
