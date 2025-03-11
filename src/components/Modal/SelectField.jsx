import React from "react";
import PropTypes from "prop-types";
import { useTheme } from "../../Provider/ThemeProvider";

const SelectField = ({ name, payload, value, onChange, className }) => {
  const { isDarkMode } = useTheme();
  const options = payload?.options || [];

  const parseValue = (val) => {
    try {
      if (val === "true") return true;
      if (val === "false") return false;
      if (!isNaN(val) && val !== "") return Number(val);
      return val;
    } catch {
      return val;
    }
  };

  return (
    <div
      className={`flex flex-1 items-center py-2 px-2 rounded-md text-sm ${
        isDarkMode ? "border border-dark-900" : "bg-dark-400 text-dark-1000"
      } ${className || ""}`}
    >
      <select
        name={name}
        className="w-full border-none outline-none bg-transparent"
        onChange={(e) => onChange(parseValue(e.target.value))}
        value={value?.toString() || ""}
      >
        <option
          className={`${
            isDarkMode
              ? " bg-dark-1000 text-dark-100"
              : "bg-dark-300 text-white"
          }`}
          value=""
        >
          {payload?.header_option || "Hãy chọn"}
        </option>
        {options.map((option, index) => (
          <option
            className={`${
              isDarkMode
                ? " bg-dark-1000 text-dark-100"
                : "bg-dark-300 text-white"
            }`}
            key={index}
            value={option.value.toString()}
          >
            {option.label}
          </option>
        ))}
      </select>
    </div>
  );
};

// **PropTypes Validation**
SelectField.propTypes = {
  name: PropTypes.string.isRequired,
  payload: PropTypes.shape({
    header_option: PropTypes.string,
    options: PropTypes.arrayOf(
      PropTypes.shape({
        label: PropTypes.string.isRequired,
        value: PropTypes.oneOfType([
          PropTypes.string,
          PropTypes.number,
          PropTypes.bool,
        ]).isRequired,
      })
    ).isRequired,
  }).isRequired,
  value: PropTypes.oneOfType([
    PropTypes.string,
    PropTypes.number,
    PropTypes.bool,
  ]),
  onChange: PropTypes.func.isRequired,
  className: PropTypes.string,
};

export default SelectField;
