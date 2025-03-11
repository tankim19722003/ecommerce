import React, { useState } from "react";
import Card from "./Card";
import sanpham1 from "../../assets/images/sanpham1.webp";
import sanpham2 from "../../assets/images/sanpham2.webp";
import sanpham3 from "../../assets/images/sanpham3.jpg";
import { useTheme } from "../../Provider/ThemeProvider";

const ITEMS_PER_PAGE = 30;

const CardList = () => {
  const { isDarkMode } = useTheme();
  const [currentPage, setCurrentPage] = useState(1);

  const totalPages = Math.ceil(cardContent.length / ITEMS_PER_PAGE);

  const displayedCards = cardContent.slice(
    (currentPage - 1) * ITEMS_PER_PAGE,
    currentPage * ITEMS_PER_PAGE
  );

  return (
    <div className="w-full flex items-center justify-center my-[40px] tl:px-[10px]">
      <div
        className={`pc:w-[90%] mb:w-full flex-col ${
          isDarkMode ? "bg-white" : "bg-dark-200 text-white"
        } shadow-md rounded-[5px]`}
      >
        <div
          className={`w-full h-[54px] mb:h-full  px-[20px] flex items-center mb:flex-wrap   ${
            isDarkMode ? "border-[#ccc]" : "border-border-dark"
          } border-b-[1px] border-dashed`}
        >
          <div className="mb:min-w-full pc:border-r-[1px] pr-[10px]  border-[#ccc] py-[5px]">
            <span className="font-nunito font-bold text-[1.2rem] text-[#F8AF24]">
              Gợi ý sản phẩm dành cho bạn
            </span>
          </div>
          <div className="flex-1 flex items-center gap-[10px] px-[10px] overflow-hidden mb:py-[10px] mb:px-[2px] ">
            <div
              className={`active  border-[1px] border-[#ccc] rounded-full cursor-pointer px-[10px] py-[5px] font-nunito font-semibold text-[0.9rem] ${
                isDarkMode
                  ? " hover:bg-primary hover:text-white hover:border-none"
                  : "hover:bg-dark-700 hover:text-black hover:border-none"
              }`}
            >
              <span className="truncate">Sản phẩm trending</span>
            </div>
            <div
              className={` border-[1px]   border-[#ccc]  rounded-full cursor-pointer px-[10px] py-[5px] font-nunito font-semibold text-[0.9rem] ${
                isDarkMode
                  ? " hover:bg-primary hover:text-white hover:border-none hover:font-bold"
                  : "hover:bg-dark-700 hover:text-black hover:border-none hover:font-bold"
              }`}
            >
              <span className="truncate"> Sản phẩm công nghệ</span>
            </div>
          </div>
        </div>

        <div className="flex flex-wrap items-center pc:pr-[20px] tl:pr-[10px] mb:pr-[10px] pt-[10px] pb-[20px]">
          {displayedCards.map((content) => (
            <Card key={content.id} cardContent={content} />
          ))}
        </div>

        <div className="flex justify-center items-center space-x-4 py-4">
          <button
            onClick={() => setCurrentPage((prev) => Math.max(prev - 1, 1))}
            disabled={currentPage === 1}
            className={`px-4 py-2 rounded ${
              currentPage === 1
                ? "bg-gray-300 cursor-not-allowed"
                : "bg-blue-500 text-white"
            }`}
          >
            Trước
          </button>

          <span className="font-bold">
            Trang {currentPage} / {totalPages}
          </span>

          <button
            onClick={() =>
              setCurrentPage((prev) => Math.min(prev + 1, totalPages))
            }
            disabled={currentPage === totalPages}
            className={`px-4 py-2 rounded ${
              currentPage === totalPages
                ? "bg-gray-300 cursor-not-allowed"
                : "bg-blue-500 text-white"
            }`}
          >
            Tiếp
          </button>
        </div>
      </div>
    </div>
  );
};

export default CardList;

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
