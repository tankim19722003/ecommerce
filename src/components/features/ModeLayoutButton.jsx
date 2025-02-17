import React from "react";
import { useTheme } from "../../Provider/ThemeProvider";

const ModeLayoutButton = () => {
  const { isDarkMode, toggleTheme } = useTheme();

  return (
    <div
      onClick={toggleTheme}
      className="w-[28px] h-[28px] flex items-center justify-center border-[1px] border-[#767676]  rounded-full cursor-pointer transition-all duration-300"
    >
      {isDarkMode ? (
        <i className="fa-solid fa-moon"></i>
      ) : (
        <i className="fa-solid fa-sun"></i>
      )}
    </div>
  );
};

export default ModeLayoutButton;
