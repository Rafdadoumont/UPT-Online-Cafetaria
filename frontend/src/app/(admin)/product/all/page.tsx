"use client"
import { useState} from "react";
import {Meal} from "@/types";
import {DessertTable} from "@/app/(admin)/product/components/dessert-table";
import {MealTable} from "@/app/(admin)/product/components/meal-table";
import {SoupTable} from "@/app/(admin)/product/components/soup-table";
import {ToggleGroup, ToggleGroupItem} from "@/components/ui/toggle-group";

export default function AllProductPage() {
    const [selectedTable, setSelectedTable] = useState<string>("Soup");

    const handleToggleChange = (value: string) => {
        setSelectedTable(value);
    };

    return (
        <div className="container mx-auto px-4 py-8 lg:py-16 flex flex-col items-start">
            <div className="flex justify-between w-full">
                <div>
                    <h1 className="text-3xl font-bold mb-1">Products</h1>
                    <p className="text-sm text-muted-foreground mb-6">
                        An overview of all products in the database.
                    </p>
                </div>
                <div className="flex items-center">
                    <ToggleGroup type="single" value={selectedTable} onValueChange={handleToggleChange}>
                        <ToggleGroupItem value="Soup" aria-label="Toggle Soup">
                            <p>Soup</p>
                        </ToggleGroupItem>
                        <ToggleGroupItem value="Meal" aria-label="Toggle Meal">
                            <p>Meal</p>
                        </ToggleGroupItem>
                        <ToggleGroupItem value="Dessert" aria-label="Toggle Dessert">
                            <p>Dessert</p>
                        </ToggleGroupItem>
                    </ToggleGroup>
                </div>
            </div>
            <div className="w-full flex justify-between">
                {selectedTable === "Soup" && <SoupTable />}
                {selectedTable === "Meal" && <MealTable />}
                {selectedTable === "Dessert" && <DessertTable  />}
            </div>
        </div>
    );
}
