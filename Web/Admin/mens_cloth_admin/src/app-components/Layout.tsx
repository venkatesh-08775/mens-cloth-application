import React from 'react'
import { AppSideBar } from './AppSideBar'
import { SidebarProvider, SidebarTrigger } from '@/components/ui/sidebar'

interface Props{
    children:React.ReactNode
}
function Layout({children}:Props) {
  return (
    <main className='w-full h-full flex'>
         <SidebarProvider>
         <AppSideBar/>
         

        <div className='w-full h-full'>
        <SidebarTrigger className="-ml-1" />
            {children}
        </div>

         </SidebarProvider>
        

    </main>
  )
}

export default Layout