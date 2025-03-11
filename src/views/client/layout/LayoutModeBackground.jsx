import { useTheme } from "../../../Provider/ThemeProvider";

const LayoutModeBackground = ({ children }) => {
  const { isDarkMode } = useTheme();
  return (
    <div
      className={`flex flex-col items-center justify-center   ${
        isDarkMode ? "bg-background" : "bg-[#515151]"
      }`}
    >
      {children}
    </div>
  );
};

export default LayoutModeBackground;
