import React from "react";
import User from "./User";
import Navbar from "./Navbar";
import ModeLayoutButton from "../features/ModeLayoutButton";
import { useTheme } from "../../Provider/ThemeProvider";

const HeaderTop = () => {
  const { isDarkMode, toggleTheme } = useTheme();

  const navItems = [
    { label: "Mở gian hàng bán lẻ", href: "#" },
    { label: "Dịch vụ vận chuyển", href: "#" },
    { label: "Điều khoản dịch vụ", href: "#" },
  ];
  return (
    <div
      className={`w-full flex  mb:hidden items-center justify-center ${
        isDarkMode
          ? "bg-white text-black  border-b-[1px] border-dashed border-border-light"
          : "bg-dark-200 text-white  border-b-[1px] border-dashed border-border-dark "
      }`}
    >
      <div className="pc:w-[90%]  mb:w-full pc:h-[38px] pc:flex items-center  mb:fixed z-30 mb:bg-[#cccccc5a] top-0 bottom-0">
        <div className="flex pc:w-full  pc:flex-row pc:justify-between pc:items-center  mb:flex-col-reverse mb:justify-end mb:items-start mb:w-[50%] mb:min-w-[240px]  mb:h-[100vh] mb:fixed  mb:top-0 mb:right-0   mb:bg-white z-50  mb:border-l-[1px] mb:border-[#ccc]">
          <ul className=" mb:w-full flex pc:flex-row mb:flex-col items-center   pc:gap-[20px] mb:gap-[10px] font-nunito  text-[0.85rem] ">
            <li className=" group relative  mb:w-full mb:px-[10px] mb:py-[5px]  ">
              <a
                className="hover:text-primary transition-all duration-300"
                href=""
              >
                Mở gian hàng chủa bạn
              </a>
              <Navbar items={navItems} />
            </li>
            <li className=" group relative  mb:w-full mb:px-[10px] mb:py-[5px] transition-all duration-300">
              <a className="hover:text-primary" href="">
                Trung tâm trợ giúp
              </a>
              <Navbar items={navItems} />
            </li>
            <li className="group relative  mb:w-full mb:px-[10px] mb:py-[5px] text-blac transition-all duration-300">
              <a className="hover:text-primary" href="">
                Điều khoản dịch vụ
              </a>
              <Navbar items={navItems} />
            </li>
          </ul>
          <div className=" flex items-center justify-end">
            <ModeLayoutButton />
            <User />
          </div>
        </div>
      </div>
    </div>
  );
};

export default HeaderTop;
