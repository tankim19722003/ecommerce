import React, { useContext, useEffect, useState } from "react";
import avt from "../../assets/images/sanpham3.jpg";
import { useTheme } from "../../Provider/ThemeProvider";
import { AuthContext, useAuth } from "../../contexts/User/AuthContext";
import UpdateInfoUserModal from "../Modal/UpdateInfoUserModal";
import UpdateImgModal from "../Modal/UpdateImgModal";
import { useLocation, useNavigate } from "react-router-dom";
import { useShop } from "../../contexts/User/ShopContext";

const Profile = () => {
  const { isDarkMode } = useTheme();
  const navigate = useNavigate();
  const location = useLocation();

  const {
    authState: { user },
  } = useAuth();

  const [showUpdateInfoUserModal, setUpdateInfoUserModal] = useState(false);
  const [showUpdateImgModal, setUpdateImgModal] = useState(false);

  const handleOpenUpdateInfoUserModal = () => {
    setUpdateInfoUserModal(true);
    navigate("?popup=updateuserinfo", { replace: true });
  };

  const onCloseUpdateInfoUserModal = () => {
    setUpdateInfoUserModal(false);
    navigate(location.pathname, { replace: true });
  };

  const onCloseUpdateImgModal = () => {
    setUpdateImgModal(false);
  };

  const onOpenUpdateImgModal = () => {
    setUpdateImgModal(true);
  };

  useEffect(() => {
    const searchParams = new URLSearchParams(location.search);
    if (searchParams.get("popup") === "updateuserinfo") {
      setUpdateInfoUserModal(true);
    }
  }, [location.search]);

  return (
    <div className="w-full flex gap-[20px]  ">
      <div
        className={`w-full flex flex-col rounded-[5px] ${
          isDarkMode ? "bg-white text-dark-100" : "bg-dark-200 text-white "
        }`}
      >
        <div className="w-full flex items-center justify-between px-[20px] py-[10px] border-b-[1px] border-dashed ">
          <div className="flex flex-col font-nunito gap-[5px]">
            <h1 className="font-bold text-[1.4rem]">Hồ sơ của tôi</h1>
            <p className="font-normal text-[0.95rem]">
              Quản lý thông tin hồ sơ để bảo mật tài khoản
            </p>
          </div>
          <div className={`font-normal text-[1rem] `}>
            <div
              className="truncate rounded-[5px] flex items-center gap-[5px] text-dark-1000 text-[0.9rem] px-[10px]  py-[5px] bg-primary cursor-pointer"
              onClick={handleOpenUpdateInfoUserModal}
            >
              <span>Cập nhật thông tin</span>
              <i className="fa-solid fa-pen-to-square"></i>
            </div>
          </div>
        </div>
        <div className="w-full  flex   px-[40px] py-[40px] gap-[40px]">
          <div className="w-4/12 flex flex-col  border-dashed">
            <div className="w-full  flex flex-col items-center ">
              <img
                className="w-full h-full max-w-[180px] max-h-[180px] rounded-full object-cover cursor-pointer"
                src={
                  user.avatar !== "user.png"
                    ? user.avatar
                    : " https://as1.ftcdn.net/v2/jpg/03/46/83/96/1000_F_346839683_6nAPzbhpSkIpb8pmAwufkC7c5eD7wYws.jpg"
                }
                alt="Ảnh đại diện của người dùng"
              />{" "}
              <div
                className={`font-nunito font-bold pt-[10px] text-[1.3rem] ${
                  user.fullname ? "text-dark-100 " : " text-dark-400"
                } ${isDarkMode ? "text-dark-100" : "text-dark-1000"}`}
              >
                {user.fullname ? user.fullname : "Chưa cập nhật"}
              </div>
              <div className="w-full flex justify-center mt-[10px]">
                <div
                  className={` px-[10px] py-[5px] border-dashed rounded-[5px] text-[0.7rem] font-nunito   cursor-pointer ${
                    isDarkMode
                      ? "text-dark-100 border-[1px] border-[#3e3e3e] "
                      : "text-dark-1000 border-[1px] border-[#ffffff] "
                  }`}
                  onClick={onOpenUpdateImgModal}
                >
                  <span>Thay đổi ảnh đại diện</span>
                </div>
              </div>
            </div>
          </div>
          <div className="w-8/12 flex flex-col gap-[20px]">
            <div className="w-full flex items-center gap-[40px] font-nunito py-[5px]">
              <div className="w-3/12 font-bold text-[1rem] flex justify-end">
                <span>Tên đăng nhập</span>
              </div>
              <div
                className={`w-8/12 font-normal text-[1rem] ${
                  user?.account ? "text-dark-100 " : " text-dark-400"
                } ${isDarkMode ? "text-dark-100" : "text-dark-1000"}`}
              >
                {user.account ? user.account : "Chưa cập nhật"}
              </div>
            </div>
            <div className="w-full flex items-center gap-[40px] font-nunito py-[5px]">
              <div className="w-3/12 font-bold text-[1rem] flex justify-end">
                <span>Họ và tên</span>
              </div>
              <div
                className={`w-8/12 font-normal text-[1rem] ${
                  user?.account ? "text-dark-100 " : " text-dark-400"
                } ${isDarkMode ? "text-dark-100" : "text-dark-1000"}`}
              >
                {user.fullname ? user.fullname : "Chưa cập nhật"}
              </div>
            </div>

            <div className="w-full flex items-center gap-[40px] font-nunito py-[5px] ">
              <div className="w-3/12 font-bold text-[1rem] flex justify-end">
                <span>Email </span>
              </div>
              <div
                className={`w-8/12 font-normal text-[1rem] ${
                  user.email ? "text-dark-100 " : " text-dark-400"
                } ${isDarkMode ? "text-dark-100" : "text-dark-1000"}`}
              >
                {user?.email ? user.email : "Chưa cập nhật"}
              </div>
            </div>
            <div className="w-full flex items-center gap-[40px] font-nunito py-[5px] ">
              <div className="w-3/12 font-bold text-[1rem] flex justify-end">
                <span>Số điện thoại </span>
              </div>
              <div
                className={`w-8/12 font-normal text-[0.95rem] ${
                  user.phone_number
                    ? "text-dark-100 "
                    : " text-dark-400 text-[1rem]"
                } ${isDarkMode ? "text-dark-100" : "text-dark-1000"}`}
              >
                {user.phone_number}
              </div>
            </div>
            <div className="w-full flex items-center gap-[40px] font-nunito py-[5px] ">
              <div className="w-3/12 font-bold text-[1rem] flex justify-end">
                <span>Giới tính </span>
              </div>
              <div
                className={`w-8/12 font-normal text-[1rem] ${
                  user?.gender !== null ? "text-dark-100 " : " text-dark-400"
                } ${isDarkMode ? "text-dark-100" : "text-dark-1000"}`}
              >
                {user.gender === null
                  ? "Chưa cập nhật"
                  : user.gender === true
                  ? "Nam"
                  : "Nữ"}
              </div>
            </div>
            <div className="w-full flex items-center gap-[40px] font-nunito py-[5px]">
              <div className="w-3/12 font-bold text-[1rem] flex justify-end">
                <span>Ngày sinh </span>
              </div>
              <div
                className={`w-8/12 font-normal text-[1rem] ${
                  user.birth_date ? "text-dark-100 " : " text-dark-400"
                } ${isDarkMode ? "text-dark-100" : "text-dark-1000"}`}
              >
                {user.birth_date
                  ? new Date(user.birth_date).toLocaleDateString("vi-VN")
                  : "Chưa cập nhật"}
              </div>
            </div>
          </div>
        </div>
        {showUpdateInfoUserModal && (
          <UpdateInfoUserModal
            onCloseUpdateInfoUserModal={onCloseUpdateInfoUserModal}
          />
        )}

        {showUpdateImgModal && (
          <UpdateImgModal onCloseUpdateImgModal={onCloseUpdateImgModal} />
        )}
      </div>
    </div>
  );
};

export default Profile;
