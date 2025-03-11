import PropTypes from "prop-types";
import { useTheme } from "../../Provider/ThemeProvider";

const DateField = ({ payload, onChange, className }) => {
  const { isDarkMode } = useTheme();
  const { name, value } = payload;

  return (
    <div
      className={`flex flex-1 items-center py-2 px-2 rounded-md text-sm ${
        isDarkMode ? "border border-dark-900" : "bg-dark-400 text-dark-1000"
      } ${className || ""}`}
    >
      <input
        type="date"
        name={name}
        className="w-full border-none outline-none bg-transparent"
        value={value ? value.toString().slice(0, 10) : ""}
        onChange={onChange}
      />
    </div>
  );
};

DateField.propTypes = {
  payload: PropTypes.shape({
    name: PropTypes.string.isRequired,
    value: PropTypes.oneOfType([PropTypes.string, PropTypes.instanceOf(Date)]),
  }).isRequired,
  onChange: PropTypes.func.isRequired,
  className: PropTypes.string,
};

export default DateField;
