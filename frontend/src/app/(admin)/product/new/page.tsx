"use client"
import {ProductForm} from "@/app/(admin)/product/components/product-form";
import {ReservationForm} from "@/app/(user)/reservation/new/components/reservation-form";

export default function NewProductPage() {

    return (
        <div className="container mx-auto p-10  flex flex-col items-center  min-h-screen">
            <h1 className="text-3xl font-bold mb-6">New Product</h1>
            <div className="max-w-2xl w-full flex justify-center">
                <ProductForm />
            </div>
        </div>
    );
}
