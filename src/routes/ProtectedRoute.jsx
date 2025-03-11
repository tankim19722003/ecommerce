import { Navigate, Outlet } from "react-router-dom";
import { useContext, useState } from "react";
import { useAuth } from "../contexts/User/AuthContext";
import Loading from "../views/client/pages/Loading";

const ProtectedRoute = ({ children }) => {
  const {
    authState: { authLoading, isAuthenticated },
  } = useAuth();

  if (authLoading) {
    return <Loading />;
  }

  if (!isAuthenticated) {
    return <Navigate to="/login" replace />;
  }

  return children ? children : <Outlet />;
};

export default ProtectedRoute;
