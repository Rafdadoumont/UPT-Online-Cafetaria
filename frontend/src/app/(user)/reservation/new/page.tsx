"use client"
import { useEffect } from "react";
import {ReservationForm} from "@/app/(user)/reservation/new/components/reservation-form";

export default function NewReservationPage() {

    useEffect(() => {}, []);

    return (
        <div className="container mx-auto p-10  flex flex-col items-center  min-h-screen">
            <h1 className="text-3xl font-bold mb-6">New Reservation</h1>
            <div className="max-w-2xl w-full flex justify-center">
                <ReservationForm />
            </div>
        </div>
    );
}
