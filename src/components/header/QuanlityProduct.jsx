import React, { useEffect, useState } from "react";
import { useTheme } from "../../Provider/ThemeProvider";

const QuanlityProduct = ({ defaultQuanlity }) => {
  const { isDarkMode, toggleTheme } = useTheme();
  const [quantity, setQuantity] = useState(defaultQuanlity);

  const handleIncrease = () => setQuantity((prev) => prev + 1);
  const handleDecrease = () => {
    if (quantity > 1) {
      setQuantity((prev) => prev - 1);
    }
  };

  const handleChange = (e) => {
    const value = parseInt(e.target.value, 10);
    if (!isNaN(value) && value >= 1) {
      setQuantity(value);
    }
  };

  console.log(quantity);

  return (
    <div className="w-full h-full flex items-center justify-center  gap-[10px] ">
      <div
        className={`h-full aspect-square flex items-center justify-center rounded-[5px] ${
          isDarkMode ? "bg-white text-dark-100 border-[1px] " : "bg-dark-500"
        } cursor-pointer`}
        onClick={handleDecrease}
      >
        <i className="fa-solid fa-minus"></i>
      </div>

      <div
        className={`w-[100px] h-full flex items-center justify-center px-[20px] rounded-[5px] ${
          isDarkMode ? "bg-white text-dark-100 border-[1px] " : "bg-dark-500"
        }  font-nunito font-bold text-[1rem]`}
      >
        <input
          className="w-full outline-none bg-transparent text-center"
          type="number"
          value={quantity}
          onChange={handleChange}
          style={{
            appearance: "textfield",
            MozAppearance: "textfield",
            WebkitAppearance: "none",
          }}
        />
      </div>

      <div
        className={`h-full aspect-square flex items-center justify-center ${
          isDarkMode ? "bg-white text-dark-100 border-[1px] " : "bg-dark-500"
        } rounded-[5px] cursor-pointer`}
        onClick={handleIncrease}
      >
        <i className="fa-solid fa-plus"></i>
      </div>
    </div>
  );
};

export default QuanlityProduct;
