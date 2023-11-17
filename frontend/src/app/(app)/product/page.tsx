"use client"
import { useEffect, useState } from "react";
import { Product as ProductType } from "@/types";
import {ProductTable} from "@/app/(app)/product/components/product-table";
import {ProductForm} from "@/app/(app)/product/components/product-form";

export default function ProductPage() {
    const [products, setProducts] = useState<ProductType[]>([]);

    useEffect(() => {
        async function init() {
            try {
                const res = await fetch('http://localhost:8080/api/product/all');
                const data = await res.json();
                setProducts(data);
            } catch (error) {
                console.error("Error fetching products:", error);
            }
        }
        init();
    }, []);

    return (
        <div className="container relative hidden h-screen flex-col items-center justify-center md:grid lg:max-w-none lg:grid-cols-2 lg:px-0">
            <ProductTable products={products} />
            <ProductForm />
        </div>
    );
}
