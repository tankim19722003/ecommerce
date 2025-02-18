import React, { useEffect } from "react";
import HeaderTop from "../../../components/Header/HeaderTop";
import Header from "../../../components/Header/Header";
import { useTheme } from "../../../Provider/ThemeProvider";
import sanpham1 from "../../../assets/images/sanpham1.webp";
import sanpham2 from "../../../assets/images/sanpham2.webp";
import sanpham3 from "../../../assets/images/sanpham3.jpg";
import InfoShop from "../../../components/Products/InfoShop";
import CoupouList from "../../../components/features/CoupouList";
import QuanlityProduct from "../../../components/header/QuanlityProduct";
import SuggestionsSlide from "../../../components/header/SuggestionsSlide";
import AttributeProduct from "../../../components/features/AttributeProduct";
import PathAccess from "../../../components/features/PathAccess";
import { useAuth } from "../../../Provider/AuthProvider";
import { useNavigate } from "react-router-dom";

const Cart = () => {
  const { isDarkMode, toggleTheme } = useTheme();
  const { isAuthenticated } = useAuth();
  const navigate = useNavigate();
  useEffect(() => {
    if (!isAuthenticated) {
      navigate("/");
    }
  }, [isAuthenticated, navigate]);

  return (
    <div
      className={`flex flex-col justify-center   ${
        isDarkMode ? "bg-background" : "bg-[#515151]"
      }`}
    >
      <HeaderTop />
      <Header />
      <SuggestionsSlide />
      <PathAccess />
      <div className="pc:w-full  flex justify-center pc:my-[20px]    mb:px-[0px]">
        <div className="pc:w-[90%] tl:w-full mb:w-full  flex mb:flex-col mb:justify-center gap-[20px]  mb:px-[10px] shadow-sm">
          <div
            className={`pc:w-full tl:w-full mb:w-full ${
              isDarkMode ? "bg-white" : "bg-dark-200 text-white"
            }  py-[10px] pc:px-[20px] tl:px-[10px] mb:px-[10px] rounded-[5px]`}
          >
            <ul className="w-full flex flex-col items-center gap-[20px] mt-[20px] ">
              {cardContent.map((item) => (
                <li
                  key={item.id}
                  className={`w-full flex flex-col items-center  pt-[0px] pb-[15px] px-[10px] rounded-[5px] ${
                    isDarkMode
                      ? "bg-white text-dark-100 border-[1px]"
                      : "bg-dark-400 text-white"
                  }`}
                >
                  <div className="w-full flex items-center justify-between ">
                    <InfoShop info={item.infoShop} />
                    <div
                      className={`p-[5px] w-[25px] h-[25px] flex items-center justify-center ${
                        isDarkMode ? "bg-white " : "bg-dark-400"
                      } `}
                    >
                      <input
                        className="w-full h-full outline-none cursor-pointer bg-transparent"
                        type="checkbox"
                        name=""
                        id=""
                      />
                    </div>
                  </div>{" "}
                  <div className="w-full flex items-center px-[10px]  py-[10px] bg-dark-900 mb-[10px] rounded-[5px]">
                    <CoupouList coupouList={item.coupous} />
                  </div>
                  <div className="w-full h-full flex mb:flex-col  gap-[20px] mb:gap-[10px]  ">
                    <div className="pc:max-w-[100px] pc:max-h-[100px] tl:max-w-[120px] tl:max-h-[140px]  mb:min-w-full  flex justify-between  ">
                      <img
                        className=" w-full h-full aspect-square   rounded-[5px] object-cover"
                        src={item.thumbnail[0]}
                        alt=""
                      />
                    </div>{" "}
                    <div className="w-full  flex items-center mb:flex-col gap-[20px] mb:gap-[10px] justify-between ">
                      <div className="w-3/12 font-nunito font-bold pc:text-[1rem] tl:text-[1rem] mb:text-[1rem]">
                        {item.nameProduct}
                      </div>
                      <div className="w-2/12 flex items-center   ">
                        <AttributeProduct />
                      </div>
                      <div className="w-2/12 flex items-center justify-center ">
                        <div className=" flex items-center gap-[10px]">
                          {(() => {
                            const discountVoucher = item.coupous.find(
                              (coupou) => coupou.tag === "discount"
                            );
                            console.log(discountVoucher);

                            const discountedPrice = discountVoucher
                              ? item.price *
                                (1 - discountVoucher.treatment / 100)
                              : item.price;

                            return (
                              <>
                                <span className="font-semibold pc:text-[1.1rem] tl:text-[1rem] mb:text-[1rem] ">
                                  {discountedPrice.toLocaleString("vi-VN")}đ
                                </span>

                                {discountVoucher && (
                                  <span
                                    className={`font-semibold mt-[3px] pc:text-[0.9rem] tl:text-[0.8rem] mb:text-[0.8rem] line-through ${
                                      isDarkMode
                                        ? "text-[#292929]"
                                        : "text-white"
                                    }`}
                                  >
                                    {item.price.toLocaleString("vi-VN")}đ
                                  </span>
                                )}
                              </>
                            );
                          })()}
                        </div>
                      </div>
                      <div className="w-2/12 flex items-center justify-center">
                        <div className="w-full h-[30px] flex ">
                          <QuanlityProduct defaultQuanlity={item.quanlity} />
                        </div>
                      </div>{" "}
                      <div className="w-2/12 flex items-center justify-center ">
                        <div className=" flex items-center gap-[10px]">
                          {(() => {
                            const discountVoucher = item.coupous.find(
                              (coupou) => coupou.tag === "discount"
                            );
                            console.log(discountVoucher);

                            const discountedPrice = discountVoucher
                              ? item.price *
                                (1 - discountVoucher.treatment / 100)
                              : item.price;

                            return (
                              <>
                                <span className="font-semibold pc:text-[1.2rem] tl:text-[1rem] mb:text-[1rem] text-[#ee2f2f]">
                                  {discountedPrice.toLocaleString("vi-VN")}đ
                                </span>
                              </>
                            );
                          })()}
                        </div>
                      </div>
                      <div
                        className={`p-[5px] w-[25px] h-[25px] flex items-center justify-center ${
                          isDarkMode ? "bg-white " : "bg-dark-400"
                        } `}
                      >
                        <input
                          className="w-full h-full outline-none cursor-pointer bg-transparent"
                          type="checkbox"
                          name=""
                          id=""
                        />
                      </div>
                    </div>
                  </div>
                </li>
              ))}
            </ul>
          </div>
          <div className="w-full fixed bottom-0 left-0 flex justify-center  z-50  bg-white">
            <div className="w-[90%] h-[100px]  sticky top-0   pc:rounded-[5px] shadow-sm mb:border-t-[1px]"></div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Cart;
const cardContent = [
  {
    id: 1,
    thumbnail: [sanpham1, sanpham2],
    infoShop: {
      nameShop: "Mozzi",
      shopTag: "Shop thú cưng",
      avatarShop: sanpham3,
      score: 1000,
    },

    nameProduct: "Combo 10 gói cá tuyết sấy nhà Mozzi",
    price: 240000,
    sales: 999,
    quanlity: 2,
    coupous: [
      { id: 1, tag: "discount", nameVoucher: "Giảm giá", treatment: 50 },
      { id: 2, tag: "shipping", nameVoucher: "Trợ ship", treatment: 50 },
    ],
  },
  {
    id: 2,
    thumbnail: [sanpham2, sanpham3],
    infoShop: {
      nameShop: "Pet Paradise",
      shopTag: "Dịch vụ thú cưng",
      avatarShop: sanpham1,
      score: 4000,
    },

    nameProduct: "Thức ăn cho chó vị gà nhập khẩu",
    price: 150000,
    sales: 20000,
    quanlity: 2,
    coupous: [
      { id: 1, tag: "discount", nameVoucher: "Giảm giá", treatment: 30 },
      { id: 2, tag: "shipping", nameVoucher: "Trợ ship", treatment: 20 },
    ],
  },
  {
    id: 3,
    thumbnail: [sanpham3, sanpham1],
    infoShop: {
      nameShop: "Cún Cưng",
      shopTag: "Phụ kiện thú cưng",
      avatarShop: sanpham2,
      score: 4000,
    },
    nameProduct: "Dây dắt chó bền đẹp, chống rối",
    price: 80000,
    sales: 1500,
    quanlity: 6,
    coupous: [
      { id: 2, tag: "shipping", nameVoucher: "Trợ ship", treatment: 50 },
    ],
  },
  {
    id: 4,
    thumbnail: [sanpham1, sanpham3],
    infoShop: {
      nameShop: "Meow Meow Store",
      shopTag: "Thức ăn mèo",
      avatarShop: sanpham1,
      score: 500,
    },

    nameProduct: "Hạt dinh dưỡng cao cấp cho mèo",
    price: 300000,
    sales: 35000,
    quanlity: 2,
    coupous: [
      { id: 1, tag: "discount", nameVoucher: "Giảm giá", treatment: 60 },
      { id: 2, tag: "shipping", nameVoucher: "Trợ ship", treatment: 50 },
    ],
  },
  {
    id: 5,
    thumbnail: [sanpham2, sanpham1],
    infoShop: {
      nameShop: "Happy Paws",
      shopTag: "Phụ kiện & Thức ăn",
      avatarShop: sanpham3,
      score: 100,
    },

    nameProduct: "Ổ nằm cho thú cưng bằng vải cotton",
    price: 500000,
    sales: 99,
    quanlity: 7,
    coupous: [
      { id: 1, tag: "discount", nameVoucher: "Giảm giá", treatment: 30 },
      { id: 2, tag: "shipping", nameVoucher: "Trợ ship", treatment: 50 },
    ],
  },
  {
    id: 6,
    thumbnail: [sanpham3, sanpham2],
    infoShop: {
      nameShop: "Animal Lover",
      shopTag: "Dụng cụ vệ sinh",
      avatarShop: sanpham2,
      score: 0,
    },

    nameProduct: "Hộp cát vệ sinh cho mèo tự động",
    price: 1200000,
    sales: 20,
    quanlity: 3,
    coupous: [
      { id: 2, tag: "shipping", nameVoucher: "Trợ ship", treatment: 50 },
    ],
  },
  {
    id: 7,
    thumbnail: [sanpham1, sanpham2],
    infoShop: {
      nameShop: "Choco Pet",
      shopTag: "Shop chó mèo",
      avatarShop: sanpham3,
      score: 600,
    },

    nameProduct: "Dầu tắm dưỡng lông hương hoa cỏ",
    price: 180000,
    sales: 400,
    quanlity: 5,
    coupous: [
      { id: 1, tag: "discount", nameVoucher: "Giảm giá", treatment: 20 },
      { id: 2, tag: "shipping", nameVoucher: "Trợ ship", treatment: 50 },
    ],
  },
  {
    id: 8,
    thumbnail: [sanpham2, sanpham3],
    infoShop: {
      nameShop: "Purrfection Store",
      shopTag: "Phụ kiện mèo",
      avatarShop: sanpham1,
      score: 700,
    },

    nameProduct: "Bàn cào móng mèo đa năng kèm đồ chơi",
    price: 250000,
    sales: 520,
    quanlity: 1,
    coupous: [
      { id: 1, tag: "discount", nameVoucher: "Giảm giá", treatment: 50 },
      { id: 2, tag: "shipping", nameVoucher: "Trợ ship", treatment: 50 },
    ],
  },
  {
    id: 9,
    thumbnail: [sanpham1, sanpham3],
    infoShop: {
      nameShop: "Woof & Meow",
      shopTag: "Thức ăn thú cưng",
      avatarShop: sanpham2,
      score: 200,
    },

    nameProduct: "Thức ăn khô cao cấp dành cho chó lớn",
    price: 700000,
    sales: 80,
    quanlity: 1,
    coupous: [
      { id: 1, tag: "discount", nameVoucher: "Giảm giá", treatment: 20 },
      { id: 2, tag: "shipping", nameVoucher: "Trợ ship", treatment: 50 },
    ],
  },
  {
    id: 10,
    thumbnail: [sanpham3, sanpham1],
    infoShop: {
      nameShop: "Cozy Pets",
      shopTag: "Chăm sóc thú cưng",
      avatarShop: sanpham3,
      score: 400,
    },

    nameProduct: "Giường ngủ sang trọng cho thú cưng",
    price: 800000,
    sales: 150,
    quanlity: 9,
    coupous: [
      { id: 1, tag: "discount", nameVoucher: "Giảm giá", treatment: 30 },
      { id: 2, tag: "shipping", nameVoucher: "Trợ ship", treatment: 50 },
    ],
  },
  {
    id: 11,
    thumbnail: [sanpham1, sanpham2],
    infoShop: {
      nameShop: "Pawfection",
      shopTag: "Dịch vụ thú cưng",
      avatarShop: sanpham1,
      score: 300,
    },

    nameProduct: "Balo đựng thú cưng trong suốt tiện lợi",
    price: 350000,
    sales: 400,
    quanlity: 12,
    coupous: [
      { id: 1, tag: "discount", nameVoucher: "Giảm giá", treatment: 70 },
      { id: 2, tag: "shipping", nameVoucher: "Trợ ship", treatment: 50 },
    ],
  },
  {
    id: 12,
    thumbnail: [sanpham2, sanpham3],
    infoShop: {
      nameShop: "Tail Waggers",
      shopTag: "Đồ chơi thú cưng",
      avatarShop: sanpham2,
      score: 1000,
    },

    nameProduct: "Bóng cao su phát sáng cho thú cưng",
    price: 120000,
    sales: 500,
    quanlity: 4,
    coupous: [
      { id: 1, tag: "discount", nameVoucher: "Giảm giá", treatment: 10 },
      { id: 2, tag: "shipping", nameVoucher: "Trợ ship", treatment: 50 },
    ],
  },
  {
    id: 13,
    thumbnail: [sanpham3, sanpham2],
    infoShop: {
      nameShop: "Furry Friends",
      shopTag: "Phụ kiện thú cưng",
      avatarShop: sanpham1,
      score: 900,
    },

    nameProduct: "Áo hoodie thời trang cho chó mèo",
    price: 180000,
    sales: 2400,
    quanlity: 1,
    coupous: [
      { id: 1, tag: "discount", nameVoucher: "Giảm giá", treatment: 5 },
      { id: 2, tag: "shipping", nameVoucher: "Trợ ship", treatment: 50 },
    ],
  },
  {
    id: 14,
    thumbnail: [sanpham1, sanpham3],
    infoShop: {
      nameShop: "Kitty Haven",
      shopTag: "Thức ăn mèo",
      avatarShop: sanpham3,
      score: 10000,
    },

    nameProduct: "Thức ăn ướt cao cấp cho mèo vị cá ngừ",
    price: 400000,
    sales: 120000,
    quanlity: 1,
    coupous: [
      { id: 1, tag: "discount", nameVoucher: "Giảm giá", treatment: 20 },
      { id: 2, tag: "shipping", nameVoucher: "Trợ ship", treatment: 50 },
    ],
  },
  {
    id: 15,
    thumbnail: [sanpham2, sanpham1],
    infoShop: {
      nameShop: "Happy Tails",
      shopTag: "Thức ăn & Phụ kiện",
      avatarShop: sanpham2,
      score: 1000,
    },

    nameProduct: "Bát ăn chống trượt cho thú cưng",
    price: 90000,
    sales: 650,
    quanlity: 2,
    coupous: [
      { id: 2, tag: "shipping", nameVoucher: "Trợ ship", treatment: 50 },
    ],
  },
];
