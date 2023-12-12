"use client"
import { useEffect, useState } from "react";
import Cookies from 'js-cookie';
import { Product as ProductType } from "@/types";
import {ProductTable} from "@/app/(admin)/products/components/product-table";
import {ProductForm} from "@/app/(admin)/products/components/product-form";
import {Button} from "@/components/ui/button";

export default function ProductPage() {
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
                console.error("Error fetching products:", error);
            }
        }
        init();
    }, []);

    return (
        <div className="container relative hidden h-max p-10 flex-col items-center justify-center md:grid lg:max-w-none lg:grid-cols-2 lg:px-0">
            <ProductTable products={products} />
            <ProductForm />
        </div>
    );
}
