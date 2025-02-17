import React from "react";

const Rank = ({ score }) => {
  console.log(score);
  let rank = "";
  let colorClass = "";
  let rankIcon = null;

  if (score <= 0) {
    rank = "E ";
    colorClass = "text-[#c77727]";
    rankIcon = <i className="fa-solid fa-medal"></i>;
  } else if (score > 0 && score <= 200) {
    rank = "D";
    colorClass = "text-gray-500";
    rankIcon = <i className="fa-solid fa-star"></i>;
  } else if (score > 200 && score <= 800) {
    rank = "C";
    colorClass = "text-yellow-500";
    rankIcon = <i className="fa-solid fa-crown"></i>;
  } else if (score > 800 && score <= 2000) {
    rank = "B";
    colorClass = "text-[#35e3e1]";
    rankIcon = <i className="fa-solid fa-diamond"></i>;
  } else if (score > 2000 && score <= 5000) {
    rank = "A";
    colorClass = "text-[#6230ed]";
    rankIcon = <i className="fa-regular fa-gem"></i>;
  } else {
    rank = "S";
    colorClass = "text-[#ff3d3d]";
    rankIcon = <i className="fa-solid fa-trophy"></i>;
  }

  return (
    <div className={`flex items-center gap-[5px] text-[0.9rem] ${colorClass}`}>
      {rankIcon}
      <span className="font-bold font-nunito">{rank} rank</span>
    </div>
  );
};

export default Rank;
