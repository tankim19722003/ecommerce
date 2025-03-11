import { motion, AnimatePresence } from "framer-motion";
import { useTheme } from "../../../Provider/ThemeProvider";
import { useState } from "react";
import { Link } from "react-router-dom";
const NavItem = ({ payload }) => {
  const { label, menuId, items, icon } = payload;

  const { isDarkMode } = useTheme();
  const [openMenus, setOpenMenus] = useState({ [menuId]: true });

  const toggleDropdown = (menuId) => {
    setOpenMenus((prev) => ({
      ...prev,
      [menuId]: !prev[menuId],
    }));
  };
  return (
    <li className="w-full cursor-pointer">
      <div
        className={`flex items-center rounded-[5px] ${
          isDarkMode ? "hover:bg-dark-900" : "hover:bg-light-800"
        }`}
      >
        <div className="w-full flex items-center gap-[10px] py-[6px] px-[5px]  ">
          <div
            className={`w-[30px] h-[30px] flex items-center justify-center text-[0.9rem] rounded-[5px] ${
              isDarkMode ? " border-[1px]" : ""
            } `}
          >
            <i className={icon}></i>
          </div>
          <span className={`font-nunito font-bold  text-[0.9rem] `}>
            {label}
          </span>
        </div>{" "}
        <div
          className="text-[0.8rem] w-[30px] h-[30px] flex items-center justify-center transition-transform duration-300 cursor-pointer"
          onClick={() => toggleDropdown({ menuId })}
          style={{
            transform: openMenus[{ menuId }]
              ? "rotate(180deg)"
              : "rotate(0deg)",
          }}
        >
          <i className="fa-solid fa-chevron-down"></i>
        </div>
      </div>
      <AnimatePresence>
        {openMenus[{ menuId }] && (
          <motion.ul
            initial={{ height: 0 }}
            animate={{ height: "auto" }}
            exit={{ height: 0 }}
            transition={{ duration: 0.3, ease: "easeInOut" }}
            className="w-full flex flex-col pl-[40px] overflow-hidden"
          >
            {items.map((item) => {
              return (
                <li
                  key={item.id}
                  className={`w-full py-[8px] px-[5px] rounded-[5px] ${
                    isDarkMode ? "hover:bg-dark-900" : "hover:bg-light-800 "
                  }`}
                >
                  <Link to={item.href}>{item.label}</Link>
                </li>
              );
            })}
          </motion.ul>
        )}
      </AnimatePresence>
    </li>
  );
};

export default NavItem;
