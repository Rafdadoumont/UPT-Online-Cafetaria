import React from "react";
import {Table, TableBody, TableCaption, TableCell, TableHead, TableHeader, TableRow} from "@/components/ui/table";
import {Meal, Product} from "@/types";

interface MealTableProps {
    meals: Meal[]
}

export function MealTable({meals}: MealTableProps) {
    return (
        <Table>
            <TableHeader>
                <TableRow>
                    <TableHead>Id</TableHead>
                    <TableHead>Name</TableHead>
                    <TableHead>Price</TableHead>
                    <TableHead>Meal type</TableHead>
                    <TableHead>Description</TableHead>
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
                    </TableRow>
                ))}
            </TableBody>
        </Table>
    )
}
