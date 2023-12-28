import {useEffect, useState} from "react";
import {Reservation} from "@/types";
import Cookies from "js-cookie";
import {Table, TableBody, TableCell, TableHead, TableHeader, TableRow} from "@/components/ui/table";
import {Label} from "@/components/ui/label";
import {Button} from "@/components/ui/button";

interface DashboardTableProps {
    fulfilled: boolean
}

export function DashboardTable({fulfilled}: DashboardTableProps) {
    const [reservations , setReservations] = useState<Reservation[]>([]);
    const [rerender, setRerender] = useState<boolean>()

    useEffect(() => {
        async function fetchData() {
            try {
                const accessToken: String | undefined = Cookies.get('access-token');
                if(fulfilled){
                    const res = await fetch('http://localhost:8080/api/reservation/all/fulfilled', {
                        headers: {
                            'Authorization': `Bearer ${accessToken}`,
                        },
                    });
                    const data = await res.json();
                    setReservations(data);
                } else {
                    const res = await fetch('http://localhost:8080/api/reservation/all/unfulfilled', {
                        headers: {
                            'Authorization': `Bearer ${accessToken}`,
                        },
                    });
                    const data = await res.json();
                    setReservations(data);
                }
            } catch (error) {
                console.error("Error fetching reservations:", error);
            }
        }
        fetchData();
    }, [rerender]);

    async function toggleFulfill(reservationId: number) {
        try {
            const accessToken = Cookies.get("access-token");
            const response: Response = await fetch('http://localhost:8080/api/reservation/toggle-fulfill/' + reservationId, {
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

    return (
        <Table>
            <TableHeader>
                <TableRow>
                    <TableHead>Date</TableHead>
                    <TableHead>Time</TableHead>
                    <TableHead>Products</TableHead>
                    <TableHead>Status</TableHead>
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
                            {reservation.fulfilled ?
                                <Button onClick={() => toggleFulfill(reservation.reservationId)}
                                        className="bg-red-600 hover:bg-green-500 font-bold">Un-fulfill</Button> :
                                <Button onClick={() => toggleFulfill(reservation.reservationId)}
                                        className="bg-green-500 hover:bg-red-600 font-bold">Fulfill</Button>
                            }
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
    )
}