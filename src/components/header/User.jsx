import React, { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import avt from "../../assets/images/sanpham3.jpg";
import { useAuth } from "../../Provider/AuthProvider";
import { useTheme } from "../../Provider/ThemeProvider";

const User = () => {
  const navigate = useNavigate();
  const { isAuthenticated, logout } = useAuth();
  const [statusModal, setStatusModal] = useState(false);
  const { isDarkMode } = useTheme();

  const handleChangStatusModalExtention = (event) => {
    event.stopPropagation(); // Ngăn chặn sự kiện lan ra ngoài
    setStatusModal((prev) => !prev);
  };

  const handleClickOutside = (event) => {
    if (!event.target.closest(".user-menu")) {
      setStatusModal(false);
    }
  };

  const handleLogoutAccount = async () => {
    await logout();
    navigate(0);
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
    <div className="ml-[20px]">
      {isAuthenticated ? (
        <div
          className="flex items-center gap-[5px] cursor-pointer relative user-menu"
          onClick={handleChangStatusModalExtention}
        >
          <div className="font-nunito font-semibold text-[0.9rem]">
            <span>Trương Chí Nguyên</span>
          </div>
          <div className="w-[32px] h-[32px]">
            <img
              className="w-full h-full aspect-square object-cover rounded-full"
              src={avt}
              alt="Avatar"
            />
          </div>
          {statusModal && (
            <div
              className={`absolute flex top-[100%] right-[0%] shadow-lg rounded-[5px] ${
                isDarkMode ? "bg-white" : "bg-dark-400"
              } min-w-[170px] z-50 transition-all duration-300`}
              onClick={(e) => e.stopPropagation()}
            >
              <ul className="w-full flex flex-col justify-center font-nunito font-light text-[0.87rem]">
                <li
                  className={`w-full flex items-center py-[6px] px-[10px] ${
                    isDarkMode ? "hover:bg-dark-900 " : "hover:bg-dark-500 "
                  } rounded-[5px]`}
                >
                  <Link to="/userinfo">Thông tin tài khoản</Link>
                </li>
                <li
                  className={`w-full flex items-center py-[6px] px-[10px] ${
                    isDarkMode ? "hover:bg-dark-900 " : "hover:bg-dark-500 "
                  } rounded-[5px]`}
                >
                  <span>Đơn hàng của tôi</span>
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
            </div>
          )}
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
