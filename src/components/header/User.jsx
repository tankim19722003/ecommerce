import { useContext, useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import avt from "../../assets/images/sanpham3.jpg";

import { useTheme } from "../../Provider/ThemeProvider";
import { useAuth } from "../../contexts/User/AuthContext";
import { motion, AnimatePresence } from "framer-motion";

const User = () => {
  const navigate = useNavigate();
  const {
    authState: { isAuthenticated, user },
    logoutUser,
  } = useAuth();

  const [statusModal, setStatusModal] = useState(false);
  const { isDarkMode } = useTheme();

  const handleChangStatusModalExtention = (event) => {
    event.stopPropagation();
    setStatusModal((prev) => !prev);
  };

  const handleClickOutside = (event) => {
    if (!event.target.closest(".user-menu")) {
      setStatusModal(false);
    }
  };

  const handleLogoutAccount = async () => {
    logoutUser();
  };

  useEffect(() => {
    if (statusModal) {
      document.addEventListener("click", handleClickOutside);
    } else {
      document.removeEventListener("click", handleClickOutside);
    }

    return () => document.removeEventListener("click", handleClickOutside);
  }, [statusModal]);

  return (
    <div className="pc:ml-[20px] mb:px-[10px]">
      {isAuthenticated ? (
        <div
          className="flex items-center gap-[5px] cursor-pointer relative user-menu"
          onClick={handleChangStatusModalExtention}
        >
          <div className="font-nunito font-semibold text-[0.9rem]">
            <span>{user.account}</span>
          </div>
          <div className="w-[32px] h-[32px]">
            <img
              className="w-full h-full max-w-[32px] max-h-[32px] rounded-full object-cover cursor-pointer"
              src={
                user.avatar !== "user.png"
                  ? user.avatar
                  : " https://as1.ftcdn.net/v2/jpg/03/46/83/96/1000_F_346839683_6nAPzbhpSkIpb8pmAwufkC7c5eD7wYws.jpg"
              }
              alt="Ảnh đại diện của người dùng"
            />
          </div>
          <AnimatePresence>
            {statusModal && (
              <motion.div
                initial={{ height: 0 }}
                animate={{ height: "auto" }}
                exit={{ height: 0 }}
                transition={{ duration: 0.3, ease: "easeInOut" }}
                className={`absolute flex top-[100%] right-[0%] shadow-lg rounded-[5px] ${
                  isDarkMode ? "bg-white" : "bg-dark-400"
                } min-w-[170px] z-50 overflow-hidden`}
                onClick={(e) => e.stopPropagation()}
              >
                <ul className="w-full flex flex-col justify-center font-nunito font-light text-[0.87rem]">
                  <li
                    className={`w-full flex items-center py-[6px] px-[10px] ${
                      isDarkMode ? "hover:bg-dark-900 " : "hover:bg-dark-500 "
                    } rounded-[5px]`}
                  >
                    <Link to="/userinfo/account/profile">
                      Thông tin tài khoản
                    </Link>
                  </li>
                  <li
                    className={`w-full flex items-center py-[6px] px-[10px] ${
                      isDarkMode ? "hover:bg-dark-900 " : "hover:bg-dark-500 "
                    } rounded-[5px]`}
                  >
                    <Link to="/userinfo/order">Đơn hàng</Link>
                  </li>
                  <li
                    className={`w-full flex items-center py-[6px] px-[10px] ${
                      isDarkMode ? "hover:bg-dark-900 " : "hover:bg-dark-500 "
                    } rounded-[5px]`}
                  >
                    <Link to="/userinfo/account/address">Địa chỉ</Link>
                  </li>

                  <li
                    className={`w-full flex items-center py-[6px] px-[10px] ${
                      isDarkMode ? "hover:bg-dark-900 " : "hover:bg-dark-500 "
                    } rounded-[5px]`}
                    onClick={handleLogoutAccount}
                  >
                    Đăng xuất
                  </li>
                </ul>
              </motion.div>
            )}
          </AnimatePresence>
        </div>
      ) : (
        <div className="flex items-center text-[0.9rem]">
          <div className="flex items-center pc:justify-end mb:justify-start mb:border-b-[1px] z-10">
            <Link
              to={`/register`}
              className="mb:w-full h-[42px] flex items-center pc:justify-end mb:justify-center pl-[20px] font-nunito font-medium cursor-pointer"
            >
              Đăng ký
            </Link>
            <Link
              to={`/login`}
              className="mb:w-full h-[42px] flex items-center pc:justify-end mb:justify-center pl-[20px] font-nunito font-bold text-primary cursor-pointer"
            >
              Đăng nhập
            </Link>
          </div>
        </div>
      )}
    </div>
  );
};

export default User;
