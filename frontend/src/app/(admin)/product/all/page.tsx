"use client"
import { useEffect, useState } from "react";
import Cookies from 'js-cookie';
import { Product as ProductType } from "@/types";
import {ProductTable} from "@/app/(admin)/product/components/product-table";
import {ProductForm} from "@/app/(admin)/product/components/product-form";
import {Button} from "@/components/ui/button";

export default function AllProductPage() {
    const [products, setProducts] = useState<ProductType[]>([]);

    useEffect(() => {
        async function init() {
            try {
                const accessToken: String | undefined = Cookies.get('access-token');
                const res = await fetch('http://localhost:8080/api/product/all', {
                    headers: {
                        'Authorization': `Bearer ${accessToken}`,
                    },
                });
                const data = await res.json();
                setProducts(data);
            } catch (error) {
                console.error("Error fetching product:", error);
            }
        }
        init();
    }, []);

    return (
        <div className="container mx-auto p-10  flex flex-col items-center  min-h-screen">
            <h1 className="text-3xl font-bold mb-6">All Product</h1>
            <div className="max-w-2xl w-full flex justify-center">
                <ProductTable products={products} />
            </div>
        </div>
    );
}
