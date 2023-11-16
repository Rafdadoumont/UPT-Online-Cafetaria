"use client"
import { useEffect, useState } from "react";
import { Product as ProductType } from "@/types";

export default function ProductPage() {
    const [products, setProducts] = useState<ProductType[]>([]);

    useEffect(() => {
        async function init() {
            try {
                const res = await fetch('http://localhost:8080/api/product/all');
                const data = await res.json();
                console.log(data);
                setProducts(data);
            } catch (error) {
                console.error("Error fetching products:", error);
            }
        }
        init();
    }, []);

    return (
        <div>
            <h1>Products</h1>
            <ul>
                {products.map(product => (
                    <li key={product.id}>
                        <h3>{product.name}</h3>
                        <p>Price: ${product.price}</p>
                        <p>Description: {product.description}</p>
                    </li>
                ))}
            </ul>
        </div>
    );
}
