'use client'
import React, { useState } from 'react'
import { AppSideBar } from './AppSideBar'
import { SidebarProvider, SidebarTrigger } from '@/components/ui/sidebar'

interface Props{
    children:React.ReactNode
}
function Layout({children}:Props) {

  const[openMenu,setOpenMenu]=useState<boolean>(true);

  return (
    <main className='w-full h-full flex'>
         <SidebarProvider>
         <AppSideBar openMenu={openMenu} setOpenMenu={setOpenMenu}/>
         

        <div className='w-full h-full'>
        <SidebarTrigger className="-ml-1" onClick={()=>{setOpenMenu(!openMenu)}}/>
            {children}
        </div>

         </SidebarProvider>
        

    </main>
  )
}

export default Layout