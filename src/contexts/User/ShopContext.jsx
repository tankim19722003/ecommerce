import axios from "axios";
import { createContext, useReducer, useContext, useEffect } from "react";
import { shopReducer } from "../../reducers/User/ShopReducer";
import { apiUrl, LOCAL_STORAGE_TOKEN_NAME, SET_SHOP_INFO } from "./contants";
import setAuthToken from "../../utils/User/setAuthToken";

import { useAuth } from "./AuthContext";
import InfoShop from "../../components/Products/InfoShop";
const ShopContext = createContext();

export const ShopContextProvider = ({ children }) => {
  const [shopState, dispatch] = useReducer(shopReducer, {
    statusShop: "",
    shopLoading: false,
    shopInfo: null,
  });
  const token = localStorage.getItem(LOCAL_STORAGE_TOKEN_NAME);
  const {
    authState: { user },
  } = useAuth();

  const loadShopInfo = async () => {
    if (!token || !user?.id) {
      dispatch({
        type: SET_SHOP_INFO,
        payload: { statusShop: "", shopInfo: null },
      });
      return;
    }

    setAuthToken(token);

    try {
      const response = await axios.get(`${apiUrl}/api/v1/shop/${user.id}`, {
        headers: { Authorization: `Bearer ${token}` },
      });

      if (response.status === 200) {
        dispatch({
          type: SET_SHOP_INFO,
          payload: {
            statusShop: response.data.status,
            shopInfo: response.data,
          },
        });
      }

      return response.data;
    } catch (error) {
      console.error("Error fetching shop info:", error);

      dispatch({
        type: SET_SHOP_INFO,
        payload: { statusShop: "", shopInfo: null },
      });

      return null;
    }
  };

  useEffect(() => {
    if (user) {
      loadShopInfo();
    }
  }, [user]);

  const shopContextData = {
    loadShopInfo,
    shopState,
  };

  return (
    <ShopContext.Provider value={shopContextData}>
      {children}
    </ShopContext.Provider>
  );
};

export const useShop = () => useContext(ShopContext);
