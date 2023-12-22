export interface User {
    id: number,
    firstname: string,
    lastname: string
    email: string
}

export interface Product {
    productId: number;
    name: string;
    price: number;
    description: string;
    active: boolean;
}

export interface Meal extends Product {
    mealType: string;
}

export interface Reservation {
    reservationId: number,
    user: User,
    products: Product[],
    reservationDate: string,
    reservationTime: string,
    fulfilled: boolean
}

export interface Soup extends Product {
}

export interface Dessert extends Product {
}

export enum MealType {
    MEAT= "MEAT",
    FISH = "FISH",
    VEGGIE = "VEGGIE"
}

export interface Drink extends Product {
    sparkling: boolean,
    caffeine: boolean,
    sugarLevel: SugarLevel
}

export enum SugarLevel {
    ZERO_SUGARS = "ZERO_SUGARS",
    NO_ADDED_SUGARS = "NO_ADDED_SUGARS",
    ADDED_SUGARS = "ADDED_SUGARS"
}

export enum Role{
    ADMIN = "ADMIN",
    EMPLOYEE = "EMPLOYEE",
    USER = "USER"
}

export function isValidString(string: string): boolean {
    if (string !== null && typeof string == "string") {
        if (string.trim().length > 0) {
            return true;
        } else {
            throw new Error(`${string} cannot be empty.`);
        }
    } else {
        throw new Error(`${string} cannot be empty.`);
    }
}

export function isValidNumber(number: number, minimum: number, maximum?: number): boolean {
    if (number !== null && typeof number === "number" && !isNaN(number)) {
        if (maximum === undefined || maximum === null) {
            if (number >= minimum) {
                return true;
            } else {
                throw new Error(`${number} cannot be smaller than the minimum.`);
            }
        } else {
            if (number >= minimum && number <= maximum) {
                return true;
            } else {
                throw new Error(`${number} is not in the range of the minimum and maximum.`);
            }
        }
    } else {
        throw new Error(`${number} is not a valid number.`);
    }
}


