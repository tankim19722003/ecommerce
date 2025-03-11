import React, { useEffect, useState } from "react";
import { useTheme } from "../../Provider/ThemeProvider";
import { useAuth } from "../../contexts/User/AuthContext";
import {
  notifyError,
  notifySuccess,
  notifyWarning,
} from "../../utils/client/Notify";
import InputField from "../Modal/InputField";
import LocationSelector from "../Modal/LocationSelector";
import Button from "../Modal/Button";
import { ToastContainer } from "react-toastify";
import { useShop } from "../../contexts/User/ShopContext";
import { useNavigate } from "react-router-dom";

const FormRegisterShop = () => {
  const navigate = useNavigate();
  const [localAddress, setLocalAddress] = useState({
    province: "",
    district: "",
    village: "",
  });

  const {
    authState: { user },
    registerShop,
  } = useAuth();

  const {
    shopState: { shopInfo, statusShop },
  } = useShop();

  const { isDarkMode } = useTheme();
  const [accessibility, setAccessibility] = useState(false);

  const handleChangeAccessibility = (e) => {
    setAccessibility(e.target.checked);
  };

  useEffect(() => {
    setFormData((prev) => ({
      ...prev,
      village_id: parseInt(localAddress.village),
      email: user.email ? user.email : "",
    }));

    if (statusShop === "PENDING") {
      navigate(`/salesregistation/registersuccess/${user.id}`);
    }
  }, [localAddress.village, statusShop]);

  const [formData, setFormData] = useState({
    shop_name: "",
    description: "",
    village_id: parseInt(localAddress.village),
    specific_address: "",
    phone_number: "",
    email: "",
    cmnd: "",
  });

  console.log(formData);
  console.log("Shop Info:", statusShop);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleRegisterShop = async () => {
    if (!accessibility) {
      notifyWarning(
        "Bạn có đồng ý với chính sách và điều khoản sử dụng của chúng tôi",
        2000,
        isDarkMode
      );
      return;
    }
    try {
      const response = await registerShop(user.id, formData);
      if (response.success) {
        notifySuccess("Đăng ký shop thành công!", 3000);
        navigate(`/salesregistation/registersuccess/${user.id}`);
      }
      notifyWarning(response.message, 3000);
      return;
    } catch (error) {
      notifyError("Đã xảy ra l��i khi đăng ký shop!", 3000);
    }
  };
  return (
    <div className="w-full flex flex-col items-center py-[40px]">
      <ToastContainer />
      <div
        className={`pc:w-[35%] pc:max-w-[600px] pc:min-w-[400px] mb:w-full flex flex-col items-center ${
          isDarkMode
            ? "bg-light-100 text-dark-100 "
            : "bg-dark-200 text-light-100"
        } rounded-[5px] py-[20px]`}
      >
        <div className="w-full px-[20px] py-[10px] flex flex-col gap-[5px] border-b-[1px] border-dashed">
          <h1 className="font-nunito font-black text-[1.9rem] text-primary">
            Đăng ký shop
          </h1>
          <p className="   ">
            Điền đầy đủ thông tin để trở thành người bán hàng trên shoppe
          </p>
        </div>
        <div className="w-full py-[20px] flex flex-col gap-[20px] border-b-[1px] border-dashed px-[20px]">
          <div className="flex items-center gap-[5px] font-nunito font-bold text-red-600 ">
            <div className="w-[28px] h-[28px] rounded-full border-[1px] border-dark-300 flex items-center justify-center text-[0.75rem]">
              <i className="fa-solid fa-store"></i>
            </div>
            <span className="text-[0.85rem]">Thông tin Shop</span>
          </div>
          <form className="flex items-center ">
            <InputField
              payload={{
                type: "text",
                placeholder: "Nhập tên shop của bạn",
                name: "shop_name",
                value: formData.shop_name,
              }}
              onChange={handleChange}
            />
          </form>
          <div className="flex items-center">
            <InputField
              payload={{
                type: "text",
                placeholder: "Nhập mô tả shop của bạn",
                name: "description",
                value: formData.description,
              }}
              onChange={handleChange}
            />
          </div>
          <div className="flex flex-col ">
            <div className="pc:w-3/12 mb:w-full flex mt-[5px] truncate mb:pb-[10px] text-[0.9rem] pb-[10px]">
              <span>Địa chỉ lấy hàng</span>
            </div>
            <div className="flex-1">
              <LocationSelector
                localAddress={localAddress}
                onChange={setLocalAddress}
              />
            </div>
          </div>
          <div className="flex ">
            <textarea
              className={`flex-1 min-h-[100px] max-h-[100px] outline-none rounded-[5px] px-[10px] py-[5px] text-[0.9rem] ${
                isDarkMode ? "bg-light-100 border-[1px]" : "bg-dark-400 "
              }`}
              id=""
              type="text"
              placeholder="Nhập địa chỉ chi tiết  của bạn"
              name="specific_address"
              onChange={handleChange}
              value={formData.specific_address}
            ></textarea>
          </div>
          <div className="flex items-center">
            <InputField
              payload={{
                type: "email",
                placeholder: "Nhập địa chỉ email của bạn",
                name: "email",
                value: formData.email,
                disabled: formData.email ? true : false,
              }}
              onChange={handleChange}
            />
          </div>
          <div className="flex items-center">
            <InputField
              payload={{
                type: "tel",
                placeholder: "Nhập số điện thoại của bạn",
                name: "phone_number",
                value: formData.phone_number,
              }}
              onChange={handleChange}
            />
          </div>
          <div className="flex items-center">
            <InputField
              payload={{
                type: "text",
                placeholder: "CCCD/CMND",
                name: "cmnd",
                value: formData.cmnd,
              }}
              onChange={handleChange}
            />
          </div>
          <div className="flex gap-[5px] ">
            <div className="">
              <input
                className="outline-none bg-transparent"
                type="checkbox"
                name="accessibility"
                onChange={handleChangeAccessibility}
                checked={accessibility}
              />
            </div>
            <span className="text-[0.8rem] font-nunito font-normal mt-[2px]">
              Bạn có đồng ý với{" "}
              <a className="text-blue-600 font-bold" href="">
                chính sách
              </a>{" "}
              và{" "}
              <a className="text-blue-600 font-bold" href="">
                điều khoản sử dụng
              </a>{" "}
              của chúng tôi
            </span>
          </div>
          <div className="w-full flex flex-col items-center gap-[15px] mt-[20px]">
            <Button onClick={handleRegisterShop}>
              <span>Xác nhận</span>
            </Button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default FormRegisterShop;
