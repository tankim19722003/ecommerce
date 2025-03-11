import React from "react";
import { Link } from "react-router-dom";

const RegisterSuccess = () => {
  return (
    <div className="w-full container-minus-headerflexible flex flex-col items-center justify-center ">
      <div className="pc:w-[30%]  pc:max-w-[440px] mb:w-full flex flex-col items-center justify-between bg-white rounded-[5px] pt-[10px] pb-[20px] ">
        {" "}
        <div className="w-full flex flex-col items-center py-[20px] px-[20px]">
          <h1 className="font-nunito font-black text-[1.9rem] text-green-500">
            Đăng ký shop thành công
          </h1>
          <div className="flex flex-col items-center justify-center">
            <div className="text-[5rem] text-green-500">
              <i className="fa-regular fa-circle-check"></i>
            </div>
          </div>
          <div className="w-full px-[20px] py-[10px] flex flex-col items-center justify-center gap-[5px] ">
            <p className="font-nunito font-medium text-[1rem] mt-[5px] text-dark-400 text-center">
              Yêu cầu đăng ký của bạn đang được quản trị viên xem xét vui lòng
              đợi kết quả
            </p>
          </div>
        </div>
        <div className="w-full px-[20px]">
          <Link
            to="/"
            className="w-full flex items-center justify-center py-[10px] bg-primary rounded-[5px] text-light-100 font-bold font-nunito mt-[10px]"
          >
            <span>Về trang chủ</span>
          </Link>
        </div>
      </div>
    </div>
  );
};

export default RegisterSuccess;
