import React from "react";
import { useTheme } from "../../Provider/ThemeProvider";

const SuggestionsSlide = () => {
  const { isDarkMode, toggleTheme } = useTheme();
  const items = [
    "Áo thun nam",
    "Quần jeans nữ",
    "Giày thể thao",
    "Túi xách thời trang",
    "Kính râm",
    "Đồng hồ thông minh",
    "Áo khoác mùa đông",
    "Váy dự tiệc",
    "Giày cao gót",
    "Phụ kiện trang sức",
    "Laptop",
    "Điện thoại thông minh",
    "Tai nghe không dây",
    "Loa Bluetooth",
    "Bàn phím cơ",
    "Chuột gaming",
    "Tủ lạnh",
    "Máy giặt",
    "Nồi chiên không dầu",
    "Máy pha cà phê",
    "Máy hút bụi",
    "Máy massage",
    "Thảm yoga",
    "Bình nước thể thao",
    "Xe đẩy trẻ em",
    "Tã lót",
    "Đồ chơi giáo dục",
    "Ghế ăn dặm",
    "Kem chống nắng",
    "Vitamin tổng hợp",
  ];

  return (
    <div
      className={`w-full flex  items-center border-t-[1px] py-[10px] ${
        isDarkMode
          ? "border-dashed border-border-lgi bg-dark-900 text-black"
          : "border-dashed border-border-dark bg-dark-200 text-white"
      }   `}
    >
      <div className="w-[100%] flex justify-center  overflow-hidden ">
        <div className="w-full flex gap-[20px] suggestions-wrapper animate-slide text-[0.8rem] cursor-pointer">
          {items.map((item, index) => (
            <span
              key={index}
              className={`${
                index % 2 === 0 ? "font-bold" : "font-normal "
              } suggestions-item px-[10px]`}
            >
              {item}
            </span>
          ))}
        </div>
      </div>
    </div>
  );
};

export default SuggestionsSlide;
