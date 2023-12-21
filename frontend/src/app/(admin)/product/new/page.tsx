"use client"
import {ProductForm} from "@/app/(admin)/product/components/product-form";
import Image from "next/image";
import TopViewFood from "root/public/top-view-food.jpg";

export default function NewProductPage() {

    return (
        <div className="container mx-auto p-10 flex flex-col items-center min-h-screen">
            <div className="w-full flex justify-between">
                <div className="w-3/5">
                    <h1 className="text-3xl font-bold mb-1">Create a new product</h1>
                    <p className="text-sm text-muted-foreground mb-6">Product will be added to database.</p>
                    <ProductForm />
                </div>
                <div className="w-2/5 flex justify-center items-center">
                    <Image src={TopViewFood} alt="Top view food" />
                </div>
            </div>
        </div>
    );
}
