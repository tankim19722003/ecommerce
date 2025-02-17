import React from "react";
import { useTheme } from "../../Provider/ThemeProvider";

const AttributeProduct = () => {
  const { isDarkMode, toggleTheme } = useTheme();
  return (
    <div className=" flex items-center justify-center gap-[5px]">
      <div
        className={`  px-[10px] py-[3px] rounded-[5px] font-nunito font-medium text-[0.9rem] ${
          isDarkMode
            ? "bg-white border-[1px] text-dark-100"
            : "bg-dark-500 text-white"
        }`}
      >
        Combo 10 gói
      </div>
    </div>
  );
};

export default AttributeProduct;
