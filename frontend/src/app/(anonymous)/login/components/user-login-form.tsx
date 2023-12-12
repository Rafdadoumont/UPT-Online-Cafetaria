"use client"
import * as React from "react"

import { cn } from "@/lib/utils"
import { Button } from "@/components/ui/button"
import { Input } from "@/components/ui/input"
import { Label } from "@/components/ui/label"
import {Form, FormControl, FormField, FormItem, FormLabel, FormMessage} from "@/components/ui/form";
import {useForm} from "react-hook-form";
import * as z from "zod";
import {zodResolver} from "@hookform/resolvers/zod";
import {useRouter} from "next/navigation";

interface UserLoginFormProps extends React.HTMLAttributes<HTMLDivElement> {}

const FormSchema = z.object({
    email: z.string().email({
        message: "Invalid e-mail address"
    }),
    password: z.string().min(1, {
        message: "Password is required"
    })
})

export function UserLoginForm({ className, ...props }: UserLoginFormProps) {
    const router = useRouter();
    const [isLoading, setIsLoading] = React.useState<boolean>(false);
    const [errorMessage, setErrorMessage] = React.useState<string>("");
    const [successMessage, setSuccessMessage] = React.useState<string>("");
    const form = useForm<z.infer<typeof FormSchema>>({
        resolver: zodResolver(FormSchema),
        defaultValues: {
            email: "",
            password: ""
        }
    })

    async function onSubmit(data: z.infer<typeof FormSchema>) {
        setIsLoading(true);
        console.log("Form submit");

        const body = {
            email: data.email,
            password: data.password
        };

        try {
            const res: Response = await fetch("http://localhost:8080/api/auth/login", {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(body),
            });
            const bodyJson = await res.json();

            if (res.status === 200) {
                setSuccessMessage("Authenticated successfully! Redirecting...")
                document.cookie = `access-token=${bodyJson.access_token}; path=/;`;
                setTimeout(() => {
                    router.push('/home');
                }, 500);
            } else {
                setErrorMessage(bodyJson.Authenticate);
            }

        } catch (error) {
            console.error("Error submitting form:", error);
            setErrorMessage("An error occurred, please try again");
        } finally {
            setIsLoading(false);
        }
    }

    return (
        <Form {...form}>
            <form onSubmit={form.handleSubmit(onSubmit)} noValidate>
                <div className={cn("grid gap-6", className)} {...props}>
                    <div className="grid gap-2">
                        <div className="grid gap-1">
                            <FormField
                                control={form.control}
                                name="email"
                                render={({field}) => (
                                    <FormItem>
                                        <FormControl>
                                            <Input
                                                {...field}
                                                placeholder="Email"
                                                type="email"
                                                autoCapitalize="none"
                                                autoComplete="email"
                                                autoCorrect="off"
                                                disabled={isLoading}
                                            />
                                        </FormControl>
                                        <FormMessage />
                                    </FormItem>
                                )}/>
                        </div>
                        <div className="grid gap-1">
                            <FormField
                                control={form.control}
                                name="password"
                                render={({field}) => (
                                    <FormItem>
                                        <FormControl>
                                            <Input
                                                {...field}
                                                id="password"
                                                placeholder="Password"
                                                type="password"
                                                autoCapitalize="none"
                                                autoComplete="password"
                                                autoCorrect="off"
                                                disabled={isLoading}
                                            />
                                        </FormControl>
                                        <FormMessage />
                                    </FormItem>
                                )}
                            />
                        </div>
                        <Button type="submit" disabled={isLoading}>
                            Sign In with Email
                        </Button>
                        {errorMessage && (
                            <div className="text-red-600 flex items-center justify-center">{errorMessage}</div>
                        )}
                        {successMessage && (
                            <div className="text-green-500 flex items-center justify-center">{successMessage}</div>
                        )}
                    </div>
                </div>
            </form>
        </Form>
    )
}
