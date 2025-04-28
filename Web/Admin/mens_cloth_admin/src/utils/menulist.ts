import { MenuType } from "@/types/menutype";
import { LayoutDashboard } from 'lucide-react';
import { ChartBarStacked } from 'lucide-react';
import { SquareChartGantt } from 'lucide-react';
import { Logs } from 'lucide-react';
import { Users } from 'lucide-react';
import { UserCheck } from 'lucide-react';


export const menuList:MenuType[] = [
    {
        name:"Dashboard",
        to:"/",
        isActive:"/",
        icon: LayoutDashboard,
        accessList:["admin","manager","employee"]
    },
    {
        name:"Category",
        to:"/category",
        isActive:"/product",
        icon:ChartBarStacked ,
        accessList:["admin","manager","employee"]
    },
    {
        name:"Product",
        to:"/product",
        isActive:"/product",
        icon:SquareChartGantt,
        accessList:["admin","manager","employee"]
    },
    {
        name:"Order",
        to:"/order",
        isActive:"/order",
        icon:Logs,
        accessList:["admin","manager","employee"]
    },
    {
        name:"Customer",
        to:"/customer",
        isActive:"/customer",
        icon:Users,
        accessList:["admin","manager"]
    },
    {
        name:"User",
        to:"/user",
        isActive:"/user",
        icon:UserCheck,
        accessList:["admin"]
    },
]