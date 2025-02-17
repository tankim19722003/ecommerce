import React, { useState, useEffect } from "react";
import logo from "../../assets/images/logo_snapbuy.png";
const ScrollButton = () => {
  const [scrollPosition, setScrollPosition] = useState(0);

  useEffect(() => {
    const handleScroll = () => {
      const scrollTop =
        window.pageYOffset || document.documentElement.scrollTop;
      const scrollHeight =
        document.documentElement.scrollHeight -
        document.documentElement.clientHeight;
      const scrollPercentage = (scrollTop / scrollHeight) * 100;
      setScrollPosition(scrollPercentage);
    };

    window.addEventListener("scroll", handleScroll);

    return () => window.removeEventListener("scroll", handleScroll);
  }, []);

  const scrollToTop = () => {
    window.scrollTo({
      top: 0,
      behavior: "smooth",
    });
  };

  return (
    <div
      className="fixed z-50 bottom-4 right-4 w-[52px] h-[52px] flex items-center justify-center cursor-pointer"
      onClick={scrollToTop}
    >
      <div
        className="absolute inset-0 rounded-full border-[1px] "
        style={{
          background: `conic-gradient(#ffffff ${scrollPosition}%, #DA251D ${scrollPosition}%)`,
          borderRadius: "50%",
        }}
      />
      <div className="w-[42px] h-[42px] bg-[#fff] border-[1px] border-[#ccc] rounded-full flex items-center justify-center relative z-10">
        <img className="w-[20px]" src={logo} alt="" />
      </div>
    </div>
  );
};

export default ScrollButton;
