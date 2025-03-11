import React from "react";

const CoupouList = ({ coupouList }) => {
  return (
    <div className="flex items-center overflow-auto scrollbar-custom-none gap-[3px] ">
      {coupouList.map((coupou) => (
        <div
          key={coupou.id}
          className=" flex items-center justify-center z-20 bg-[#d81c1c] text-white font-nunito font-bold text-[0.7rem] whitespace-nowrap py-[2px] px-[8px] rounded-[5px] "
        >
          <span>
            {coupou.nameVoucher} -{coupou.treatment}%
          </span>
        </div>
      ))}
    </div>
  );
};

export default CoupouList;
