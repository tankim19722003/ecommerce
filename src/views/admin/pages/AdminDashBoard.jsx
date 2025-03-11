import NavAdmin from "../../../components/Admin/Nav/NavAdmin";
import LayoutModeBackground from "../../client/layout/LayoutModeBackground";
import ModeLayoutButton from "../../../components/features/ModeLayoutButton";
import logo from "../../../assets/images/logo_snapbuy.png";
import { useState } from "react";
import { Outlet } from "react-router-dom";
import { useTheme } from "../../../Provider/ThemeProvider";

const AdminDashBoard = () => {
  const { isDarkMode } = useTheme();
  return (
    <LayoutModeBackground>
      <div className="w-full flex flex-col items-center justify-center ">
        <div
          className={`w-full flex sticky top-0 items-center justify-center  ${
            isDarkMode
              ? "bg-light-100 border-b-[1px] border-dashed  border-light-200"
              : "bg-dark-200 text-light-100"
          }`}
        >
          <div className="w-[98%] h-[60px] flex justify-between items-center ">
            <div className=" flex items-center gap-[10px] mb-[5px]">
              <div className="flex items-center gap-[5px] ">
                <img className="w-[30px] object-contain" src={logo} alt="" />
                <div className="font-jersey15 font-black text-[1.5rem] mt-[10px]">
                  <span className="text-primary">SNAP</span>
                  <span>BUY</span>
                </div>
              </div>
              <div className="font-nunito font-semibold text-[1.1rem] mt-[10px] mb:hidden">
                Admin manager
              </div>
            </div>
            <div className="">
              <ModeLayoutButton />
            </div>
          </div>
        </div>
        <div className="w-[98%] flex flex-col items-center ">
          <div className=" w-full flex    gap-[20px] ">
            <div className="w-3/12 max-w-[280px] min-w-[260px]">
              <NavAdmin />
            </div>
            <div className="flex-1 mt-[20px] h-[2000px]">
              <Outlet />
            </div>
          </div>
        </div>
      </div>
    </LayoutModeBackground>
  );
};

export default AdminDashBoard;
