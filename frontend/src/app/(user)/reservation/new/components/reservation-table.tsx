import React from "react";
import {Table, TableBody, TableCaption, TableCell, TableHead, TableHeader, TableRow} from "@/components/ui/table";
import {Reservation} from "@/types";
import {Label} from "@/components/ui/label";

interface reservationTableProps {
    reservations: Reservation[]
}

export function ReservationTable({reservations}: reservationTableProps) {
    return (
        <Table className="border border-gray-300">
            <TableHeader>
                <TableRow>
                    <TableHead>Date</TableHead>
                    <TableHead>Time</TableHead>
                    <TableHead>Products</TableHead>
                </TableRow>
            </TableHeader>
            <TableBody>
                {reservations && reservations.map((reservation) => (
                    <TableRow>
                        <TableCell>{reservation.reservationDate}</TableCell>
                        <TableCell>{reservation.reservationTime}</TableCell>
                        <TableCell>
                            {reservation.product && reservation.product.map((product, index) => (
                                <span key={product.id}>
                                    {product.name}
                                    {index !== reservation.product.length - 1 && ', '}
                                </span>
                            ))}
                        </TableCell>
                    </TableRow>
                ))}
                {reservations.length === 0 && (
                    <TableRow>
                        <TableCell colSpan={3} className="text-center">
                            <Label>No reservations</Label>
                        </TableCell>
                    </TableRow>
                )}
            </TableBody>
        </Table>
    )
}
