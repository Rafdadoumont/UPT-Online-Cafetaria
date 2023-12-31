"use client"
import React, {useEffect, useState} from "react";
import {Table, TableBody, TableCell, TableHead, TableHeader, TableRow} from "@/components/ui/table";
import {Meal} from "@/types";
import Cookies from "js-cookie";
import {Button} from "@/components/ui/button";

interface MealTableProps {
    meals: Meal[]
}

export function MealTable() {
    const [meals, setMeals] = useState<Meal[]>([]);
    const [rerender, setRerender] = useState<boolean>()

    useEffect(() => {
        async function fetchProducts() {
            try {
                const accessToken = Cookies.get("access-token");
                const res = await fetch('http://localhost:8080/api/meal/all', {
                    headers: {
                        'Authorization': `Bearer ${accessToken}`,
                    },
                });
                const data = await res.json();
                setMeals(data);
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

    async function deactivate(mealId: number) {
        try {
            const accessToken = Cookies.get("access-token");
            const response: Response = await fetch('http://localhost:8080/api/product/deactivate/' + mealId, {
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

    async function remove(mealId: number) {
        try {
            const accessToken = Cookies.get("access-token");
            const response: Response = await fetch('http://localhost:8080/api/product/delete/' + mealId, {
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
                    <TableHead>Meal type</TableHead>
                    <TableHead>Description</TableHead>
                    <TableHead>In menu</TableHead>
                    <TableHead></TableHead>
                    <TableHead></TableHead>
                </TableRow>
            </TableHeader>
            <TableBody>
                {meals.map((meal) => (
                    <TableRow>
                        <TableCell className="font-medium">{meal.productId}</TableCell>
                        <TableCell>{meal.name}</TableCell>
                        <TableCell>{meal.price}</TableCell>
                        <TableCell>{meal.mealType}</TableCell>
                        <TableCell>{meal.description}</TableCell>
                        <TableCell>{meal.active ? "Yes" : "No"}</TableCell>
                        <TableCell>
                            {meal.active ?
                                <Button onClick={() => deactivate(meal.productId)}>Deactivate</Button> :
                                <Button onClick={() => activate(meal.productId)}>Activate</Button>
                            }
                        </TableCell>
                        <TableCell>
                            <Button onClick={() => remove(meal.productId)} className="bg-red-600 hover:bg-red-500 text-white font-bold py-2 px-4 rounded">
                                Delete
                            </Button>
                        </TableCell>
                    </TableRow>
                ))}
            </TableBody>
        </Table>
    )
}
