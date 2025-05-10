"use client";

import { menuList } from "@/utils/menulist";
import { usePathname, useRouter } from "next/navigation";
import { Button } from "@/components/ui/button";
import { X } from "lucide-react";
import React from "react";

interface Props {
  openMenu: boolean;
  setOpenMenu: React.Dispatch<React.SetStateAction<boolean>>;
}

export function AppSideBar({ openMenu, setOpenMenu }: Props) {
  const path = usePathname();
  const router = useRouter();

  return (
    <>
      {/* Sidebar */}
      <div
        className={`fixed top-0 left-0 h-full w-[200px] bg-primary text-white shadow-lg z-50 transform transition-transform duration-300  ${
          openMenu ? "translate-x-0" : "-translate-x-full"
        }`}
      >
        <div className="w-full flex justify-between items-center p-4 border-b border-white/10">
          <h2 className="text-[22px] font-semibold text-center">Mens Cloth</h2>
          <Button
            size="icon"
            variant="ghost"
            onClick={() => setOpenMenu(false)}
            className="text-white hover:bg-white/10"
          >
            <X className="w-5 h-5" />
          </Button>
        </div>

        <div className="p-2 space-y-2">
          {menuList?.map((item, index) => (
            <Button
              key={index}
              onClick={() => router.push(item?.to)}
              variant="ghost"
              className={`w-full justify-start gap-3 px-3 py-2 text-white cursor-pointer ${
                path === item.to ? "bg-white/10" : ""
              }`}
            >
              {item?.icon && <item.icon className="w-5 h-5" />}
              <span>{item.name}</span>
            </Button>
          ))}
        </div>
      </div>
    </>
  );
}
