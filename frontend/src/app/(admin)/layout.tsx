import {NavigationBar} from "@/components/navigation/navigation-bar";

export default function AppLayout({children}: {
    children: React.ReactNode
}) {
    return (
        <>
            <NavigationBar />
            {children}
        </>
    )
}
