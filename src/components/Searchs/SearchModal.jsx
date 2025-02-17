import React, { useState, useEffect, useRef } from "react";
import { useTheme } from "../../Provider/ThemeProvider";
const SearchModal = ({ onCloseSearchModal }) => {
  const { isDarkMode, toggleTheme } = useTheme();
  const [isVisible, setIsVisible] = useState(false);
  const [category, setCategory] = useState(null);
  const inputRef = useRef(null);

  useEffect(() => {
    setIsVisible(true);
    if (inputRef.current) {
      inputRef.current.focus();
    }
    return () => setIsVisible(false);
  }, []);

  const categories = [
    {
      title: "Sản phẩm công nghệ",
      items: [
        { id: 1, label: "Bàn phím" },
        { id: 2, label: "Chuột" },
        { id: 3, label: "Màn hình" },
        { id: 4, label: "Laptop" },
        { id: 5, label: "Máy tính bàn" },
        { id: 6, label: "Điện thoại" },
        { id: 7, label: "Tablet" },
        { id: 8, label: "Tai nghe" },
        { id: 9, label: "Loa Bluetooth" },
        { id: 10, label: "Ổ cứng SSD" },
        { id: 11, label: "Ổ cứng HDD" },
        { id: 12, label: "Webcam" },
        { id: 13, label: "Router Wi-Fi" },
        { id: 14, label: "Bộ phát sóng 4G" },
        { id: 15, label: "Máy in" },
        { id: 16, label: "Máy quét (Scanner)" },
        { id: 17, label: "Phần mềm diệt virus" },
        { id: 18, label: "Thẻ nhớ" },
        { id: 19, label: "USB" },
        { id: 20, label: "Bàn di chuột (Mouse Pad)" },
        { id: 21, label: "Bàn phím" },
        { id: 22, label: "Chuột" },
        { id: 23, label: "Màn hình" },
        { id: 24, label: "Laptop" },
        { id: 25, label: "Máy tính bàn" },
        { id: 26, label: "Điện thoại" },
        { id: 27, label: "Tablet" },
        { id: 28, label: "Tai nghe" },
        { id: 29, label: "Loa Bluetooth" },
        { id: 30, label: "Ổ cứng SSD" },
        { id: 31, label: "Ổ cứng HDD" },
        { id: 32, label: "Webcam" },
        { id: 33, label: "Router Wi-Fi" },
        { id: 34, label: "Bộ phát sóng 4G" },
        { id: 35, label: "Máy in" },
        { id: 36, label: "Máy quét (Scanner)" },
        { id: 37, label: "Phần mềm diệt virus" },
        { id: 38, label: "Thẻ nhớ" },
        { id: 39, label: "USB" },
        { id: 40, label: "Bàn di chuột (Mouse Pad)" },
      ],
    },
    {
      title: "Sản phẩm thời trang",
      items: [
        { id: 1, label: "Áo thun" },
        { id: 2, label: "Quần jeans" },
        { id: 3, label: "Giày thể thao" },
        { id: 4, label: "Túi xách" },
        { id: 5, label: "Mũ lưỡi trai" },
        { id: 6, label: "Kính râm" },
        { id: 7, label: "Áo khoác" },
        { id: 8, label: "Đầm" },
        { id: 9, label: "Đồng hồ" },
        { id: 10, label: "Phụ kiện trang sức" },
      ],
    },
  ];

  return (
    <div
      onClick={onCloseSearchModal}
      className=" bg-[#0000001e] fixed top-0  right-0 left-0 bottom-0 z-[100] flex flex-col items-center "
    >
      <div className="flex w-full  items-center justify-center py-[38px] tl:px-[10px] ">
        <div
          onClick={(e) => e.stopPropagation()}
          className={`pc:w-[90%] tl:w-full mb:w-[94%]  min-w-[270px] mb:h-full flex flex-col gap-[20px] ${
            isDarkMode ? "bg-[#f2f2f2]" : "bg-dark-200"
          }  py-[20px] px-[20px] rounded-[5px] transition-all duration-500 ${
            isVisible
              ? "translate-y-3 opacity-100"
              : "translate-y-[-20px] opacity-0"
          }`}
        >
          <div className="w-full flex pc:flex-row mb:flex-col items-center gap-[10px]">
            <div
              className={`pc:w-2/12 min-w-[230px] ${
                isDarkMode ? "bg-white text-black" : "bg-[#1f1f1f] text-white"
              }  mb:w-full h-[42px] flex shadow-sm rounded-[5px]`}
            >
              <select
                className={`w-full rounded-[5px] outline-none border-none ${
                  isDarkMode ? "text-black bg-white" : "bg-[#1f1f1f] text-white"
                } font-nunito text-[0.9rem] bg-transparent  `}
              >
                <option className="" value="">
                  Giá
                </option>
                <option value="">Tên</option>
              </select>
            </div>
            <div
              className={`w-full h-[42px] flex items-center gap-[5px] pl-[10px] pr-[3px] py-[2px] ${
                isDarkMode ? "bg-white" : "bg-[#1f1f1f]"
              } shadow-sm rounded-[5px]`}
            >
              <input
                ref={inputRef}
                className="w-full font-nunito font-light text-[0.9rem] outline-none bg-transparent"
                type="text"
                placeholder="Tìm kiếm sản phẩm"
              />
              <button className="w-[100px] h-[32px] flex items-center gap-[3px] outline-none justify-center text-white font-nunito font-medium cursor-pointer bg-[#797979] rounded-[5px]">
                <i className="fa-solid fa-magnifying-glass"></i>
              </button>
            </div>
          </div>
          <div className="w-full  flex pc:flex-row mb:flex-col justify-between gap-[20px] text-black">
            <div
              className={`pc:w-[240px] mb:w-full min-w-[230px] pc:min-h-[400px] mb:min-h-[200px] flex flex-col ${
                isDarkMode ? "bg-white" : "bg-[#1f1f1f] text-white"
              } rounded-[5px] px-[10px] py-[5px]`}
            >
              <h3 className="flex items-center font-nunito font-bold text-[0.9rem]  py-[5px] px-[10px] rounded-[5px]">
                <span>Danh mục sản phẩm</span>
              </h3>
              <ul className="flex flex-col justify-center max-h-[300px] gap-[2px] overflow-auto scrollbar-custom ">
                {categories.map((cat) => (
                  <li
                    key={cat.title}
                    onClick={() => setCategory(cat)}
                    className={`w-full flex items-center gap-[10px] py-[5px] px-[5px] cursor-pointer ${
                      isDarkMode ? "hover:bg-[#efefef]" : "hover:bg-[#2e2e2e]"
                    } rounded-[5px]`}
                  >
                    <i className="text-[0.5rem] fa-solid fa-angle-right"></i>
                    <span className="font-nunito font-semibold text-[0.9rem]">
                      {cat.title}
                    </span>
                  </li>
                ))}
              </ul>
            </div>
            <div className="pc:w-10/12 max-h-[400px] overflow-auto scrollbar-custom mb:w-full flex ">
              <div className="">
                <div className=" flex flex-wrap ml-[20px]">
                  {category &&
                    category.items.map((item) => (
                      <div
                        key={item.id}
                        className={`flex pc:w-3/12 mb:6/12 min-w-[180px] h-[30px] items-center gap-[5px] ${
                          isDarkMode ? "text-[#343434]" : "text-white"
                        } cursor-pointer pr-[20px] mt-[14px]`}
                      >
                        <i className="text-[0.5rem] fa-solid fa-circle"></i>
                        <span className="font-nunito font-bold text-[0.9rem]">
                          {item.label}
                        </span>
                      </div>
                    ))}
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default SearchModal;
