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
}

export interface Meal extends Product {
    mealType: string;
}

export interface Reservation {
    reservationId: number,
    user: User,
    product: Product[],
    reservationDate: string,
    reservationTime: string
}
