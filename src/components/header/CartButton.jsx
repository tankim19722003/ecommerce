import { useNavigate } from "react-router-dom";

import { AuthContext } from "../../contexts/User/AuthContext";
import { useContext, useEffect } from "react";

const CartButton = ({ isLoggedIn }) => {
  const navigate = useNavigate();

  const {
    authState: { isAuthenticated },
  } = useContext(AuthContext);

  const handleCartClick = () => {
    if (isAuthenticated) {
      navigate("/cart");
    } else {
      alert("Bạn cần đăng nhập để tiếp tục!");
      navigate("/login");
    }
  };

  return (
    <div
      onClick={handleCartClick}
      className="relative cart pc:w-[80px] tl:w-[60px] mb:min-w-[42px] h-[42px] bg-[#f8af24] flex items-center justify-center hover:border-[1px] border-[#ccc] text-white rounded-[5px] cursor-pointer text-[1.2rem]"
    >
      <i className="fa-solid fa-cart-shopping"></i>
    </div>
  );
};

export default CartButton;
