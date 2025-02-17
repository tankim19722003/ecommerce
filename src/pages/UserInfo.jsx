import React, { useEffect } from "react";
import { useAuth } from "../Provider/AuthProvider";
import { useNavigate } from "react-router-dom";

const UserInfo = () => {
  const navigate = useNavigate();
  const { isAuthenticated } = useAuth();

  useEffect(() => {
    if (!isAuthenticated) {
      navigate("/");
    }
  }, [isAuthenticated, navigate]); // Thêm dependencies để tránh render lại liên tục

  return <div>UserInfo</div>;
};

export default UserInfo;
