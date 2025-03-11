import { useState } from "react";
import User from "./User";
import Navbar from "./Navbar";
import ModeLayoutButton from "../features/ModeLayoutButton";
import { useTheme } from "../../Provider/ThemeProvider";
import { Link } from "react-router-dom";
import logo from "../../assets/images/logo_snapbuy.png";

const HeaderTop = () => {
  const { isDarkMode } = useTheme();
  const [toggleShowNav, setToggleShowNav] = useState(false);

  const handletoggleShowNav = () => {
    setToggleShowNav(!toggleShowNav);
  };

  const navItems = [
    { label: "Kênh người bán", href: "/shopdashboard" },
    { label: "Dịch vụ vận chuyển", href: "#" },
    { label: "Điều khoản dịch vụ", href: "#" },
  ];
  return (
    <div
      className={`w-full flex   items-center justify-center mb:py-[5px] ${
        isDarkMode
          ? "bg-white text-black  border-b-[1px] border-dashed border-border-light"
          : "bg-dark-200 text-white  border-b-[1px] border-dashed border-border-dark "
      }`}
    >
      <div className="w-full px-[10px] pc:hidden mb:flex items-center justify-between text-[1.3rem] ">
        <Link to="/" className=" flex items-center gap-[5px]" href="/">
          <img className="w-[26px] object-contain" src={logo} alt="" />
          <div className="font-jersey15 font-black text-[1.5rem] mt-[5px]">
            <span className="text-primary">SNAP</span>
            <span>BUY</span>
          </div>
        </Link>
        <div className="flex items-center gap-[10px]">
          <ModeLayoutButton />{" "}
          <div
            className="flex items-center justify-center px-[8px] py-[6px] rounded-[5px] border-[1px]"
            onClick={handletoggleShowNav}
          >
            <i className="fa-solid fa-bars"></i>
          </div>
        </div>
      </div>
      <div
        className={`pc:w-[90%] ${
          toggleShowNav ? " flex" : "hidden"
        } mb:w-full pc:h-[38px] pc:flex items-center  mb:fixed z-30 mb:bg-[#cccccc5a] top-0 bottom-0 `}
      >
        <div className="flex pc:w-full  pc:flex-row pc:justify-between pc:items-center  mb:flex-col-reverse mb:justify-end mb:items-start mb:w-[60%] mb:min-w-[260px]  mb:h-[100vh] mb:fixed  mb:top-0 mb:left-0   mb:bg-white z-50  mb:border-l-[1px] mb:border-[#ccc]">
          <ul className=" mb:w-full flex pc:flex-row mb:flex-col items-center   pc:gap-[20px] mb:gap-[10px] font-nunito  text-[0.85rem] ">
            <li className=" group relative  mb:w-full mb:px-[10px] mb:py-[5px]  ">
              <a
                to="salesregistation"
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
          <div className=" mb:w-full flex mb:flex-col-reverse mb:items-start   items-center justify-end">
            <div className="mb:hidden">
              <ModeLayoutButton />
            </div>
            <div className="mb:py-[10px]">
              {" "}
              <User />
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default HeaderTop;
