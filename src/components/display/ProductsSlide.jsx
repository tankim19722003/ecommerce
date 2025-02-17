import React, { useEffect, useRef } from "react";
import sanpham1 from "../../assets/images/sanpham1.webp";
import sanpham2 from "../../assets/images/sanpham2.webp";
import sanpham3 from "../../assets/images/sanpham3.jpg";
import { useTheme } from "../../Provider/ThemeProvider";
import { Link } from "react-router-dom";

const ProductsSlide = () => {
  const { isDarkMode, toggleTheme } = useTheme();
  const scrollContainerRef = useRef(null);

  const scrollLeft = () => {
    if (scrollContainerRef.current) {
      const container = scrollContainerRef.current;
      const itemWidth = container.scrollWidth / items.length;
      const scrollAmount = itemWidth;

      if (container.scrollLeft === 0) return;

      container.scrollBy({
        left: -scrollAmount,
        behavior: "smooth",
      });
    }
  };

  const scrollRight = () => {
    if (scrollContainerRef.current) {
      const container = scrollContainerRef.current;
      const itemWidth = container.scrollWidth / items.length;
      const scrollAmount = itemWidth;

      // Add a small tolerance margin to ensure proper reset
      if (
        Math.ceil(container.scrollLeft + container.clientWidth) >=
        container.scrollWidth
      ) {
        container.scrollTo({
          left: 0,
          behavior: "smooth",
        });
        return;
      }

      container.scrollBy({
        left: scrollAmount,
        behavior: "smooth",
      });
    }
  };

  useEffect(() => {
    const interval = setInterval(() => {
      scrollRight();
    }, 3000);

    return () => clearInterval(interval);
  }, []);

  return (
    <div className="w-full flex items-center justify-center">
      <div className="relative pc:w-[90%] tl:w-full mb:w-full pc:py-[30px] tl:py-[30px] tl:px-[10px] mb:py-[20px]">
        <div className="w-full">
          <button
            onClick={scrollLeft}
            className="absolute w-[36px] h-[36px] flex items-center justify-center pc:left-[-20px] tl:left-[5px]  mb:left-[2px] top-1/2  transform -translate-y-1/2 bg-[#2b2b2b41] text-white p-2 rounded-full z-10"
          >
            <i className="fa-solid fa-chevron-left"></i>
          </button>
          <div
            ref={scrollContainerRef}
            className="w-full flex items-center pc:justify-start tl:justify-start  overflow-auto scrollbar-custom-none   mb:px-[10px]  gap-[20px] scroll-smooth "
          >
            {items.map((item) => (
              <div
                key={item.id}
                className={`pc:w-4/12  pc:min-w-[400px] tl:w-6/12 tl:min-w-[400px]  mb:w-full  mb:min-w-full flex items-center  gap-[10px]  hover:translate-y-[-4px] hover:translate-x-[-4px] transition-all duration-200 my-[10px] ${
                  isDarkMode ? "bg-white text-black" : "bg-dark-200 text-white"
                } shadow-md p-[10px] rounded-[5px]  cursor-pointer`}
              >
                <img
                  className=" w-[110px] h-[110px] object-cover aspect-square rounded-[5px]"
                  src={item.thumbnail[0]}
                  alt=""
                />
                <div className="h-full py-[5px] flex-1">
                  <h3 className="w-full h-[50px] font-nunito font-bold   line-clamp-2 ">
                    {item.nameProduct}
                  </h3>
                  <span className="my-[10px] font-bold text-[1rem] text-[#d81f1f] py-[5px]">
                    {item.price.toLocaleString("vi-VN")}đ
                  </span>
                  <div className="flex items-center justify-between">
                    <div className="flex  items-center overflow-auto scrollbar-custom-none gap-[3px] ">
                      <div className=" flex items-center justify-center z-20 bg-[#d81c1c] text-white font-nunito font-bold text-[0.8rem] whitespace-nowrap py-[2px] px-[8px] rounded-[5px] ">
                        <span>Sale -{item.discount}%</span>
                      </div>
                      <div className=" flex items-center justify-center z-20 bg-[#d81c1c] text-white font-nunito font-bold text-[0.8rem] whitespace-nowrap py-[2px] px-[8px] rounded-[5px] ">
                        <span>Free ship</span>
                      </div>
                    </div>
                    <Link
                      to={`product/${item.id}`}
                      className={`w-[28px] h-[28px] rounded-[5px]  ${
                        isDarkMode
                          ? "bg-white text-black "
                          : "bg-dark text-white "
                      } flex items-center justify-center shadow-md text-[1rem] `}
                    >
                      <i className="fa-solid fa-arrow-up-right-from-square"></i>
                    </Link>
                  </div>
                </div>
              </div>
            ))}
          </div>
          <button
            onClick={scrollRight}
            className="absolute w-[36px] h-[36px] flex items-center justify-center pc:right-[-20px] tl:right-[5px] mb:right-[2px] top-1/2 transform -translate-y-1/2 bg-[#2b2b2b41] text-white  p-2 rounded-full"
          >
            <i className="fa-solid fa-chevron-right"></i>
          </button>
        </div>
      </div>
    </div>
  );
};

const items = [
  {
    id: 1,
    thumbnail: [sanpham1, sanpham2],
    nameShop: "Mozzi",
    shopTag: "Shop thú cưng",
    nameProduct: "Combo 10 gói cá tuyết sấy nhà Mozzi",
    price: 240000,
    discount: 50,
    sales: 999,
    avatarShop: sanpham3,
  },
  {
    id: 2,
    thumbnail: [sanpham2, sanpham3],
    nameShop: "Pet Paradise",
    shopTag: "Dịch vụ thú cưng",
    nameProduct: "Thức ăn cho chó vị gà nhập khẩu",
    price: 150000,
    discount: 30,
    sales: 200,
    avatarShop: sanpham1,
  },
  {
    id: 3,
    thumbnail: [sanpham3, sanpham1],
    nameShop: "Cún Cưng",
    shopTag: "Phụ kiện thú cưng",
    nameProduct: "Dây dắt chó bền đẹp, chống rối",
    price: 80000,
    discount: 20,
    sales: 150,
    avatarShop: sanpham2,
  },
  {
    id: 4,
    thumbnail: [sanpham1, sanpham3],
    nameShop: "Meow Meow Store",
    shopTag: "Thức ăn mèo",
    nameProduct: "Hạt dinh dưỡng cao cấp cho mèo",
    price: 300000,
    discount: 25,
    sales: 350,
    avatarShop: sanpham1,
  },
  {
    id: 5,
    thumbnail: [sanpham2, sanpham1],
    nameShop: "Happy Paws",
    shopTag: "Phụ kiện & Thức ăn",
    nameProduct: "Ổ nằm cho thú cưng bằng vải cotton",
    price: 500000,
    discount: 40,
    sales: 99,
    avatarShop: sanpham3,
  },
  {
    id: 6,
    thumbnail: [sanpham3, sanpham2],
    nameShop: "Animal Lover",
    shopTag: "Dụng cụ vệ sinh",
    nameProduct: "Hộp cát vệ sinh cho mèo tự động",
    price: 1200000,
    discount: 10,
    sales: 20,
    avatarShop: sanpham2,
  },
  {
    id: 7,
    thumbnail: [sanpham1, sanpham2],
    nameShop: "Choco Pet",
    shopTag: "Shop chó mèo",
    nameProduct: "Dầu tắm dưỡng lông hương hoa cỏ",
    price: 180000,
    discount: 15,
    sales: 400,
    avatarShop: sanpham3,
  },
];

export default ProductsSlide;
