import { Navigate, Outlet, useLocation } from "react-router-dom";

import { useAuth } from "../contexts/User/AuthContext";
import Loading from "../views/client/pages/Loading";
import { useState } from "react";
const ProtectedRouteShop = ({ children }) => {
  const location = useLocation();
  const [shopInfo, setShopInfo] = useState({
    id: 1,
    name: "Shop 1",
    address: "123 Thanh Nghị",
    phone: "0987654321",
    email: "shop1@example.com",
    isActive: true,
    role: "user",
  });

  const {
    authState: { authLoading, isAuthenticated },
  } = useAuth();

  if (authLoading) {
    return <Loading />;
  }

  if (!isAuthenticated) {
    return <Navigate to="/login" replace />;
  }

  if (shopInfo.role !== "shop") {
    return <Navigate to="/salesregistation/formregister" replace />;
  }

  return children ? children : <Outlet />;
};

export default ProtectedRouteShop;
