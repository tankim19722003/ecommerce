import React from "react";
import PropTypes from "prop-types";
import { useTheme } from "../../Provider/ThemeProvider";
import InfoShop from "./InfoShop";
import Rank from "../features/Rank";

import CoupouList from "../features/CoupouList";
import { Link } from "react-router-dom";

const Card = ({ cardContent }) => {
  const { isDarkMode, toggleTheme } = useTheme();

  return (
    <div
      className={`card  min-w-[160px] pc:mt-[20px]  mb:mt-[10px]  pc:ml-[20px]  mb:ml-[10px] flex flex-col rounded-[10px] shadow-md relative group ${
        isDarkMode ? "bg-white text-black" : "bg-dark-300 text-white"
      }`}
    >
      <div className=" flex overflow-hidden rounded-[10px]">
        <div className="w-full flex  transition-transform duration-300 ease-in-out hover:translate-x-[-100%] rounded-[10px]">
          <img
            className="aspect-square z-10 w-full object-cover rounded-[10px]"
            src={cardContent.thumbnail[0]}
            alt=""
          />
          <img
            className="aspect-square w-full object-cover rounded-[10px]"
            src={cardContent.thumbnail[1]}
            alt=""
          />
        </div>
      </div>

      <div className="w-full flex flex-col ">
        <Link to={`/product/${cardContent.id}`} className="w-full flex-col">
          <div className="w-full h-[50px]  flex my-[5px] px-[10px] cursor-pointer">
            <h3
              className={`font-bold ${
                isDarkMode ? "text-[#373737]" : "text-white"
              }  line-clamp-2`}
            >
              {cardContent.nameProduct}
            </h3>
          </div>
          <div className="w-full flex items-center gap-[10px] py-[0px] px-[10px] cursor-pointer">
            {(() => {
              const discountVoucher = cardContent.coupous.find(
                (coupou) => coupou.tag === "discount"
              );
              console.log(discountVoucher);

              const discountedPrice = discountVoucher
                ? cardContent.price * (1 - discountVoucher.treatment / 100)
                : cardContent.price;

              return (
                <>
                  <span className="font-bold pc:text-[1.1rem] tl:text-[1rem] mb:text-[1rem] text-[#ee2f2f]">
                    {discountedPrice.toLocaleString("vi-VN")}đ
                  </span>

                  {discountVoucher && (
                    <span
                      className={`font-bold pc:text-[0.9rem] tl:text-[0.8rem] mb:text-[0.8rem] line-through ${
                        isDarkMode ? "text-[#292929]" : "text-white"
                      }`}
                    >
                      {cardContent.price.toLocaleString("vi-VN")}đ
                    </span>
                  )}
                </>
              );
            })()}
          </div>
          <div className="w-full  flex  items-center justify-between my-[5px] px-[10px] cursor-pointer">
            <span
              className={`font-normal pc:text-[0.8rem] tl:text-[0.7rem] mb:text-[0.7rem]   whitespace-nowrap  ${
                isDarkMode ? "text-[#292929]" : "text-white"
              }`}
            >
              {cardContent.sales >= 10000
                ? (cardContent.sales / 10000).toFixed(1) + "k"
                : cardContent.sales >= 1000
                ? (cardContent.sales / 1000).toFixed(1) + "k"
                : cardContent.sales}{" "}
              đã bán
            </span>
          </div>
        </Link>
        <div
          className={`w-full flex items-center justify-between gap-[5px] border-t-[1px] ${
            isDarkMode ? "border-[#ccc] " : "border-border-dark"
          } border-dashed px-[10px] py-[10px]`}
        >
          <CoupouList coupouList={cardContent.coupous} />
        </div>
      </div>

      <div className="absolute flex flex-col gap-[5px] top-[2%] right-[2%] opacity-0 translate-x-[20px] group-hover:opacity-100 group-hover:translate-x-0  transition-all duration-300 ease-in-out z-10  ">
        <div
          className={`w-[34px] h-[34px]  flex items-center justify-center ${
            isDarkMode
              ? " bg-white text-[#4b4b4b] hover:text-[#F8AF24]"
              : "bg-[#20202074] text-white"
          } font-nunito text-[1rem]   transition-all duration-300 ease-in-out rounded-full cursor-pointer `}
        >
          <i className="mt-[2px] mr-[2px] fa-solid fa-cart-plus"></i>
        </div>
        <Link
          to={`/product/${cardContent.id}`}
          className={`w-[34px] h-[34px]  flex items-center justify-center ${
            isDarkMode
              ? " bg-white text-[#4b4b4b] hover:text-[#4b7dfb]"
              : "bg-[#20202074] text-white"
          } font-nunito text-[1rem]   transition-all duration-300 ease-in-out rounded-full cursor-pointer `}
        >
          <i className="fa-solid fa-circle-info"></i>
        </Link>
        <div
          className={`w-[34px] h-[34px]  flex items-center justify-center ${
            isDarkMode
              ? " bg-white text-[#4b4b4b] hover:text-[#f04646]"
              : "bg-[#20202074] text-white"
          } font-nunito text-[1rem] transition-all duration-300 ease-in-out rounded-full cursor-pointer `}
        >
          <i className=" fa-solid fa-heart"></i>
        </div>
      </div>
    </div>
  );
};

Card.propTypes = {
  cardContent: PropTypes.shape({
    thumbnail: PropTypes.arrayOf(PropTypes.string).isRequired,
    infoShop: PropTypes.arrayOf(PropTypes.string).isRequired,
    nameProduct: PropTypes.string.isRequired,
    price: PropTypes.number.isRequired,
    discount: PropTypes.number,
    sales: PropTypes.number,
  }).isRequired,
};

export default Card;
