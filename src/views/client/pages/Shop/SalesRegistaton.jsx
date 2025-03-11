import LayoutModeBackground from "../../layout/LayoutModeBackground";
import HeaderFlexibleView from "../../../../components/Header/HeaderFlexibleView";
import { Outlet } from "react-router-dom";

const SalesRegistaton = () => {
  return (
    <LayoutModeBackground>
      <HeaderFlexibleView title={"Đăng ký trở thành người bán"} />
      <Outlet />
    </LayoutModeBackground>
  );
};

export default SalesRegistaton;
