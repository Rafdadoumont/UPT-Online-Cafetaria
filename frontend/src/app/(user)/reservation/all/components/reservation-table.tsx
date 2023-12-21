import React, { useState } from "react";
import { Table, TableBody, TableCell, TableHeader, TableHead, TableRow } from "@/components/ui/table";
import { Reservation } from "@/types";
import { Label } from "@/components/ui/label";
import QRCode from "react-qr-code";
import {
    Dialog,
    DialogContent,
    DialogHeader,
    DialogTitle,
    DialogTrigger
} from "@/components/ui/dialog";

interface ReservationTableProps {
    reservations: Reservation[]
}

export function ReservationTable({ reservations }: ReservationTableProps) {
    const [isDialogOpen, setIsDialogOpen] = useState(false);
    const [selectedReservation, setSelectedReservation] = useState<Reservation | null>(null);

    const openDialog = (reservation: Reservation) => {
        setSelectedReservation(reservation);
        setIsDialogOpen(true);
    };

    const closeDialog = () => {
        setSelectedReservation(null);
        setIsDialogOpen(false);
    };

    return (
        <>
            <Table>
                <TableHeader>
                    <TableRow>
                        <TableHead>Date</TableHead>
                        <TableHead>Time</TableHead>
                        <TableHead>Products</TableHead>
                        <TableHead />
                    </TableRow>
                </TableHeader>
                <TableBody>
                    {reservations.map((reservation) => (
                        <TableRow key={reservation.reservationId}>
                            <TableCell>{reservation.reservationDate}</TableCell>
                            <TableCell>{reservation.reservationTime}</TableCell>
                            <TableCell>
                                {reservation.products?.map((product) => (
                                    <span key={product.productId}>{product.name}, </span>
                                ))}
                            </TableCell>
                            <TableCell>
                                <button onClick={() => openDialog(reservation)}>View details</button>
                            </TableCell>
                        </TableRow>
                    ))}
                    {reservations.length === 0 && (
                        <TableRow>
                            <TableCell colSpan={4} className="text-center">
                                <Label>No reservations</Label>
                            </TableCell>
                        </TableRow>
                    )}
                </TableBody>
            </Table>

            <Dialog open={isDialogOpen} onOpenChange={closeDialog} >
                <DialogContent>
                    <DialogHeader>
                        <DialogTitle>Reservation Details</DialogTitle>
                    </DialogHeader>
                    <p>Reservation Date: {selectedReservation?.reservationDate}</p>
                    <p>Reservation Time: {selectedReservation?.reservationTime}</p>
                    <QRCode value={"http://localhost:3000/reservation/" + selectedReservation?.reservationId}/>
                </DialogContent>
            </Dialog>
        </>
    );
}
