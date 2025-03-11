import { useState } from "react";
import { useParams } from "react-router-dom";
import LayoutModeBackground from "../../layout/LayoutModeBackground";

import HeaderTop from "../../../../components/Header/HeaderTop";
import Header from "../../../../components/Header/Header";
import SuggestionsSlide from "../../../../components/header/SuggestionsSlide";
import sanpham1 from "../../../../assets/images/sanpham1.webp";
import SectionContainer from "../../layout/SectionContainer";
import { useTheme } from "../../../../Provider/ThemeProvider";

function ProductDetail() {
  const { id } = useParams();
  const { isDarkMode } = useTheme();
  console.log(id);

  const [product, setProduct] = useState(null);

  return (
    <LayoutModeBackground>
      <HeaderTop />
      <Header />
      <SuggestionsSlide />
      <SectionContainer>
        <div className="w-full flex  px-[20px] rounded-[5px]">
          <div className="w-full flex gap-[20px] py-[20px]">
            <div className="pc:w-[36%] mb:w-full flex flex-col gap-[10px]">
              <div className="w-full">
                <img
                  className="aspect-square rounded-[5px] object-cover w-full "
                  src={sanpham1}
                  alt=""
                />
              </div>
              <div className="w-full flex items-center gap-[10px]">
                <div className="w-[20%] cursor-pointer">
                  <img
                    className="aspect-square rounded-[5px] object-cover w-full "
                    src={sanpham1}
                    alt=""
                  />
                </div>
                <div className="w-[20%] cursor-pointer">
                  <img
                    className="aspect-square rounded-[5px] object-cover w-full brightness-50"
                    src={sanpham1}
                    alt=""
                  />
                </div>
                <div className="w-[20%] cursor-pointer">
                  <img
                    className="aspect-square rounded-[5px] object-cover w-full brightness-50"
                    src={sanpham1}
                    alt=""
                  />
                </div>
                <div className="w-[20%] cursor-pointer">
                  <img
                    className="aspect-square rounded-[5px] object-cover w-full brightness-50"
                    src={sanpham1}
                    alt=""
                  />
                </div>

                <div className="w-[20%] cursor-pointer">
                  <img
                    className="aspect-square rounded-[5px] object-cover w-full brightness-50"
                    src={sanpham1}
                    alt=""
                  />
                </div>
              </div>
            </div>
            <div
              className={`pc:w-[64%] h-full mb:w-full flex  ${
                isDarkMode ? "bg-light-100" : "bg-dark-400"
              } rounded-[5px] py-[10px] px-[20px]`}
            >
              <div className="w-full flex flex-col font-nunito">
                <div className="w-full font-semibold text-[1.3rem] py-[10px]">
                  <h1>Cá tuyết sấy nhà Mozzi</h1>
                </div>{" "}
                <div className="w-full flex items-center font-normal text-[0.85rem] py-[10px]">
                  <div className="pr-[20px] py-[5px]">
                    Đã bán <span>8k</span>
                  </div>{" "}
                  <div className="pr-[20px] py-[5px]">
                    Đã bán <span>8k</span>
                  </div>
                  <div className="pr-[20px] py-[5px]">
                    Đã bán <span>8k</span>
                  </div>
                </div>
                <div
                  className={`w-full flex font-semibold text-[1.6rem] py-[10px] px-[10px]   rounded-[5px]`}
                >
                  <span>4000</span>
                </div>
              </div>
            </div>
          </div>
        </div>
      </SectionContainer>
      <SectionContainer>sdasda</SectionContainer>
    </LayoutModeBackground>
  );
}

export default ProductDetail;
