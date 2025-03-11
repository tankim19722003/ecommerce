import axios from "axios";
import { createContext, useReducer, useEffect, useContext } from "react";
import { authReducer } from "../../reducers/User/AuthReducer";
import setAuthToken from "../../utils/User/setAuthToken";
import {
  apiUrl,
  LOCAL_STORAGE_TOKEN_NAME,
  SET_AUTH,
  UPDATE_AUTH,
} from "../../contexts/User/contants";

export const AuthContext = createContext();

export const AuthContextProvider = ({ children }) => {
  const [authState, dispatch] = useReducer(authReducer, {
    authLoading: true,
    isAuthenticated: false,
    user: null,
  });

  const loadUser = async () => {
    const token = localStorage.getItem(LOCAL_STORAGE_TOKEN_NAME);

    if (!token) {
      dispatch({
        type: SET_AUTH,
        payload: { isAuthenticated: false, user: null },
      });
      return;
    }

    setAuthToken(token);

    try {
      const response = await axios.get(
        `${apiUrl}/api/v1/user/get_user_info/${token}`,
        {
          headers: { Authorization: `Bearer ${token}` },
        }
      );

      if (response.status === 200) {
        const userData = response.data;
        console.log(response);
        dispatch({
          type: SET_AUTH,
          payload: { isAuthenticated: true, user: userData },
        });
      } else {
        throw new Error("Unauthorized");
      }
    } catch (error) {
      localStorage.removeItem(LOCAL_STORAGE_TOKEN_NAME);

      setAuthToken(null);
      dispatch({
        type: SET_AUTH,
        payload: { isAuthenticated: false, user: null },
      });
    }
  };

  useEffect(() => {
    loadUser();
  }, [localStorage.getItem(LOCAL_STORAGE_TOKEN_NAME)]);

  const loginUser = async (userForm) => {
    try {
      const response = await axios.post(
        `${apiUrl}/api/v1/user/login`,
        userForm
      );

      if (response.status === 200) {
        localStorage.setItem(LOCAL_STORAGE_TOKEN_NAME, response.data.token);
        setAuthToken(response.data.token);
        await loadUser();
      }

      return response;
    } catch (error) {
      return error.response?.data || { success: false, message: error.message };
    }
  };

  const registerUser = async (userForm) => {
    try {
      const response = await axios.post(
        `${apiUrl}/api/v1/user/register`,
        userForm
      );

      return response;
    } catch (error) {
      return error.response?.data || { success: false, message: error.message };
    }
  };

  const logoutUser = () => {
    localStorage.removeItem(LOCAL_STORAGE_TOKEN_NAME);
    localStorage.removeItem("user");
    setAuthToken(null);
    dispatch({
      type: SET_AUTH,
      payload: { isAuthenticated: false, user: null },
    });
  };

  const updateUserInfo = async (userId, updatedData) => {
    try {
      const token = localStorage.getItem(LOCAL_STORAGE_TOKEN_NAME);
      if (!token) return { success: false, message: "Bạn chưa đăng nhập" };

      const response = await axios.put(
        `${apiUrl}/api/v1/user/update_user_info/${userId}`,
        updatedData,
        {
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json",
          },
        }
      );

      if (response.status === 200) {
        const updatedUser = response.data;

        dispatch({ type: UPDATE_AUTH, payload: updatedUser });
        return {
          success: true,
          message: "Cập nhật thông tin người dùng thành công",
        };
      } else {
        throw new Error("Cập nhật thông tin thất bại");
      }
    } catch (error) {
      return error.response?.data || { success: false, message: error.message };
    }
  };

  const registerShop = async (userId, formRegisterData) => {
    const token = localStorage.getItem(LOCAL_STORAGE_TOKEN_NAME);
    try {
      const response = await axios.post(
        `${apiUrl}/api/v1/shop/register/${userId}`,
        formRegisterData,
        {
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json",
          },
        }
      );
      if (response.status !== 200) {
        console.log(response.message);
      }
      return { success: true, message: response.message };
    } catch (error) {
      return error.response?.data || { success: false, message: error.message };
    }
  };

  const authContextData = {
    loadUser,
    loginUser,
    registerUser,
    logoutUser,
    updateUserInfo,
    authState,
    registerShop,
  };

  return (
    <AuthContext.Provider value={authContextData}>
      {children}
    </AuthContext.Provider>
  );
};

export const useAuth = () => useContext(AuthContext);
