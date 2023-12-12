import React from "react";
import {Table, TableBody, TableCaption, TableCell, TableHead, TableHeader, TableRow} from "@/components/ui/table";
import {Product} from "@/types";

interface ProductTableProps {
    products: Product[]
}

export function ProductTable({products}: ProductTableProps) {
    return (
        <Table>
            <TableCaption>An overview of available products</TableCaption>
            <TableHeader>
                <TableRow>
                    <TableHead>Id</TableHead>
                    <TableHead>Name</TableHead>
                    <TableHead>Price</TableHead>
                    <TableHead>Description</TableHead>
                </TableRow>
            </TableHeader>
            <TableBody>
                {products.map((product) => (
                    <TableRow>
                        <TableCell className="font-medium">{product.productId}</TableCell>
                        <TableCell>{product.name}</TableCell>
                        <TableCell>{product.price}</TableCell>
                        <TableCell>{product.description}</TableCell>
                    </TableRow>
                ))}
            </TableBody>
        </Table>
    )
}
