"use client"
import React from "react";
import {RadioGroup, RadioGroupItem} from "@/components/ui/radio-group";
import {Label} from "@/components/ui/label";
import {Input} from "@/components/ui/input";
import {Button} from "@/components/ui/button";
import Image from "next/image";
import {Textarea} from "@/components/ui/textarea";
import {useForm} from "react-hook-form";
import * as z from "zod";
import {zodResolver} from "@hookform/resolvers/zod";
import {Form, FormControl, FormField, FormItem, FormLabel, FormMessage} from "@/components/ui/form";
import Cookies from "js-cookie";
import {useRouter} from "next/navigation";

const FormSchema = z.object({
    type: z.enum(["soup", "meal", "dessert", "drink"], {
        required_error: "Please select a food type."
    }),
    mealType: z.string().optional(),
    name: z.string({
        required_error: "Name is required."
    }).min(2, {
        message: "Name must be at least 2 characters.",
    }),
    price: z.string({
        required_error: "Price is required."
    }).refine((val) => !isNaN(Number(val)), {
        message: "Price must be a number",
    }).refine((val) => Number(val) >= 0, {
        message: "Price must be a positive number",
    }).transform((val) => Number(val)),
    description: z.string().max(120, {
        message: "Description has a maximum of 120 characters."
    }).optional(),
})

export function ProductForm() {
    const router = useRouter();
    const [errorMessage, setErrorMessage] = React.useState<string>("");
    const [successMessage, setSuccessMessage] = React.useState<string>("");

    const form = useForm<z.infer<typeof FormSchema>>({
        resolver: zodResolver(FormSchema),
        defaultValues: {
            mealType: "meat"
        },
    })

    async function onSubmit(data: z.infer<typeof FormSchema>) {
        let body
        if (data.type == "meal") {
            body = {
                name: data.name,
                price: data.price,
                description: data.description,
                mealType: data.mealType
            }
        } else {
            body = {
                name: data.name,
                price: data.price,
                description: data.description,
            }
        }
        try {
            console.log(body)
            const accessToken = Cookies.get("access-token");
            const response: Response = await fetch('http://localhost:8080/api/' + data.type + '/add', {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${accessToken}`,
                },
                body: JSON.stringify(body)
            });

            console.log(response)
            if (response.ok) {
                const bodyJson = await response.json();
                console.log(bodyJson)
                setSuccessMessage("Product successfully created!")
                setTimeout(() => {
                    router.push('/product/all');
                }, 500);
            }
        } catch (error) {
            console.error('Error adding product: ', error);
            setErrorMessage("Failed to create product");
        }
    }

    return (
        <Form {...form}>
            <form onSubmit={form.handleSubmit(onSubmit)} className="w-2/3 space-y-6" noValidate>
                <FormField
                    control={form.control}
                    name="type"
                    render={({field}) => (
                        <FormItem>
                            <FormControl>
                                <RadioGroup
                                    onValueChange={field.onChange}
                                    defaultValue={field.value}
                                    className="grid grid-cols-4 gap-4">

                                    {/*SOUP*/}
                                    <FormItem>
                                        <RadioGroupItem value="soup" id="soup" className="peer sr-only"/>
                                        <Label
                                            htmlFor="soup"
                                            className="flex flex-col items-center justify-between rounded-md border-2 border-muted bg-popover p-4 hover:bg-accent hover:text-accent-foreground peer-data-[state=checked]:border-primary [&:has([data-state=checked])]:border-primary cursor-pointer"
                                        >
                                            <Image src="/soup.png" alt="soup icon" height={30} width={30}/>
                                            Soup
                                        </Label>
                                    </FormItem>

                                    {/*MEAL*/}
                                    <FormItem>
                                        <RadioGroupItem value="meal" id="meal" className="peer sr-only" />
                                        <Label
                                            htmlFor="meal"
                                            className="flex flex-col items-center justify-between rounded-md border-2 border-muted bg-popover p-4 hover:bg-accent hover:text-accent-foreground peer-data-[state=checked]:border-primary [&:has([data-state=checked])]:border-primary cursor-pointer"
                                        >
                                            <Image src="/meal.png" alt="meal icon" height={30} width={30}/>
                                            Meal
                                        </Label>
                                    </FormItem>

                                    {/*DESSERT*/}
                                    <FormItem>
                                        <RadioGroupItem value="dessert"  id="dessert" className="peer sr-only" />
                                        <Label
                                            htmlFor="dessert"
                                            className="flex flex-col items-center justify-between rounded-md border-2 border-muted bg-popover p-4 hover:bg-accent hover:text-accent-foreground peer-data-[state=checked]:border-primary [&:has([data-state=checked])]:border-primary cursor-pointer"
                                        >
                                            <Image src="/dessert.png" alt="meal icon" height={30} width={30}/>
                                            Dessert
                                        </Label>
                                    </FormItem>

                                    {/*DRINK*/}
                                    <FormItem>
                                        <RadioGroupItem value="drink" id="drink"  className="peer sr-only" />
                                        <Label
                                            htmlFor="drink"
                                            className="flex flex-col items-center justify-between rounded-md border-2 border-muted bg-popover p-4 hover:bg-accent hover:text-accent-foreground peer-data-[state=checked]:border-primary [&:has([data-state=checked])]:border-primary cursor-pointer"
                                        >
                                            <Image src="/drink.png" alt="drink icon" height={30} width={30}/>
                                            Drink
                                        </Label>
                                    </FormItem>
                                </RadioGroup>
                            </FormControl>
                        <FormMessage />
                    </FormItem>
                    )}
                />
                {form.watch('type') === 'meal' && (
                    <FormField
                        control={form.control}
                        name="mealType"
                        render={({ field }) => (
                            <FormItem>
                                <FormLabel>Meal Type</FormLabel>
                                <FormControl>
                                    <RadioGroup
                                        onValueChange={field.onChange}
                                        defaultValue={"meat"}
                                        className="flex gap-4"
                                    >
                                        <RadioGroupItem value="meat" id="meat" />
                                        <Label htmlFor="meat">Meat</Label>
                                        <RadioGroupItem value="fish" id="fish" />
                                        <Label htmlFor="fish">Fish</Label>
                                        <RadioGroupItem value="vegetarian" id="vegetarian" />
                                        <Label htmlFor="vegetarian">Vegetarian</Label>
                                    </RadioGroup>
                                </FormControl>
                                <FormMessage />
                            </FormItem>
                        )}
                    />
                )}
                <FormField
                    control={form.control}
                    name="name"
                    render={({ field }) => (
                        <FormItem>
                            <FormLabel>Name</FormLabel>
                            <FormControl>
                                <Input {...field} />
                            </FormControl>
                            <FormMessage />
                        </FormItem>
                    )}
                />
                <FormField
                    control={form.control}
                    name="price"
                    render={({ field }) => (
                        <FormItem>
                            <FormLabel>Price</FormLabel>
                            <FormControl>
                                <Input {...field} />
                            </FormControl>
                            <FormMessage />
                        </FormItem>
                    )}
                />
                <FormField
                    control={form.control}
                    name="description"
                    render={({ field }) => (
                        <FormItem>
                            <FormLabel>Description</FormLabel>
                            <FormControl>
                                <Textarea {...field} />
                            </FormControl>
                            <FormMessage />
                        </FormItem>
                    )}
                />
                <Button className="w-full" type="submit">Add product</Button>
                {errorMessage && (
                    <div className="text-red-600 flex items-center justify-center">{errorMessage}</div>
                )}
                {successMessage && (
                    <div className="text-green-500 flex items-center justify-center">{successMessage}</div>
                )}
            </form>
        </Form>
    )
}
