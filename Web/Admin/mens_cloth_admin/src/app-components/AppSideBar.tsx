"use client"

import {
    Sidebar,
    SidebarContent,
    SidebarFooter,
    SidebarGroup,
    SidebarGroupContent,
    SidebarGroupLabel,
    SidebarHeader,
    SidebarMenu,
    SidebarMenuButton,
    SidebarMenuItem,
    SidebarMenuSub,
    SidebarMenuSubButton,
    SidebarMenuSubItem,
    SidebarProvider,
} from "@/components/ui/sidebar"
import { menuList } from "@/utils/menulist"
import { usePathname, useRouter } from "next/navigation"

export function AppSideBar() {


    const path = usePathname();
    const router = useRouter();
  return (
    <Sidebar collapsible="icon" className={` border-none gap-4  bg-primary`}>
            
    <SidebarHeader className='flex justify-center items-center font-semibold'>
      <SidebarMenuButton onClick={()=>router.push('/')} >
         <span  className='text-[#16151C] text-[22px]'>Men's Wear</span> 
      </SidebarMenuButton>
    </SidebarHeader>
    <SidebarContent >
        <SidebarGroup>
            <SidebarGroupContent>
                <SidebarMenu className='gap-5'>
                    {menuList?.map((item, index) => (
                        <SidebarMenuItem key={index}>
                            <SidebarMenuButton onClick={() => router.push(item?.to)} className={`${path === item.isActive ? ' text-[#006EC4] bg-[#9ad2fd] border-l-[2px]  border-[#1e86d6]':'font-normal'} w-full flex justify-start items-center text-[16px] cursor-pointer px-4 py-4`}>
                                {item?.icon &&  <item.icon/>}
                                {item?.to && <span >{item.name}</span>}
                            </SidebarMenuButton>
                        </SidebarMenuItem>
                    ))}
                </SidebarMenu>
            </SidebarGroupContent>
        </SidebarGroup>
    </SidebarContent>
    <SidebarFooter>
    </SidebarFooter>
</Sidebar>

  )
}
