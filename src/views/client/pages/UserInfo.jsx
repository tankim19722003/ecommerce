import { useEffect, useState } from "react";
import { useAuth } from "../../../Provider/AuthProvider";
import { Link, Outlet, useLocation, useNavigate } from "react-router-dom";
import HeaderTop from "../../../components/Header/HeaderTop";
import Header from "../../../components/Header/Header";
import { useTheme } from "../../../Provider/ThemeProvider";
import avt from "../../../assets/images/sanpham3.jpg";
import background from "../../../assets/images/sanpham3.jpg";

const UserInfo = () => {
  const { isDarkMode } = useTheme();
  const navigate = useNavigate();
  const { isAuthenticated } = useAuth();
  const location = useLocation();
  const [isOpen, setIsOpen] = useState(true);

  const toggleDropdown = () => {
    setIsOpen(!isOpen);
  };

  const getNavItemClass = (path) =>
    location.pathname === path ? "text-red-500 font-bold" : "";

  useEffect(() => {
    if (!isAuthenticated) {
      navigate("/");
    }
  }, [isAuthenticated, navigate]);

  return (
    <div
      className={`flex flex-col justify-center   ${
        isDarkMode ? "bg-background" : "bg-[#515151]"
      }`}
    >
      <HeaderTop />
      <Header />
      <div className="w-full flex flex-col items-center mt-[30px]  height-screen-minus-header">
        <div className="w-[90%] flex gap-[20px]">
          <div className={`w-3/12 max-w-[340px] flex flex-col gap-[10px]`}>
            <div
              className={`w-full flex items-center justify-between px-[10px] py-[10px]  ${
                isDarkMode ? "bg-white" : "bg-dark-200 text-white"
              } rounded-[5px] `}
            >
              <div className="flex items-center gap-[10px]">
                <img
                  className="w-[50px] h-[50px] max-w-[100px] max-h-[100px] rounded-full object-cover cursor-pointer"
                  src={avt}
                  alt="ảnh đại diện của người dùng"
                />
                <div className="flex flex-col  py-[5px] ">
                  <div className="truncate font-nunito font-bold text-[1rem]  z-10">
                    <span>Trương Chí Nguyên</span>
                  </div>
                </div>
              </div>
              <Link
                to="./account/profile"
                className="px-[6px] py-[2px]  rounded-[5px] cursor-pointer text-[1.2rem] "
              >
                <i className="fa-solid fa-pen-to-square"></i>
              </Link>
            </div>
            <div
              className={`w-full flex  rounded-[5px]  ${
                isDarkMode ? "bg-white" : "bg-dark-200 text-white"
              } rounded-[5px] `}
            >
              <ul className="w-full flex flex-col rounded-[5px]">
                <li className="rounded-[5px] px-[5px] py-[5px]">
                  <Link
                    to="./account/profile"
                    className={`flex items-center justify-between cursor-pointer `}
                    onClick={toggleDropdown}
                  >
                    <div className="w-full flex items-center gap-[5px]">
                      <div className="w-[40px] h-[40px] flex items-center justify-center text-[1.1rem]">
                        <i className="fa-solid fa-user"></i>
                      </div>
                      <span className={`font-nunito text-[0.9rem] `}>
                        Tài khoản của tôi
                      </span>
                    </div>

                    <div
                      className="text-[0.8rem] w-[30px] h-[30px] flex items-center justify-center transition-transform duration-300"
                      style={{
                        transform: isOpen ? "rotate(180deg)" : "rotate(0deg)",
                      }}
                    >
                      <i className="fa-solid fa-chevron-down"></i>
                    </div>
                  </Link>

                  {isOpen && (
                    <ul className="ml-[40px] flex flex-col overflow-hidden transition-all duration-300">
                      <li className="rounded-[5px] px-[10px] py-[5px] ">
                        <Link to="./account/profile">
                          <span
                            className={`font-nunito text-[0.9rem] ${getNavItemClass(
                              "/userinfo/account/profile"
                            )}`}
                          >
                            Hồ sơ
                          </span>
                        </Link>
                      </li>
                      <li className="rounded-[5px] px-[10px] py-[5px] ">
                        <Link to="./account/payment">
                          <span
                            className={`font-nunito text-[0.9rem] ${getNavItemClass(
                              "/userinfo/account/payment"
                            )}`}
                          >
                            Ngân hàng
                          </span>
                        </Link>
                      </li>
                      <li className="rounded-[5px] px-[10px] py-[5px] ">
                        <Link to="./account/address">
                          <span
                            className={`font-nunito text-[0.9rem] ${getNavItemClass(
                              "/userinfo/account/address"
                            )}`}
                          >
                            Địa chỉ
                          </span>
                        </Link>
                      </li>
                    </ul>
                  )}
                </li>
                <li className=" rounded-[5px] px-[5px] py-[5px] ">
                  <Link to="./order" className="flex items-center gap-[5px]">
                    {" "}
                    <div className="w-[40px] h-[40px] flex items-center justify-center text-[1.1rem]">
                      <i className="fa-solid fa-table-list"></i>
                    </div>
                    <span
                      className={`font-nunito text-[0.9rem] ${getNavItemClass(
                        "/userinfo/order"
                      )}`}
                    >
                      Đơn hàng{" "}
                    </span>
                  </Link>
                </li>
                <li className=" rounded-[5px] px-[5px] py-[5px]">
                  <Link to="./voucher" className="flex items-center gap-[5px]">
                    {" "}
                    <div className="w-[40px] h-[40px] flex items-center justify-center text-[1.1rem]">
                      <i className="fa-solid fa-ticket"></i>
                    </div>
                    <span
                      className={`font-nunito text-[0.9rem] ${getNavItemClass(
                        "/userinfo/voucher"
                      )}`}
                    >
                      Voucher
                    </span>
                  </Link>
                </li>
              </ul>
            </div>
          </div>

          <div className={`w-9/12 flex flex-col gap-[10px]`}>
            <div
              className={`w-full flex items-center justify-between px-[20px] py-[10px]  ${
                isDarkMode ? "bg-white" : "bg-dark-200 text-white"
              } rounded-[5px] `}
            >
              <Outlet />
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default UserInfo;
