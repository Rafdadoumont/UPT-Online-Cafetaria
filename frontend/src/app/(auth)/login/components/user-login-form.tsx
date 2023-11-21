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
    const [isLoading, setIsLoading] = React.useState<boolean>(false)
    const form = useForm<z.infer<typeof FormSchema>>({
        resolver: zodResolver(FormSchema),
        defaultValues: {
            email: "",
            password: ""
        }
    })

    function onSubmit(data: z.infer<typeof FormSchema>) {
        setIsLoading(true)
        console.log("Form submit")
        console.log(data)
        setTimeout(() => {
            setIsLoading(false)
        }, 3000)
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
                                name="email"
                                render={({field}) => (
                                    <FormItem>
                                        <FormControl>
                                            <Input
                                                id="password"
                                                placeholder="Password"
                                                type="password"
                                                autoCapitalize="none"
                                                autoComplete="password"
                                                autoCorrect="off"
                                                disabled={isLoading}
                                            />
                                        </FormControl>
                                    </FormItem>
                                )}
                            />
                        </div>
                        <Button type="submit" disabled={isLoading}>
                            Sign In with Email
                        </Button>
                    </div>
                </div>
            </form>
        </Form>
    )
}
