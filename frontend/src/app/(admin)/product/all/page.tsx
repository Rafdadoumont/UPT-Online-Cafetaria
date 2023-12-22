"use client"
import {FormEventHandler, useEffect, useState} from "react";
import Cookies from 'js-cookie';
import {Meal, Product as ProductType} from "@/types";
import {DessertTable} from "@/app/(admin)/product/components/dessert-table";
import {ProductForm} from "@/app/(admin)/product/components/product-form";
import {Button} from "@/components/ui/button";
import {MealTable} from "@/app/(admin)/product/components/meal-table";
import {SoupTable} from "@/app/(admin)/product/components/soup-table";
import {ToggleGroup, ToggleGroupItem} from "@/components/ui/toggle-group";
import {Bold, Italic, Underline} from "lucide-react";

export default function AllProductPage() {
    const [selectedTable, setSelectedTable] = useState<string>("Soup");
    const [desserts, setDesserts] = useState<ProductType[]>([]);
    const [meals, setMeals] = useState<Meal[]>([]);
    const [soups, setSoups] = useState<ProductType[]>([]);

    const handleToggleChange = (value: string) => {
        setSelectedTable(value);
    };


    useEffect(() => {
        async function init() {
            try {
                const accessToken: string | undefined = Cookies.get('access-token');
                const resDesserts = await fetch('http://localhost:8080/api/dessert/all', {
                    headers: {
                        'Authorization': `Bearer ${accessToken}`,
                    },
                });
                const dataDesserts = await resDesserts.json();
                setDesserts(dataDesserts);

                const resMeals = await fetch('http://localhost:8080/api/meal/all', {
                    headers: {
                        'Authorization': `Bearer ${accessToken}`,
                    },
                });
                const dataMeals = await resMeals.json();
                setMeals(dataMeals);

                const resSoups = await fetch('http://localhost:8080/api/soup/all', {
                    headers: {
                        'Authorization': `Bearer ${accessToken}`,
                    },
                });
                const dataSoups = await resSoups.json();
                setSoups(dataSoups);
            } catch (error) {
                console.error("Error fetching products:", error);
            }
        }
        init();
    }, []);

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
                {selectedTable === "Soup" && <SoupTable products={soups} />}
                {selectedTable === "Meal" && <MealTable meals={meals} />}
                {selectedTable === "Dessert" && <DessertTable products={desserts} />}
            </div>
        </div>
    );
}
