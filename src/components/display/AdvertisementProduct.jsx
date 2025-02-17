import React from "react";
import sanpham1 from "../../assets/images/sanpham1.webp";
import sanpham2 from "../../assets/images/sanpham2.webp";
import sanpham3 from "../../assets/images/sanpham3.jpg";
import { useTheme } from "../../Provider/ThemeProvider";

const AdvertisementProduct = () => {
  const { isDarkMode } = useTheme();

  return (
    <div className="w-full flex justify-center py-[30px]">
      <div className="pc:w-[90%] tl:w-full mb:w-full flex tl:flex-col mb:flex-col tl:px-[10px] mb:px-[10px]">
        <div className=" w-full flex items-center overflow-hidden gap-[20px] ">
          {" "}
          <button className="absolute   w-[28px] h-[28px] flex items-center justify-center left-[0%] top-1/2 transform -translate-y-1/2 bg-[#0000005a] text-white p-[10px] rounded-full cursor-pointer hover:bg-[#00000090] transition-all">
            <i className="fa-solid fa-chevron-left"></i>
          </button>
          <button
            className="absolute w-[28px] h-[28px] flex items-center justify-center right-[0%] top-1/2 transform -translate-y-1/2 bg-[#0000005a] text-white p-[10px] rounded-full cursor-pointer hover:bg-[#00000090] transition-all"
            z
          >
            <i className="fa-solid fa-chevron-right"></i>
          </button>
          {cardContent.map((item) => (
            <div
              className="pc:w-3/12 pc:min-w-[260px] tl:w-full mb:w-full"
              key={item.id}
            >
              <div className="w-full relative   group ">
                <div className="w-full overflow-hidden  rounded-[5px]">
                  <img
                    className="w-full h-full  aspect-square object-cover rounded-[5px]  transition-all duration-300 "
                    src={item.thumbnail[1]}
                    alt=""
                  />
                </div>
                <div className="absolute w-[96%] flex flex-col bottom-[2%] left-[2%] bg-[#f6f6f6cb] px-[10px] py-[10px] font-nunito rounded-[5px] line-clamp-2 ">
                  <h1 className="text-[0.9rem] font-bold pb-[5px]">
                    Combo 10 gói cá tuyết sấy nhà Mozzi
                  </h1>
                </div>
              </div>

              <div className="w-full flex items-center gap-[10px]  py-[10px]">
                <div
                  className={`flex-1 flex items-center justify-between  item px-[5px] py-[5px] relative ${
                    isDarkMode
                      ? "bg-white text-dark-100 border-[1px] "
                      : "bg-dark-500 text-white "
                  } rounded-full font-nunito font-bold text-[0.9rem] hover:bg-primary hover:text-white  transition-all duration-300 cursor-pointer `}
                >
                  <span className="pr-[5px] pl-[10px] text-[0.9rem] flex-1">
                    Mua ngay
                  </span>
                  <div
                    className={`min-w-[30px] h-[30px] flex items-center justify-center ${
                      isDarkMode
                        ? " border-[1px]"
                        : "border-[1px] border-[#8a8a8a]"
                    } rounded-full `}
                  >
                    <i className="fa-solid fa-cart-shopping"></i>
                  </div>
                </div>
                <div
                  className={` flex-[0.7] items-center justify-center px-[10px] text-center py-[8px] cursor-pointer ${
                    isDarkMode
                      ? " border-[1px] bg-white text-[#f22525]"
                      : " bg-dark-600 border-[#8a8a8a] text-white"
                  } rounded-full `}
                >
                  <span className="w-full  font-nunito  font-extrabold text-[0.9rem]">
                    100.000đ
                  </span>
                </div>
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  );
};

export default AdvertisementProduct;

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
    coupous: [
      { id: 2, tag: "shipping", nameVoucher: "Trợ ship", treatment: 50 },
    ],
  },
  {
    id: 16,
    thumbnail: [sanpham1, sanpham2],
    infoShop: {
      nameShop: "Pet Kingdom",
      shopTag: "Thức ăn & Phụ kiện",
      avatarShop: sanpham3,
      score: 5000,
    },
    nameProduct: "Thức ăn cho chó vị bò nhập khẩu",
    price: 320000,
    sales: 7000,
    coupous: [
      { id: 1, tag: "discount", nameVoucher: "Giảm giá", treatment: 40 },
    ],
  },
  {
    id: 17,
    thumbnail: [sanpham3, sanpham1],
    infoShop: {
      nameShop: "Paws & Claws",
      shopTag: "Phụ kiện thú cưng",
      avatarShop: sanpham2,
      score: 2000,
    },
    nameProduct: "Vòng cổ phát sáng cho thú cưng",
    price: 110000,
    sales: 1500,
    coupous: [
      { id: 2, tag: "shipping", nameVoucher: "Trợ ship", treatment: 30 },
    ],
  },
  {
    id: 18,
    thumbnail: [sanpham2, sanpham3],
    infoShop: {
      nameShop: "Meow Land",
      shopTag: "Đồ chơi mèo",
      avatarShop: sanpham1,
      score: 3000,
    },
    nameProduct: "Cây cào móng đa năng cho mèo",
    price: 280000,
    sales: 2400,
    coupous: [
      { id: 1, tag: "discount", nameVoucher: "Giảm giá", treatment: 20 },
    ],
  },
  {
    id: 19,
    thumbnail: [sanpham1, sanpham2],
    infoShop: {
      nameShop: "Woof World",
      shopTag: "Đồ chơi cho chó",
      avatarShop: sanpham3,
      score: 4500,
    },
    nameProduct: "Bóng cao su không độc hại cho chó",
    price: 95000,
    sales: 3500,
    coupous: [
      { id: 1, tag: "discount", nameVoucher: "Giảm giá", treatment: 10 },
    ],
  },
  {
    id: 20,
    thumbnail: [sanpham3, sanpham1],
    infoShop: {
      nameShop: "Fluffy Store",
      shopTag: "Chăm sóc thú cưng",
      avatarShop: sanpham2,
      score: 1200,
    },
    nameProduct: "Lược chải lông chống rối cho chó mèo",
    price: 75000,
    sales: 1800,
    coupous: [
      { id: 2, tag: "shipping", nameVoucher: "Trợ ship", treatment: 50 },
    ],
  },
  {
    id: 21,
    thumbnail: [sanpham2, sanpham3],
    infoShop: {
      nameShop: "The Pet Stop",
      shopTag: "Thực phẩm thú cưng",
      avatarShop: sanpham1,
      score: 6000,
    },
    nameProduct: "Xương gặm sạch răng vị thịt bò",
    price: 180000,
    sales: 5000,
    coupous: [
      { id: 1, tag: "discount", nameVoucher: "Giảm giá", treatment: 25 },
    ],
  },
  {
    id: 22,
    thumbnail: [sanpham1, sanpham3],
    infoShop: {
      nameShop: "Happy Paws",
      shopTag: "Phụ kiện thú cưng",
      avatarShop: sanpham2,
      score: 3200,
    },
    nameProduct: "Balo phi hành gia cho mèo",
    price: 400000,
    sales: 700,
    coupous: [
      { id: 2, tag: "shipping", nameVoucher: "Trợ ship", treatment: 40 },
    ],
  },
  {
    id: 23,
    thumbnail: [sanpham3, sanpham2],
    infoShop: {
      nameShop: "Animal Lovers",
      shopTag: "Chăm sóc thú cưng",
      avatarShop: sanpham1,
      score: 900,
    },
    nameProduct: "Sữa tắm thảo dược cho chó",
    price: 150000,
    sales: 2500,
    coupous: [
      { id: 1, tag: "discount", nameVoucher: "Giảm giá", treatment: 15 },
    ],
  },
  {
    id: 24,
    thumbnail: [sanpham2, sanpham1],
    infoShop: {
      nameShop: "Pet Paradise",
      shopTag: "Đồ dùng thú cưng",
      avatarShop: sanpham3,
      score: 2000,
    },
    nameProduct: "Thảm lót sàn chống thấm cho thú cưng",
    price: 130000,
    sales: 800,
    coupous: [
      { id: 2, tag: "shipping", nameVoucher: "Trợ ship", treatment: 30 },
    ],
  },
  {
    id: 25,
    thumbnail: [sanpham1, sanpham3],
    infoShop: {
      nameShop: "Meow & Friends",
      shopTag: "Đồ chơi cho mèo",
      avatarShop: sanpham2,
      score: 1500,
    },
    nameProduct: "Cây nhà cây cột cho mèo leo trèo",
    price: 500000,
    sales: 4500,
    coupous: [
      { id: 1, tag: "discount", nameVoucher: "Giảm giá", treatment: 35 },
    ],
  },
  {
    id: 26,
    thumbnail: [sanpham2, sanpham1],
    infoShop: {
      nameShop: "Pet Essentials",
      shopTag: "Phụ kiện chó mèo",
      avatarShop: sanpham3,
      score: 1100,
    },
    nameProduct: "Áo mưa chống nước cho thú cưng",
    price: 90000,
    sales: 1000,
    coupous: [
      { id: 2, tag: "shipping", nameVoucher: "Trợ ship", treatment: 50 },
    ],
  },
  {
    id: 27,
    thumbnail: [sanpham3, sanpham2],
    infoShop: {
      nameShop: "Furry Love",
      shopTag: "Thực phẩm dinh dưỡng",
      avatarShop: sanpham1,
      score: 3000,
    },
    nameProduct: "Hạt dinh dưỡng cao cấp cho mèo",
    price: 320000,
    sales: 6500,
    coupous: [
      { id: 1, tag: "discount", nameVoucher: "Giảm giá", treatment: 30 },
    ],
  },
  {
    id: 28,
    thumbnail: [sanpham1, sanpham3],
    infoShop: {
      nameShop: "Pawfect Choice",
      shopTag: "Đồ dùng thú cưng",
      avatarShop: sanpham2,
      score: 500,
    },
    nameProduct: "Túi đựng phân chó mèo tự hủy",
    price: 50000,
    sales: 400,
    coupous: [
      { id: 2, tag: "shipping", nameVoucher: "Trợ ship", treatment: 50 },
    ],
  },
  {
    id: 29,
    thumbnail: [sanpham2, sanpham1],
    infoShop: {
      nameShop: "Pet Comfort",
      shopTag: "Phụ kiện tiện ích",
      avatarShop: sanpham3,
      score: 1600,
    },
    nameProduct: "Giường ngủ lông mềm cho thú cưng",
    price: 350000,
    sales: 900,
    coupous: [
      { id: 1, tag: "discount", nameVoucher: "Giảm giá", treatment: 20 },
    ],
  },
  {
    id: 30,
    thumbnail: [sanpham3, sanpham2],
    infoShop: {
      nameShop: "Bark & Purr",
      shopTag: "Sản phẩm vệ sinh",
      avatarShop: sanpham1,
      score: 2500,
    },
    nameProduct: "Bàn chải đánh răng cho chó mèo",
    price: 60000,
    sales: 1200,
    coupous: [
      { id: 2, tag: "shipping", nameVoucher: "Trợ ship", treatment: 50 },
    ],
  },
];
