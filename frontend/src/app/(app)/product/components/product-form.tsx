import React from "react";
import {Card, CardContent, CardDescription, CardFooter, CardHeader, CardTitle} from "@/components/ui/card";
import {RadioGroup, RadioGroupItem} from "@/components/ui/radio-group";
import {Label} from "@/components/ui/label";
import {Input} from "@/components/ui/input";
import {Select, SelectContent, SelectItem, SelectTrigger, SelectValue} from "@/components/ui/select";
import {Button} from "@/components/ui/button";
import Image from "next/image";
import {number} from "prop-types";
import {Textarea} from "@/components/ui/textarea";

export function ProductForm() {
    return (
        <Card>
            <CardHeader>
                <CardTitle>Product</CardTitle>
                <CardDescription>
                    Add a new product to available options.
                </CardDescription>
            </CardHeader>
            <CardContent className="grid gap-6">
                <RadioGroup defaultValue="card" className="grid grid-cols-3 gap-4">
                    <div>
                        <RadioGroupItem value="card" id="card" className="peer sr-only" />
                        <Label
                            htmlFor="card"
                            className="flex flex-col items-center justify-between rounded-md border-2 border-muted bg-popover p-4 hover:bg-accent hover:text-accent-foreground peer-data-[state=checked]:border-primary [&:has([data-state=checked])]:border-primary"
                        >
                            <Image src="/soup.png" alt="soup icon" height={30} width={30}/>
                            Soup
                        </Label>
                    </div>
                    <div>
                        <RadioGroupItem
                            value="paypal"
                            id="paypal"
                            className="peer sr-only"
                        />
                        <Label
                            htmlFor="paypal"
                            className="flex flex-col items-center justify-between rounded-md border-2 border-muted bg-popover p-4 hover:bg-accent hover:text-accent-foreground peer-data-[state=checked]:border-primary [&:has([data-state=checked])]:border-primary"
                        >
                            <Image src="/meal.png" alt="meal icon" height={30} width={30}/>
                            Meal
                        </Label>
                    </div>
                    <div>
                        <RadioGroupItem value="apple" id="apple" className="peer sr-only" />
                        <Label
                            htmlFor="apple"
                            className="flex flex-col items-center justify-between rounded-md border-2 border-muted bg-popover p-4 hover:bg-accent hover:text-accent-foreground peer-data-[state=checked]:border-primary [&:has([data-state=checked])]:border-primary"
                        >
                            <Image src="/dessert.png" alt="meal icon" height={30} width={30}/>
                            Dessert
                        </Label>
                    </div>
                </RadioGroup>
                <div className="grid gap-2">
                    <Label>Name</Label>
                    <Input id="name" />
                </div>
                <div className="grid gap-2">
                    <Label>Price</Label>
                    <Input id="number" type="number" />
                </div>
                <div className="grid gap-2">
                    <Label>Description</Label>
                    <Textarea id="description" className="w-full" />
                </div>
            </CardContent>
            <CardFooter>
                <Button className="w-full">Continue</Button>
            </CardFooter>
        </Card>
    )
}
