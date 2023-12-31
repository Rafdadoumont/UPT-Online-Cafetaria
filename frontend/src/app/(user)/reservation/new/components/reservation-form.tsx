import React, {Dispatch, SetStateAction, useEffect, useState} from "react";
import { useForm } from "react-hook-form";
import { Button } from "@/components/ui/button";
import {Form, FormControl, FormField, FormItem, FormLabel, FormMessage} from "@/components/ui/form";
import {Select, SelectContent, SelectItem, SelectTrigger, SelectValue} from "@/components/ui/select";
import * as z from "zod";
import {zodResolver} from "@hookform/resolvers/zod";
import {Popover, PopoverContent, PopoverTrigger} from "@/components/ui/popover";
import {cn} from "@/lib/utils";
import { format } from "date-fns"
import {Calendar} from "@/components/ui/calendar";
import {CalendarIcon} from "lucide-react";
import Cookies from "js-cookie";
import {Meal, Product} from "@/types";
import {useRouter} from "next/navigation";

const FormSchema = z.object({
    soup: z.string(),
    meal: z.string(),
    dessert: z.string(),
    date: z.date(),
    time: z.string()
});

export function ReservationForm() {
    const router = useRouter();
    const [availableTimes, setAvailableTimes] = useState([]);
    const [availableSoups, setAvailableSoups] = useState<Product[]>([]);
    const [availableMeals, setAvailableMeals] = useState<Meal[]>([]);
    const [availableDesserts, setAvailableDesserts] = useState<Product[]>([]);
    const [errorMessage, setErrorMessage] = React.useState<string>("");
    const [successMessage, setSuccessMessage] = React.useState<string>("");

    const form = useForm<z.infer<typeof FormSchema>>({
        resolver: zodResolver(FormSchema),
        defaultValues: {},
    })

    async function fetchAvailableTimes() {
        const accessToken = Cookies.get("access-token");

        try {
            const response = await fetch('http://localhost:8080/api/reservation/available-times', {
                headers: {
                    'Authorization': `Bearer ${accessToken}`,
                },
            });
            if (response.ok) {
                const times = await response.json();
                console.log(times)
                setAvailableTimes(times);
            }
        } catch (error) {
            console.error('Error fetching available times:', error);
        }
    }

    async function fetchAvailableOptions<T>(
        type: string,
        setter: Dispatch<SetStateAction<T[]>>
    ) {
        const accessToken = Cookies.get("access-token");

        try {
            const response = await fetch(`http://localhost:8080/api/${type}/active/all`, {
                headers: {
                    'Authorization': `Bearer ${accessToken}`,
                },
            });
            if (response.ok) {
                const options = await response.json();
                setter(options as T[]);
            }
        } catch (error) {
            console.error(`Error fetching ${type} options:`, error);
        }
    }


    async function onSubmit(data: z.infer<typeof FormSchema>) {
        try {
            console.log(data)
            const userId: string | undefined = Cookies.get("user-id");
            const accessToken: string | undefined = Cookies.get('access-token');

            // Filter out "None" selections and construct the productIds array
            const productIds = [data.soup, data.meal, data.dessert].filter(productId => productId !== 'None');

            const body = JSON.stringify({
                productIds: productIds,
                userId: userId,
                reservationDate: data.date,
                reservationTime: data.time
            });
            console.log(body)

            const response = await fetch('http://localhost:8080/api/reservation/add', {
                method: 'POST',
                headers: {
                    'Authorization': `Bearer ${accessToken}`,
                    'Content-Type': 'application/json',
                },
                body: body
            });

            if (response.ok) {
                const reservation = await response.json();
                console.log('Reservation created:', reservation);
                setSuccessMessage("Reservation successfully created!")
                setTimeout(() => {
                    router.push('/reservation/all');
                }, 500);
            } else {
                console.error('Failed to create reservation:', response.status);
                setErrorMessage("Failed to create reservation");
            }
        } catch (error) {
            console.error('Error creating reservation:', error);
        }
    }

    useEffect(() => {
        fetchAvailableTimes();
        fetchAvailableOptions('soup', setAvailableSoups);
        fetchAvailableOptions('meal', setAvailableMeals);
        fetchAvailableOptions('dessert', setAvailableDesserts);
    }, []);

    return (
        <Form {...form}>
            <form onSubmit={form.handleSubmit(onSubmit)} className="w-2/3 space-y-6" noValidate>
                <FormField
                    control={form.control}
                    name="soup"
                    render={({ field }) => (
                        <FormItem>
                            <FormLabel>Soup</FormLabel>
                            <Select onValueChange={field.onChange} defaultValue={field.value}>
                                <FormControl>
                                    <SelectTrigger>
                                        <SelectValue placeholder="Select a soup" />
                                    </SelectTrigger>
                                </FormControl>
                                <SelectContent>
                                    <SelectItem value="None">
                                        None
                                    </SelectItem>
                                    {availableSoups.map((option, index) => (
                                        <SelectItem key={index} value={option.productId.toString()}>
                                            {option.name}
                                        </SelectItem>
                                    ))}
                                </SelectContent>
                            </Select>
                            <FormMessage />
                        </FormItem>
                    )}
                />
                <FormField
                    control={form.control}
                    name="meal"
                    render={({ field }) => (
                        <FormItem>
                            <FormLabel>Meal</FormLabel>
                            <Select onValueChange={field.onChange} defaultValue={field.value}>
                                <FormControl>
                                    <SelectTrigger>
                                        <SelectValue placeholder="Select a meal" />
                                    </SelectTrigger>
                                </FormControl>
                                <SelectContent>
                                    <SelectItem value="None">
                                        None
                                    </SelectItem>
                                    {availableMeals.map((option, index) => (
                                        <SelectItem key={index} value={option.productId.toString()} mealType={option.mealType}>
                                            {option.name}
                                        </SelectItem>
                                    ))}
                                </SelectContent>
                            </Select>
                            <FormMessage />
                        </FormItem>
                    )}
                />
            <FormField
                control={form.control}
                name="dessert"
                render={({ field }) => (
                    <FormItem>
                        <FormLabel>Dessert</FormLabel>
                        <Select onValueChange={field.onChange} defaultValue={field.value}>
                            <FormControl>
                                <SelectTrigger>
                                    <SelectValue placeholder="Select a dessert" />
                                </SelectTrigger>
                            </FormControl>
                            <SelectContent>
                                <SelectItem value="None">
                                    None
                                </SelectItem>
                                {availableDesserts.map((option, index) => (
                                    <SelectItem key={index} value={option.productId.toString()}>
                                        {option.name}
                                    </SelectItem>
                                ))}
                            </SelectContent>
                        </Select>
                        <FormMessage />
                    </FormItem>
                )}
            />
            <FormField
                control={form.control}
                name="date"
                render={({ field }) => (
                    <FormItem className="flex flex-col">
                        <FormLabel>Reservation Date</FormLabel>
                        <Popover>
                            <PopoverTrigger asChild>
                                <FormControl>
                                    <Button
                                        variant={"outline"}
                                        className={cn(
                                            "pl-3 text-left font-normal",
                                        )}
                                    >
                                        {field.value ? (
                                            format(field.value, "PPP")
                                        ) : (
                                            <span>Pick a date</span>
                                        )}
                                        <CalendarIcon className="ml-auto h-4 w-4 opacity-50" />
                                    </Button>
                                </FormControl>
                            </PopoverTrigger>
                            <PopoverContent className="w-auto p-0" align="center">
                                <Calendar
                                    mode="single"
                                    selected={field.value}
                                    onSelect={field.onChange}
                                    disabled={(date) =>
                                        // Check if the date is today and the time is after 11 am
                                        date < new Date(new Date().setHours(0, 0, 0, 0)) || // Disable past dates
                                        (date.toDateString() === new Date().toDateString() && new Date().getHours() >= 11)
                                    }
                                    initialFocus
                                />
                            </PopoverContent>
                        </Popover>
                    </FormItem>
                )}
            />
                <FormField
                    control={form.control}
                    name="time"
                    render={({ field }) => (
                        <FormItem>
                            <FormLabel>Time</FormLabel>
                            <Select onValueChange={field.onChange} defaultValue={field.value}>
                                <FormControl>
                                    <SelectTrigger>
                                        <SelectValue placeholder="Select a time" />
                                    </SelectTrigger>
                                </FormControl>
                                <SelectContent>
                                    {availableTimes.map((time, index) => (
                                        <SelectItem key={index} value={time}>
                                            {time}
                                        </SelectItem>
                                    ))}
                                </SelectContent>
                            </Select>
                            <FormMessage />
                        </FormItem>
                    )}
                />
                <Button type="submit">Make Reservation</Button>
                {errorMessage && (
                    <div className="text-red-600 flex items-center justify-center">{errorMessage}</div>
                )}
                {successMessage && (
                    <div className="text-green-500 flex items-center justify-center">{successMessage}</div>
                )}
            </form>
        </Form>
    );
}
