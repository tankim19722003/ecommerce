import React from "react";

const Button = ({ onClick, children }) => {
  return (
    <button
      className="w-full py-[8px] bg-primary rounded-[5px] text-white font-bold"
      onClick={onClick}
    >
      {children}
    </button>
  );
};

export default Button;
