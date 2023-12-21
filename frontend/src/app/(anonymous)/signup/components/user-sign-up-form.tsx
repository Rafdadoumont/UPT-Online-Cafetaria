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
import Cookies from "js-cookie";
import {da} from "date-fns/locale";
import {Select, SelectContent, SelectItem, SelectTrigger, SelectValue} from "@/components/ui/select";
import {gray} from "next/dist/lib/picocolors";

interface UserSignUpFormProps extends React.HTMLAttributes<HTMLDivElement> {}

const FormSchema = z.object({
    firstname: z.string().min(1, {
        message: "First name is required"
    }),
    lastname: z.string().min(1, {
        message: "Last name is required"
    }),
    email: z.string().email({
        message: "Invalid e-mail address"
    }),
    password: z.string().min(1, {
        message: "Password is required"
    }),
    role: z.string().min(1, {
       message: "Role is required"
    })
})

export function UserSignUpForm({ className, ...props }: UserSignUpFormProps) {
    const router = useRouter();
    const [isLoading, setIsLoading] = React.useState<boolean>(false);
    const [errorMessage, setErrorMessage] = React.useState<string>("");
    const [successMessage, setSuccessMessage] = React.useState<string>("");
    const form = useForm<z.infer<typeof FormSchema>>({
        resolver: zodResolver(FormSchema),
        defaultValues: {
            firstname: "",
            lastname: "",
            email: "",
            password: "",
            role: ""
        }
    })

    async function onSubmit(data: z.infer<typeof FormSchema>) {
        setSuccessMessage("")
        setErrorMessage("")
        setIsLoading(true);
        console.log("Form submit");

        const body = {
            firstname: data.firstname,
            lastname: data.lastname,
            email: data.email,
            password: data.password,
            role: data.role
        };

        try {
            const res: Response = await fetch("http://localhost:8080/api/auth/register", {
                method: "POST",
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(body),
            });
            const bodyJson = await res.json();

            if (res.status === 200) {
                setSuccessMessage("Registration successfully! Redirecting...")
                Cookies.set("access-token", bodyJson.access_token, { path: "/" });
                Cookies.set("user-id", bodyJson.user_id, { path: "/" });
                setTimeout(() => {
                    router.push('/login');
                }, 500);
            } else {
                setErrorMessage(bodyJson.Register);
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
                                name="firstname"
                                render={({field}) => (
                                    <FormItem>
                                        <FormControl>
                                            <Input
                                                {...field}
                                                placeholder="First name"
                                                type="text"
                                                autoCapitalize="none"
                                                autoComplete="none"
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
                                name="lastname"
                                render={({field}) => (
                                    <FormItem>
                                        <FormControl>
                                            <Input
                                                {...field}
                                                placeholder="Last name"
                                                type="text"
                                                autoCapitalize="none"
                                                autoComplete="none"
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
                        <div className="grid gap-1">
                            <FormField
                                control={form.control}
                                name="role"
                                render={({ field }) => (
                                    <FormItem>
                                        <Select onValueChange={field.onChange} defaultValue={field.value}>
                                            <FormControl>
                                                <SelectTrigger className="text-gray-500 font-normal">
                                                    <SelectValue placeholder="Select a role"/>
                                                </SelectTrigger>
                                            </FormControl>
                                            <SelectContent>
                                                <SelectItem key={"USER"} value={"USER"}>
                                                    USER
                                                </SelectItem>
                                                <SelectItem key={"EMPLOYEE"} value={"EMPLOYEE"}>
                                                    EMPLOYEE
                                                </SelectItem>
                                                <SelectItem key={"ADMIN"} value={"ADMIN"}>
                                                    ADMIN
                                                </SelectItem>
                                            </SelectContent>
                                        </Select>
                                        <FormMessage />
                                    </FormItem>
                                )}
                            />
                        </div>
                        <Button type="submit" disabled={isLoading}>
                            Sign Up with Email
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
