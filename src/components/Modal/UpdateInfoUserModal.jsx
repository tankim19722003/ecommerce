import { useEffect, useState } from "react";
import { useTheme } from "../../Provider/ThemeProvider";
import { useAuth } from "../../contexts/User/AuthContext";
import ModalContainer from "./ModalContainer";
import InputField from "./InputField";
import SelectField from "./SelectField";
import DateField from "./DateField";
import { ToastContainer } from "react-toastify";
import {
  notifySuccess,
  notifyWarning,
  notifyError,
} from "../../utils/client/Notify";
import Button from "./Button";

const UpdateInfoUserModal = ({ onCloseUpdateInfoUserModal }) => {
  const { isDarkMode } = useTheme();
  const {
    authState: { user },
    updateUserInfo,
  } = useAuth();

  const [formData, setFormData] = useState({
    account: user.account,
    fullname: user.fullname,
    email: user.email,
    phone_number: user.phonenumber,
    gender: user.gender,
    birth_date: user.birth_date ? user.birth_date.split("T")[0] : "",
  });

  console.log(formData);

  useEffect(() => {
    setFormData({
      ...user,
      account: user.account,
      fullname: user.fullname,
      email: user.email,
      phone_number: user.phone_number,
      gender: user.gender,
      birth_date: user.birth_date ? user.birth_date.split("T")[0] : "",
    });
  }, [user]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
  };

  const handleUpdateUserInfo = async () => {
    try {
      const response = await updateUserInfo(user.id, formData);
      if (response.success) {
        notifySuccess(response.message, 3000);
        onCloseUpdateInfoUserModal();
      }
      notifyWarning(response.message, 3000);
      return;
    } catch (error) {
      notifyWarning(error.message, 3000);
      console.error(error);
    }
  };

  return (
    <ModalContainer onCloseModal={onCloseUpdateInfoUserModal}>
      <div
        className={`w-full flex flex-col gap-[2px] py-[20px] px-[30px] border-b-[1px] border-dashed `}
      >
        <h1 className="font-nunito font-bold text-[1.5rem]">
          <span>Cập nhật thông tin</span>
        </h1>
        <p className="text-[0.9rem] ">
          Nhập và bấm xác nhận để cập nhật thông tin của bạn
        </p>
      </div>
      <div
        className={`w-full flex flex-col gap-[20px] py-[20px] px-[30px] font-nunito text-[0.95rem]  `}
      >
        <InputField
          payload={{
            type: "text",
            placeholder: "Nhập tên tài khoản",
            name: "account",
            value: formData.account,
          }}
          onChange={handleChange}
        />

        <InputField
          payload={{
            type: "text",
            placeholder: "Nhập Họ và tên",
            name: "fullname",
            value: formData.fullname,
          }}
          onChange={handleChange}
        />

        <InputField
          payload={{
            type: "text",
            placeholder: "Nhập email",
            name: "email",
            value: formData.email,
          }}
          onChange={handleChange}
        />

        <InputField
          payload={{
            type: "tel",
            placeholder: "Cập nhật số điện thoại",
            name: "phone_number",
            value: formData.phone_number,
          }}
          onChange={handleChange}
        />

        <SelectField
          name="gender"
          payload={{
            header_option: "Chọn giới tính",
            options: [
              { label: "Nam", value: true },
              { label: "Nữ", value: false },
            ],
          }}
          value={formData.gender}
          onChange={(value) =>
            setFormData((prev) => ({ ...prev, gender: value }))
          }
        />

        <div className="flex items-center gap-[20px]">
          <div className="w-3/12 min-w-[100px]  truncate font-bold ">
            Ngày sinh
          </div>
          <DateField
            value={formData.birth_date}
            payload={{ name: "birth_date", value: formData.birth_date }}
            onChange={handleChange}
          />
        </div>
        <div className="w-full flex flex-col items-center gap-[15px] mt-[20px]">
          <Button onClick={handleUpdateUserInfo}>
            <span>Cập nhật</span>
          </Button>
          <button
            className={`w-full py-[5px] border-[1px] rounded-[5px] font-bold ${
              isDarkMode
                ? "border-[#141414]  bg-white text-dark-100 "
                : " text-white bg-dark-400 border-none"
            }`}
            onClick={onCloseUpdateInfoUserModal}
          >
            Hủy
          </button>
        </div>
      </div>{" "}
      <ToastContainer />
    </ModalContainer>
  );
};

export default UpdateInfoUserModal;
