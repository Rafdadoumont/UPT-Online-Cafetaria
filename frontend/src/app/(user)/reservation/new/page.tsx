"use client"
import { ReservationForm } from "@/app/(user)/reservation/new/components/reservation-form";
import Image from "next/image";
import ChefPreparing from "root/public/chef-preparing-recipe.jpg"

export default function NewReservationPage() {
    return (
        <div className="container mx-auto p-10 flex flex-col items-center min-h-screen">
            <div className="w-full flex justify-between">
                <div className="w-3/5">
                    <h1 className="text-3xl font-bold mb-1">New Reservation</h1>
                    <p className="text-sm text-muted-foreground mb-6">Reserve your food in advance.</p>
                    <ReservationForm />
                </div>
                <div className="w-2/5 flex justify-center items-center">
                    <Image src={ChefPreparing} alt="Chef preparing food" />
                </div>
            </div>
        </div>
    );
}
