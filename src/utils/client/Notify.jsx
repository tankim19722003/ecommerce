// toastNotifications.js

import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { useTheme } from "../../Provider/ThemeProvider";

// Hàm config chung cho tất cả các loại thông báo
const getToastConfig = (duration, isDarkMode) => ({
  position: "top-right",
  autoClose: duration || 3000,
  hideProgressBar: false,
  closeOnClick: true,
  pauseOnHover: true,
  draggable: true,
  theme: isDarkMode ? "light" : " dark",
});

export const notifySuccess = (message, duration = 3000, isDarkMode = false) => {
  toast.success(message, getToastConfig(duration, isDarkMode));
};

export const notifyWarning = (message, duration = 3000, isDarkMode = false) => {
  toast.warning(message, getToastConfig(duration, isDarkMode));
};

export const notifyError = (message, duration = 3000, isDarkMode = false) => {
  toast.error(message, getToastConfig(duration, isDarkMode));
};

export const notifyInfo = (message, duration = 3000, isDarkMode = false) => {
  toast.info(message, getToastConfig(duration, isDarkMode));
};

// Thông báo mặc định
export const notifyDefault = (message, duration = 3000, isDarkMode = false) => {
  toast(message, getToastConfig(duration, isDarkMode));
};

// Thông báo cho promise (async)
export const notifyPromise = (
  promise,
  messages,
  duration = 3000,
  isDarkMode = false
) => {
  toast.promise(
    promise,
    {
      pending: messages.pending || "Đang xử lý...",
      success: messages.success || "Thành công!",
      error: messages.error || "Đã xảy ra lỗi!",
    },
    getToastConfig(duration, isDarkMode)
  );
};
