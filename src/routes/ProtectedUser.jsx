import { Navigate, Outlet } from "react-router-dom";

import { useAuth } from "../contexts/User/AuthContext";
import Loading from "../views/client/pages/Loading";

const ProtectedUser = ({ children }) => {
  const {
    authState: { authLoading, isAuthenticated },
  } = useAuth();

  if (authLoading) {
    return <Loading />;
  }

  if (!isAuthenticated) {
    return children ? children : <Outlet />;
  }

  return <Navigate to="/" replace />;
};

export default ProtectedUser;
