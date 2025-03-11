import React, { useState } from "react";
import avt from "../../../assets/images/sanpham3.jpg";
import { Link } from "react-router-dom";
import { motion, AnimatePresence } from "framer-motion";
import { useTheme } from "../../../Provider/ThemeProvider";
import NavItem from "./NavItem";

const NavAdmin = () => {
  const { isDarkMode } = useTheme();

  return (
    <div className="w-full max-w-[280px] min-w-[260px] flex flex-col gap-[10px] mt-[15px] sticky top-[77px]">
      <div
        className={`w-full flex items-center justify-between  pl-[10px] pr-[5px] py-[8px] rounded-[5px] ${
          isDarkMode
            ? "bg-light-100 text-dark-100"
            : "bg-dark-200 text-light-100"
        }`}
      >
        <div className="flex items-center gap-[5px]">
          <div className="w-[42px] h-[42px] rounded-full ">
            <img
              className="w-full h-full rounded-full object-cover aspect-square"
              src={avt}
              alt=""
            />
          </div>
          <div className="flex flex-col ">
            <h3 className="font-bold text-[0.9rem] font-nunito ">
              Trương Chí Nguyên
            </h3>
            <p className="font-nunito text-[0.8rem]">Quản trị viên cấp 1</p>
          </div>
        </div>
        <div className="w-[32px] h-[32px] flex items-center justify-center cursor-pointer hover:border-[1px] rounded-[5px] text-[1.1rem]">
          <i className="fa-solid fa-ellipsis-vertical"></i>
        </div>
      </div>
      <div
        className={`w-full  flex flex-col pl-[10px]  rounded-[5px] ${
          isDarkMode
            ? "bg-light-100 text-dark-100"
            : "bg-dark-200 text-light-100"
        }`}
      >
        <div className="w-full py-[5px] ">
          <ul className="w-full flex flex-col container-nav-admin overflow-y-auto overflow-x-hidden scrollbar-custom py-[8px] pr-[5px] font-nunito  text-[0.9rem]">
            <li>
              <Link className="flex items-center">
                <div
                  className={`w-full flex items-center gap-[10px] py-[6px] px-[5px] rounded-[5px] ${
                    isDarkMode ? "hover:bg-dark-900" : "hover:bg-light-800"
                  }`}
                >
                  <div
                    className={`w-[30px] h-[30px] flex items-center justify-center text-[0.9rem] rounded-[5px] ${
                      isDarkMode ? " border-[1px]" : ""
                    } `}
                  >
                    <i className="fa-solid fa-house"></i>
                  </div>
                  <span className={`font-nunito font-bold  text-[0.9rem] `}>
                    Trang chủ
                  </span>
                </div>
              </Link>
            </li>
            {navList.map((item) => {
              return <NavItem key={item.id} payload={item} />;
            })}
          </ul>
        </div>
      </div>
    </div>
  );
};

export default NavAdmin;

const navList = [
  {
    id: 1,
    label: "Quản lý người dùng",
    menuId: "user",
    items: [
      { id: 1, label: "Người dùng", href: "/admin/usersmanager" },
      { id: 2, label: "Người bán", href: "/admin/sellersmanager" },
    ],
    icon: "fa-solid fa-user",
  },
  {
    id: 2,
    label: "Quản lý sản phẩm",
    menuId: "products",
    items: [
      { id: 1, label: "Nghành hàng", href: "/admin/categoriesmanager" },
      { id: 2, label: "Sản phẩm", href: "/admin/productsmanager" },
    ],
    icon: "fa-solid fa-clipboard-list",
  },
  {
    id: 3,
    label: "Quản lý sản phẩm",
    menuId: "sss",
    items: [
      { id: 1, label: "Nghành hàng", href: "/admin/usersmanager" },
      { id: 2, label: "Phân phối", href: "/admin/sellersmanager" },
    ],
    icon: "fa-solid fa-clipboard-list",
  },
  {
    id: 4,
    label: "Quản lý sản phẩm",
    menuId: "ssssd",
    items: [
      { id: 1, label: "Nghành hàng", href: "/admin/usersmanager" },
      { id: 2, label: "Phân phối", href: "/admin/sellersmanager" },
    ],
    icon: "fa-solid fa-clipboard-list",
  },
  {
    id: 5,
    label: "Quản lý sản phẩm",
    menuId: "ssssddsds",
    items: [
      { id: 1, label: "Nghành hàng", href: "/admin/usersmanager" },
      { id: 2, label: "Phân phối", href: "/admin/sellersmanager" },
    ],
    icon: "fa-solid fa-clipboard-list",
  },
  {
    id: 6,
    label: "Quản lý sản phẩm",
    menuId: "ssssddsdádsas",
    items: [
      { id: 1, label: "Nghành hàng", href: "/admin/usersmanager" },
      { id: 2, label: "Phân phối", href: "/admin/sellersmanager" },
    ],
    icon: "fa-solid fa-clipboard-list",
  },
];
