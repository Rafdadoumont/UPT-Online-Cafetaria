"use client"
import React, {useState} from "react";
import {Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle} from "@/components/ui/card";
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

const FormSchema = z.object({
    type: z.enum(["soup", "meal", "dessert", "drink"], {
        required_error: "Please select a food type."
    }),
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
    const form = useForm<z.infer<typeof FormSchema>>({
        resolver: zodResolver(FormSchema),
        defaultValues: {

        },
    })

    function onSubmit(data: z.infer<typeof FormSchema>) {
        console.log(data)
    }

    return (
        <Form {...form}>
            <form onSubmit={form.handleSubmit(onSubmit)} noValidate>
                <Card>
                    <CardHeader>
                        <CardTitle>Product</CardTitle>
                        <CardDescription>
                            Add a new product to available options.
                        </CardDescription>
                    </CardHeader>
                    <CardContent className="grid gap-6">
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
                    </CardContent>
                    <CardFooter>
                        <Button className="w-full" type="submit">Continue</Button>
                    </CardFooter>
                </Card>
            </form>
        </Form>
    )
}
