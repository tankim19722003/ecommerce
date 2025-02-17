import React from "react";
import { Link } from "react-router-dom";

const PathAccess = () => {
  return (
    <div className="w-full flex items-center justify-center  py-[20px]">
      <div className="w-[90%] flex items-center gap-[5px] overflow-hidden font-nunito font-bold text-[0.9rem]  cursor-pointer text-dark-300">
        <Link to="/">Trang chủ</Link> / <Link to="/cart">giỏ hàng</Link>
      </div>
    </div>
  );
};

export default PathAccess;
