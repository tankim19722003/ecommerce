import React from "react";

const Nav = () => {
  return (
    <div className="flex-1 flex items-center mt-[10px] ml-[20px] mb:hidden tl:hidden">
      <ul className="w-full flex items-center gap-[30px] font-nunito font-bold whitespace-nowrap">
        <li>
          <a href="">
            <span className="text-[#f12b2b]">Flash</span>
            <span className="text-[#eeab2e]">sale</span>
          </a>
        </li>
        <li>
          <a href="">
            <span className="text-[#2f4cf0] ">Cao cấp</span>
          </a>
        </li>
        <li>
          <a href="">
            <span className="text-[#eeab2e]">Review</span>
          </a>
        </li>
        <li>
          <a href="">
            <span className="text-[#eeab2e]">Săn </span>
            <span className="text-[#eeab2e]">Voucher</span>
          </a>
        </li>
      </ul>
    </div>
  );
};

export default Nav;
