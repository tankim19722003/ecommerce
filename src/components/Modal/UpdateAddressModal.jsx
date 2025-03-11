import { useContext, useEffect, useState } from "react";
import ModalContainer from "./ModalContainer";
import { ToastContainer } from "react-toastify";
import { useTheme } from "../../Provider/ThemeProvider";
import {
  notifyError,
  notifySuccess,
  notifyWarning,
} from "../../utils/client/Notify";
import { useAddress } from "../../contexts/layout/AddressContext";
import Button from "./Button";
import LocationSelector from "./LocationSelector";
import InputField from "./InputField";
import { AuthContext } from "../../contexts/User/AuthContext";

const UpdateAddressModal = ({ address, onCloseUpdateAddressModal }) => {
  const {
    authState: { user },
  } = useContext(AuthContext);
  const { updateAddressReceiver } = useAddress();

  const [localAddress, setLocalAddress] = useState({
    province: address.province_id,
    district: address.district_id,
    village: address.village_id,
  });

  console.log("updateaddress", address);

  const [formData, setFormData] = useState({
    user_id: user.id,
    village_id: localAddress.village,
    specific_address: address.specific_address,
    receiver_name: address.receiver_name,
    phone_number: address.phone_number,
  });

  console.log("update address data", formData);

  useEffect(() => {
    setFormData((prev) => ({
      ...prev,
      village_id: parseInt(localAddress.village),
    }));
  }, [localAddress.village]);

  const { isDarkMode } = useTheme();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleUpdateAddress = async () => {
    try {
      const response = await updateAddressReceiver(
        address.address_id,
        formData
      );
      if (response.status !== 200) {
        notifyWarning("Thêm địa chỉ không thành công!", 3000, isDarkMode);
        return;
      }
      notifySuccess("Thêm địa chỉ thành công", 3000, isDarkMode);
      onCloseUpdateAddressModal();
    } catch (error) {
      notifyError("Có lỗi xảy ra, vui lòng thử lại sau!", 3000, isDarkMode);
    }
  };
  return (
    <ModalContainer onCloseModal={onCloseUpdateAddressModal}>
      <ToastContainer className="z-[10]" />
      <div
        className={`w-full flex flex-col gap-[2px] py-[20px] px-[30px] border-b-[1px] border-dashed `}
      >
        <h1 className="font-nunito font-bold text-[1.5rem]">
          <span>Chỉnh sửa địa chỉ giao hàng </span>
        </h1>
        <p className="text-[0.9rem] ">
          Nhập và bấm xác nhận để lưu địa chỉ giao hàng của bạn
        </p>
      </div>
      <div
        className={`w-full flex flex-col gap-[20px] py-[20px] px-[30px] font-nunito text-[0.95rem]  `}
      >
        <InputField
          payload={{
            type: "text",
            placeholder: "Nhập tên người nhận hàng",
            name: "receiver_name",
            value: formData.receiver_name,
            required: true,
          }}
          onChange={handleChange}
        />
        <InputField
          payload={{
            type: "tel",
            placeholder: "Nhập số điện thoại nhận hàng",
            name: "phone_number",
            value: formData.phone_number,
            required: true,
          }}
          onChange={handleChange}
        />
        <div className="flex flex-col    gap-[20px]">
          <div className="w-3/12 min-w-3/12   truncate font-bold ">Địa chỉ</div>
          <div className=" rounded-[5px] text-[0.9rem]">
            <LocationSelector
              localAddress={localAddress}
              onChange={setLocalAddress}
            />
          </div>
        </div>
        <InputField
          payload={{
            type: "text",
            placeholder: "Nhập địa chỉ chi tiết",
            name: "specific_address",
            value: formData.specific_address,
          }}
          onChange={handleChange}
        />

        <div className="w-full flex flex-col items-center gap-[15px] mt-[20px]">
          <Button onClick={handleUpdateAddress}>
            <span>Lưu</span>
          </Button>
          <button
            className={`w-full py-[5px] border-[1px] rounded-[5px] font-bold ${
              isDarkMode
                ? "border-[#141414]  bg-white text-dark-100 "
                : " text-white bg-dark-400 border-none"
            }`}
            onClick={onCloseUpdateAddressModal}
          >
            Hủy
          </button>
        </div>
      </div>
    </ModalContainer>
  );
};

export default UpdateAddressModal;
