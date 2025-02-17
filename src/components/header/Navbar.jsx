import React from "react";
import { useTheme } from "../../Provider/ThemeProvider";

const Navbar = ({ navId, items }) => {
  const { isDarkMode, toggleTheme } = useTheme();
  return (
    <div
      id={navId}
      className={`peseudo-element absolute hidden group-hover:flex   flex-col top-[120%] pc:left-[-5%] mb:left-0 ${
        isDarkMode ? "bg-white" : "bg-[#454545]"
      } shadow-lg rounded-[5px] font-nunito font-medium z-50 `}
    >
      <ul>
        {items.map((item, index) => (
          <li
            key={index}
            className={`py-[8px] pl-[10px] pr-[15px] mb:pl-[20px] whitespace-nowrap  ${
              isDarkMode ? "hover:bg-[#e1e1e1]" : "hover:bg-[#646464]"
            } transition-colors duration-200`}
          >
            <a className="flex items-center gap-[5px]" href={item.href}>
              <i className="mb-[2px] text-[0.5rem] fa-solid fa-angle-right"></i>
              <span>{item.label}</span>
            </a>
          </li>
        ))}
      </ul>
    </div>
  );
};

export default Navbar;
