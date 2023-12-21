import React from "react";
import {Table, TableBody, TableCaption, TableCell, TableHead, TableHeader, TableRow} from "@/components/ui/table";
import {Reservation} from "@/types";
import {Label} from "@/components/ui/label";
import {format} from "date-fns";

interface reservationTableProps {
    reservations: Reservation[]
}

export function ReservationTable({reservations}: reservationTableProps) {
    return (
        <Table className="">
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
                        <TableCell>{format(new Date(reservation.reservationDate), 'MMMM dd, yyyy')}</TableCell>
                        <TableCell>{reservation.reservationTime}</TableCell>
                        <TableCell colSpan={3}>
                            {reservation.products && reservation.products.map((product, index) => (
                                <span key={product.productId}>
                                    {product.name}
                                    {index !== reservation.products.length - 1 && ', '}
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
