"use client"
import {DashboardTable} from "@/app/(employee)/dashboard/components/dashboard-table";

export default function DashboardPage() {

    return(
        <div className="container mx-auto px-4 py-8 lg:py-16">
        <div className="flex flex-col">
            <div>
                <h1 className="text-3xl font-bold mb-1">Dashboard</h1>
                <p className="text-sm text-muted-foreground mb-6">Follow up on orders and fulfill them when they have
                    been served.</p>
                <DashboardTable fulfilled={false}/>
                <h1 className="text-3xl font-bold mb-1">Fulfilled orders</h1>
                <DashboardTable fulfilled={true}/>
            </div>
        </div>
        </div>
    );
}
