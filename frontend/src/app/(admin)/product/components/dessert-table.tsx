import React, {useEffect, useState} from "react";
import {Table, TableBody, TableCaption, TableCell, TableHead, TableHeader, TableRow} from "@/components/ui/table";
import {Meal, Product} from "@/types";
import Cookies from "js-cookie";
import {Button} from "@/components/ui/button";
import {de} from "date-fns/locale";

interface ProductTableProps {
    products: Product[]
}

export function DessertTable() {
    const [desserts, setDesserts] = useState<Product[]>([]);
    const [rerender, setRerender] = useState<boolean>()

    useEffect(() => {
        async function fetchProducts() {
            try {
                const accessToken = Cookies.get("access-token");
                const res = await fetch('http://localhost:8080/api/dessert/all', {
                    headers: {
                        'Authorization': `Bearer ${accessToken}`,
                    },
                });
                const data = await res.json();
                setDesserts(data);
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
            console.log(error)
        }
    }

    async function deactivate(dessertId: number) {
        try {
            const accessToken = Cookies.get("access-token");
            const response: Response = await fetch('http://localhost:8080/api/product/deactivate/' + dessertId, {
                method: "PUT",
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

    async function remove(dessertId: number) {
        try {
            const accessToken = Cookies.get("access-token");
            const response: Response = await fetch('http://localhost:8080/api/product/delete/' + dessertId, {
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
                {desserts.map((dessert) => (
                    <TableRow>
                        <TableCell className="font-medium">{dessert.productId}</TableCell>
                        <TableCell>{dessert.name}</TableCell>
                        <TableCell>{dessert.price}</TableCell>
                        <TableCell>{dessert.description}</TableCell>
                        <TableCell>{dessert.active ? "Yes" : "No"}</TableCell>
                        <TableCell>
                            {dessert.active ?
                                <Button onClick={() => deactivate(dessert.productId)}>Deactivate</Button> :
                                <Button onClick={() => activate(dessert.productId)}>Activate</Button>
                            }
                        </TableCell>
                        <TableCell>
                            <Button onClick={() => remove(dessert.productId)} className="bg-red-600 hover:bg-red-500 text-white font-bold py-2 px-4 rounded">
                                Delete
                            </Button>
                        </TableCell>
                    </TableRow>
                ))}
            </TableBody>
        </Table>
    )
}
