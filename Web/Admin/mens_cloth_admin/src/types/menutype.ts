import React, { JSX } from "react";

export interface MenuType {
    name: string;
    to: string;
    isActive: string;
    icon:React.FC<React.SVGProps<SVGSVGElement>>;
    accessList: string[];
}
