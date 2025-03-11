import { useState } from "react";
import { Navigate, Outlet } from "react-router-dom";

const ProtectedRouteAdmin = ({ children }) => {
  const [user, setUser] = useState({
    id: 1,
    name: "John Doe",
    email: "john.doe@example.com",
    role: "admin",
  });

  if (user.role !== "admin") {
    return <Navigate to="/admin/login" replace />;
  }

  return children ? children : <Outlet />;
};

export default ProtectedRouteAdmin;
