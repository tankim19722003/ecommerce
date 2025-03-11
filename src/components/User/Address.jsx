import { useEffect, useState } from "react";
import { useTheme } from "../../Provider/ThemeProvider";
import { useAuth } from "../../contexts/User/AuthContext";
import AddUserAddressModal from "../Modal/AddUserAddressModal";
import UpdateAddressModal from "../Modal/UpdateAddressModal";
import { useAddress } from "../../contexts/layout/AddressContext";
import { useLocation, useNavigate } from "react-router-dom";
import { notifySuccess, notifyWarning } from "../../utils/client/Notify";
import { ToastContainer } from "react-toastify";

const Address = () => {
  const { isDarkMode } = useTheme();
  const navigate = useNavigate();
  const location = useLocation();

  const {
    addressState: { addresses },
    deleteAddressReceiver,
  } = useAddress();

  const [addressData, setAddressData] = useState({});

  console.log(addresses);

  const [showAddUserAddressModal, setShowAddUserAddressModal] = useState(false);
  const [showUpdateUserAddressModal, setShowUpdateUserAddressModal] =
    useState(false);

  const onOpenAddUserAddressModal = (address) => {
    setShowAddUserAddressModal(true);
    navigate("?popup=adduseraddress", { replace: true });
  };

  const onCloseAddUserAddressModal = () => {
    setShowAddUserAddressModal(false);
    navigate(location.pathname, { replace: true });
  };

  const onOpenUpadateUserAddressModal = (address_id, addressData) => {
    setShowUpdateUserAddressModal(true);
    setAddressData({ ...addressData, address_id: address_id });
    navigate(`?popup=updateuseraddress?${address_id}`, { replace: true });
  };

  const onCloseUpdateAddressModal = () => {
    setShowUpdateUserAddressModal(false);
    navigate(location.pathname, { replace: true });
  };

  useEffect(() => {
    const searchParams = new URLSearchParams(location.search);
    if (searchParams.get("popup") === "updateuseraddress") {
      setShowAddUserAddressModal(true);
    }
    if (searchParams.get("popup") === "adduseraddress") {
      setShowAddUserAddressModal(true);
    }
  }, [location.search]);

  const handleDeletedAddress = async (addressId) => {
    try {
      const response = await deleteAddressReceiver(addressId);
      if (response.status !== 200) {
        notifyWarning("Could not delete address", 3000);
        console.log(response);
        return;
      }
      notifySuccess("Xóa địa chỉ thành công!", 3000);
      console.log(response);
    } catch (error) {
      console.error("Failed to delete address:", error);
    }
  };

  return (
    <div className="w-full flex gap-[20px]">
      <div
        className={`w-full flex flex-col  ${
          isDarkMode
            ? "bg-white text-dark-100"
            : "bg-dark-200 text-white rounded-[5px]"
        }`}
      >
        <div className="w-full flex items-center justify-between px-[20px] py-[10px] border-b-[1px] border-dashed ">
          <div className="flex flex-col font-nunito gap-[5px]">
            <h1 className="font-bold text-[1.4rem]">Địa chỉ giao hàng</h1>
            <p className="font-normal text-[0.95rem]">
              Quản lý thông tin địa chỉ giao hàng của bạn
            </p>
          </div>
        </div>
        <div className="flex flex-col gap-[20px]">
          <div className="w-full flex items-center justify-between border-b-[1px] py-[10px] px-[20px]">
            <div
              className="flex items-center justify-center gap-[5px]  font-bold px-[10px] py-[10px] text-dark-1000  rounded-[5px] bg-primary  cursor-pointer"
              onClick={onOpenAddUserAddressModal}
            >
              <i className="fa-regular fa-square-plus"></i>
              <span className="text-[0.9rem]">Thêm địa chỉ</span>
            </div>
            <div className="w-2/12 py-[5px] border-[1px] rounded-[5px]">
              <select
                className="w-full px-[10px] outline-none bg-transparent"
                name=""
                id=""
              >
                <option className="outline-none  bg-transparent" value="">
                  Lọc
                </option>
              </select>
            </div>
          </div>
          <div className="flex flex-col gap-[20px] px-[20px] ">
            {addresses ? (
              <div className="w-full flex flex-col gap-[20px] pb-[20px]">
                {addresses?.map((address, index) => (
                  <div
                    key={index}
                    className={`w-full flex-col  px-[10px] py-[10px] rounded-[5px] font-nunito ${
                      isDarkMode ? "border-[1px]" : " bg-dark-300 text-white"
                    }`}
                  >
                    <div className="w-full flex items-center justify-between gap-[10px] text-[0.9rem]">
                      <div className="flex items-center gap-[5px]">
                        <div className="flex items-center justify-center w-[30px] h-[30px] p-[10px] rounded-[5px] text-[1.2rem]  ">
                          <i className="fa-regular fa-address-book"></i>
                        </div>
                        <div className="flex items-center gap-[20px] font-nunito text-[1.02rem]">
                          <div className="font-bold">
                            {address.receiver_name}
                          </div>
                          <div className="">{address.phone_number}</div>
                        </div>
                      </div>
                      <div className="flex items-center gap-[10px]">
                        {address.isDefault && (
                          <div className="truncate flex items-center justify-center font-bold text-primary text-[0.9rem] px-[20px] py-[5px] rounded-[5px] border-[1px] ">
                            <span>Mặc định</span>
                          </div>
                        )}
                        <div
                          className="flex items-center gap-[5px] border-[1px] px-[10px] py-[9px] rounded-[5px] cursor-pointer"
                          onClick={() =>
                            onOpenUpadateUserAddressModal(
                              address.address_id,
                              address
                            )
                          }
                        >
                          <i className="fa-solid fa-pen-to-square"></i>
                        </div>
                        <div
                          className="flex items-center gap-[5px] border-[1px] px-[10px] py-[9px] rounded-[5px] cursor-pointer"
                          onClick={() =>
                            handleDeletedAddress(address.address_id)
                          }
                        >
                          <i className="fa-solid fa-trash"></i>
                        </div>
                      </div>
                    </div>
                    <div className="w-full flex items-center gap-[10px] mt-[10px]">
                      <div className="flex items-center py-[10px] gap-[10px]">
                        <div className="flex items-center justify-center w-[30px] h-[30px] p-[10px] rounded-full border-[1px] text-red-700">
                          <i className="fa-solid fa-location-dot"></i>
                        </div>
                        <span className="font-nunito font-bold text-[0.9rem]">
                          {address.village_name} - {address.district_name} -{" "}
                          {address.province_name}
                        </span>
                      </div>
                    </div>
                    <div
                      className={`w-full flex items-center gap-[10px] mt-[10px] px-[5px] ${
                        isDarkMode ? "bg-dark-900" : "bg-dark-400"
                      } rounded-[5px]`}
                    >
                      <div className="flex items-center py-[10px] gap-[10px] ">
                        <div className="flex items-center justify-center w-[24px] h-[24px] p-[10px] rounded-full  text-green-700">
                          <i className="fa-solid fa-location-crosshairs"></i>
                        </div>
                        <span className="font-nunito font-medium text-[0.9rem]">
                          {address.specific_address}
                        </span>
                      </div>
                    </div>
                  </div>
                ))}
              </div>
            ) : (
              <div className="w-full flex justify-center px-[10px] py-[20px] font-nunito ">
                Bạn chưa có địa chỉ nào
              </div>
            )}
          </div>
        </div>
        {showAddUserAddressModal && (
          <AddUserAddressModal
            onCloseAddUserAddressModal={onCloseAddUserAddressModal}
          />
        )}{" "}
        {showUpdateUserAddressModal && (
          <UpdateAddressModal
            address={addressData}
            onCloseUpdateAddressModal={onCloseUpdateAddressModal}
          />
        )}{" "}
        <ToastContainer />
      </div>
    </div>
  );
};

export default Address;
