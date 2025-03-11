import { useTheme } from "../../../Provider/ThemeProvider";
import { useContext, useEffect, useState } from "react";
import { useNavigate, Link } from "react-router-dom";
import logo_snapbuy from "../../../assets/images/logo_snapbuy.png";
import logo_google from "../../../assets/images/logo_google.png";
import logo_facebook from "../../../assets/images/logo_facebook.png";

import { useAuth } from "../../../contexts/User/AuthContext";
import Loading from "../../../views/client/pages/Loading";
import {
  notifySuccess,
  notifyWarning,
  notifyError,
} from "../../../utils/client/Notify";
import { ToastContainer } from "react-toastify";
import HeaderFlexibleView from "../../../components/Header/HeaderFlexibleView";
import ModeLayoutButton from "../../../components/features/ModeLayoutButton";

const AdminLogin = () => {
  const { isDarkMode } = useTheme();
  const [showPass, setShowPass] = useState(false);
  const changeStatusShowPass = () => {
    setShowPass(!showPass);
  };
  return (
    <div
      className={`w-full h-[100vh] flex flex-col items-center ${
        isDarkMode ? "bg-background text-dark-100" : "bg-dark-200 text-white"
      }`}
    >
      <div
        className={`w-full flex items-center justify-center ${
          isDarkMode
            ? "bg-light-100 text-dark-100 border-b-[1px] border-dark-700 shadow-sm"
            : "bg-dark-200 text-light-100 border-b-[1px] border-dark-400"
        }`}
      >
        <div className={`w-[90%] h-[60px] flex items-center justify-between `}>
          <div className="flex items-center gap-[10px] mb-[8px] ">
            <div className="flex items-center gap-[5px] ">
              <img className="w-[30px]" src={logo_snapbuy} alt="" />
              <div className="font-jersey15 font-black text-[1.5rem] mt-[10px]">
                <span className="text-primary">Snap</span>
                <span className="">Buy</span>
              </div>
            </div>
            <div className="flex items-centerfont-nunito font-nunito font-bold text-[1.1rem] mt-[15px]">
              Đăng nhập quản trị viên
            </div>
          </div>
          <div className="">
            <ModeLayoutButton />
          </div>
        </div>
      </div>
      <ToastContainer />
      <div className="w-full container-minus-headerflexible flex flex-col items-center justify-center ">
        {" "}
        <div
          className={`w-[30%] max-w-[420px] min-w-[340px] tl:min-w-[400px]  rounded-[5px] ${
            isDarkMode ? "bg-white border-[1px]" : "bg-dark-400"
          } py-[20px] px-[20px]`}
        >
          <Link to="/" className="w-full flex  items-center gap-[10px] ">
            <img className="w-[30px]" src={logo_snapbuy} alt="" />
            <div className="flex items-centerfont-nunito font-extrabold text-[1.3rem] mt-[5px]">
              {" "}
              <span className="text-primary">Snap</span>
              <span className="">Buy</span>
            </div>
          </Link>
          <div className="w-full flex flex-col py-[20px] pb-[10px]">
            <h1 className="text-[1.5rem] font-nunito font-bold ">
              Đăng nhập tài khoản quản trị
            </h1>
          </div>
          <div className="flex flex-col mt-[20px] gap-[20px]">
            <div
              className={`w-full flex items-center ${
                isDarkMode ? "border-[1px] bg-light-100" : "bg-dark-200"
              } rounded-[5px] `}
            >
              <div className="p-[10px] flex items-center justify-center">
                <i className="fa-solid fa-user"></i>
              </div>
              <input
                className="w-full bg-transparent outline-none px-[0px] py-[10px] rounded-[5px] text-[0.9rem]"
                type="text"
                name="phoneOrAccount"
                placeholder="Nhập tài khoản"
              />
            </div>
            <div
              className={`w-full flex items-center ${
                isDarkMode ? "border-[1px] bg-light-100" : "bg-dark-200"
              } rounded-[5px] `}
            >
              <div className="p-[10px] flex items-center justify-center">
                <i className="fa-solid fa-lock"></i>
              </div>
              <input
                className="w-full outline-none bg-transparent px-[0px] py-[10px] rounded-[5px] text-[0.9rem]"
                type={`${showPass ? "text" : "password"}`}
                placeholder="Nhập mật khẩu"
                name="password"
              />
              <div
                className="p-[10px] flex items-center justify-center cursor-pointer"
                onClick={changeStatusShowPass}
              >
                {showPass ? (
                  <i className="fa-regular fa-eye-slash"></i>
                ) : (
                  <i className="fa-regular fa-eye"></i>
                )}
              </div>
            </div>
          </div>
          <button className="w-full outline-none px-[10px] py-[8px] rounded-[5px]  text-[1rem] text-white font-bold bg-primary my-[20px]">
            Đăng nhập
          </button>
        </div>
      </div>
    </div>
  );
};

export default AdminLogin;
