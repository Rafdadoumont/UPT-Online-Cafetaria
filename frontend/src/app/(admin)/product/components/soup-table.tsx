"use client"
import React, {useEffect, useState} from "react";
import {Table, TableBody, TableCaption, TableCell, TableHead, TableHeader, TableRow} from "@/components/ui/table";
import {Product as ProductType, Product} from "@/types";
import {Button} from "@/components/ui/button";
import Cookies from "js-cookie";

interface ProductTableProps {
    products: Product[]
}

export function SoupTable() {
    const [soups, setSoups] = useState<ProductType[]>([]);
    const [rerender, setRerender] = useState<boolean>()

    useEffect(() => {
        async function fetchProducts() {
            try {
                const accessToken = Cookies.get("access-token");
                const res = await fetch('http://localhost:8080/api/soup/all', {
                    headers: {
                        'Authorization': `Bearer ${accessToken}`,
                    },
                });
                const data = await res.json();
                setSoups(data);
            } catch (error) {
                console.error("Error refetching products:", error);
            }
        }

        fetchProducts();
    }, [rerender]);


    async function activate(soupId: number) {
        try {
            const accessToken = Cookies.get("access-token");
            const response: Response = await fetch('http://localhost:8080/api/product/activate/' + soupId, {
                method: "PUT",
                headers: {
                    'Authorization': `Bearer ${accessToken}`,
                },
            });
            console.log(response)
            setRerender(!rerender)
        } catch (error) {

        }
    }

    async function deactivate(soupId: number) {
        try {
            const accessToken = Cookies.get("access-token");
            const response: Response = await fetch('http://localhost:8080/api/product/deactivate/' + soupId, {
                method: "PUT",
                headers: {
                    'Authorization': `Bearer ${accessToken}`,
                },
            });
            console.log(response)
            setRerender(!rerender)
        } catch (error) {

        }
    }

    async function remove(soupId: number) {
        try {
            const accessToken = Cookies.get("access-token");
            const response: Response = await fetch('http://localhost:8080/api/product/delete/' + soupId, {
                method: "DELETE",
                headers: {
                    'Authorization': `Bearer ${accessToken}`,
                },
            });
            console.log(response)
            setRerender(!rerender)
        } catch (error) {
            console.log(error)
        }
    }

    return (
        <Table>
            <TableHeader>
                <TableRow>
                    <TableHead>Id</TableHead>
                    <TableHead>Name</TableHead>
                    <TableHead>Price</TableHead>
                    <TableHead>Description</TableHead>
                    <TableHead>In menu</TableHead>
                    <TableHead></TableHead>
                    <TableHead></TableHead>
                </TableRow>
            </TableHeader>
            <TableBody>
                {soups.map((soup) => (
                    <TableRow>
                        <TableCell className="font-medium">{soup.productId}</TableCell>
                        <TableCell>{soup.name}</TableCell>
                        <TableCell>{soup.price}</TableCell>
                        <TableCell>{soup.description}</TableCell>
                        <TableCell>{soup.active ? "Yes" : "No"}</TableCell>
                        <TableCell>
                            {soup.active ?
                                <Button onClick={() => deactivate(soup.productId)}>Deactivate</Button> :
                                <Button onClick={() => activate(soup.productId)}>Activate</Button>
                            }
                        </TableCell>
                        <TableCell>
                            <Button onClick={() => remove(soup.productId)} className="bg-red-600 hover:bg-red-500 text-white font-bold py-2 px-4 rounded">
                                Delete
                            </Button>
                        </TableCell>
                    </TableRow>
                ))}
            </TableBody>
        </Table>
    )
}
