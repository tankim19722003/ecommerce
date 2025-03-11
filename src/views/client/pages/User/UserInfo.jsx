import { Outlet } from "react-router-dom";
import HeaderTop from "../../../../components/Header/HeaderTop";
import Header from "../../../../components/Header/Header";

import SuggestionsSlide from "../../../../components/header/SuggestionsSlide";
import ScrollButton from "../../../../components/features/ScrollButton";

import NavUserInfo from "../../../../components/User/NavUserInfo";
import LayoutModeBackground from "../../layout/LayoutModeBackground";

const UserInfo = () => {
  return (
    <LayoutModeBackground>
      <HeaderTop />
      <Header />
      <SuggestionsSlide />
      <ScrollButton />
      <div className="w-full flex flex-col items-center pc:py-[30px] mb:py-[10px]  mb:px-[10px] height-screen-minus-header">
        <div className="pc:w-[90%] mb:w-full flex gap-[20px]">
          <NavUserInfo />

          <div className={`w-9/12 mb:w-full flex flex-col gap-[10px]`}>
            <div
              className={`w-full flex items-center justify-between   rounded-[5px] `}
            >
              <Outlet />
            </div>
          </div>
        </div>
      </div>
    </LayoutModeBackground>
  );
};

export default UserInfo;
