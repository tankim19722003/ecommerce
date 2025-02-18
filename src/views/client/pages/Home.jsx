import Banner from "../../../components/display/Banner";
import ProductsSlide from "../../../components/display/ProductsSlide";
import Header from "../../../components/Header/Header";
import HeaderTop from "../../../components/Header/HeaderTop";
import SuggestionsSlide from "../../../components/header/SuggestionsSlide";
import CardList from "../../../components/Products/CardList";
import { useTheme } from "../../../Provider/ThemeProvider";

const Home = () => {
  const { isDarkMode } = useTheme();
  return (
    <div
      className={`flex flex-col justify-center   ${
        isDarkMode ? "bg-background" : "bg-[#515151]"
      }`}
    >
      <HeaderTop />
      <Header />
      <SuggestionsSlide />
      <Banner />
      <ProductsSlide />
      <CardList />
      <ProductsSlide />
    </div>
  );
};

export default Home;
