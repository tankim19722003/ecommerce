import {
  createContext,
  useContext,
  useEffect,
  useState,
  useReducer,
} from "react";
import axios from "axios";
import { AuthContext } from "../User/AuthContext";
import {
  ADD_ADDRESS,
  apiUrl,
  LOCAL_STORAGE_TOKEN_NAME,
  SET_ADDRESSES,
  SET_DISTRICTS,
  SET_PROVINCES,
  SET_VILLAGES,
  UPDATE_ADDRESS,
  DELETE_ADDRESS,
} from "../User/contants";
import { addressReducer } from "../../reducers/User/AddressReducer";

export const AddressContext = createContext();

export const AddressProvider = ({ children }) => {
  const [addressState, dispatch] = useReducer(addressReducer, {
    provinces: [],
    districts: [],
    villages: [],
    addresses: [],
  });

  const { authState } = useContext(AuthContext);
  const token = localStorage.getItem(LOCAL_STORAGE_TOKEN_NAME);

  const fetchProvinces = async () => {
    try {
      const response = await axios.get(
        `${apiUrl}/api/v1/user_village/get_all_provinces`
      );
      if (response.status === 200) {
        dispatch({ type: SET_PROVINCES, payload: response.data });
      }
    } catch (error) {
      console.error("Lỗi khi lấy danh sách tỉnh:", error);
    }
  };

  const fetchDistricts = async (provinceId) => {
    try {
      const response = await axios.get(
        `${apiUrl}/api/v1/user_village/get_all_districts/${provinceId}`
      );
      if (response.status === 200) {
        dispatch({ type: SET_DISTRICTS, payload: response.data });
      }
      return response.data;
    } catch (error) {
      console.error("Lỗi khi lấy danh sách huyện:", error);
    }
  };

  const fetchVillages = async (districtsId) => {
    try {
      const response = await axios.get(
        `${apiUrl}/api/v1/user_village/get_all_villages/${districtsId}`
      );
      if (response.status === 200) {
        dispatch({ type: SET_VILLAGES, payload: response.data });
        return response.data;
      }
    } catch (error) {
      console.error("Lỗi khi lấy danh sách huyện:", error);
    }
  };

  useEffect(() => {
    if (authState.isAuthenticated) {
      fetchProvinces();
      fetchAddress(authState.user.id);
    }
  }, [authState.isAuthenticated]);

  const fetchAddress = async (userId) => {
    try {
      const response = await axios.get(
        `${apiUrl}/api/v1/user_village/get_all_address/${userId} `,
        {
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json",
          },
        }
      );

      dispatch({
        type: SET_ADDRESSES,
        payload: response.data.addressResponses,
      });
    } catch (error) {
      console.error("Lỗi khi thêm địa chỉ:", error);
    }
  };

  const addAddressReceiver = async (addressData) => {
    try {
      const response = await axios.post(
        `${apiUrl}/api/v1/user_village/add_user_address`,
        addressData,
        {
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json",
          },
        }
      );
      if (response.status === 200) {
        dispatch({ type: ADD_ADDRESS, payload: response.data });
      }
      return response;
    } catch (error) {
      console.error("Lỗi khi thêm địa chỉ:", error);
    }
  };

  const updateAddressReceiver = async (userVillageId, addressData) => {
    try {
      if (!token) throw new Error("Token không tồn tại");

      const response = await axios.put(
        `${apiUrl}/api/v1/user_village/update_user_address/${userVillageId}`,
        addressData,
        {
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json",
          },
        }
      );

      if (response.status === 200) {
        dispatch({ type: UPDATE_ADDRESS, payload: response.data });
        console.log("Thêm địa chỉ thành công", response.data);
      }

      return response;
    } catch (error) {
      console.error(
        "Lỗi khi thêm địa chỉ:",
        error.response?.data || error.message
      );
      throw error;
    }
  };

  const deleteAddressReceiver = async (addressId) => {
    try {
      const response = await axios.delete(
        `${apiUrl}/api/v1/user_village/${addressId}`,

        {
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json",
          },
        }
      );
      if (response.status === 200) {
        dispatch({ type: DELETE_ADDRESS, payload: addressId });
        console.log("Xóa địa chỉ thành công", response.data);
      }
      return response;
    } catch (error) {
      console.error(
        "Lỗi khi xóa địa chỉ:",
        error.response?.data || error.message
      );
      throw error;
    }
  };

  return (
    <AddressContext.Provider
      value={{
        addressState,
        fetchVillages,
        fetchProvinces,
        fetchDistricts,
        addAddressReceiver,
        fetchAddress,
        updateAddressReceiver,
        deleteAddressReceiver,
      }}
    >
      {children}
    </AddressContext.Provider>
  );
};

// Hook tiện ích để dùng AddressContext
export const useAddress = () => useContext(AddressContext);
