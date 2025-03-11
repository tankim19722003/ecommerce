import Banner from "../../../components/display/Banner";
import ProductsSlide from "../../../components/display/ProductsSlide";
import ScrollButton from "../../../components/features/ScrollButton";
import Header from "../../../components/Header/Header";
import HeaderTop from "../../../components/Header/HeaderTop";
import SuggestionsSlide from "../../../components/header/SuggestionsSlide";
import CardList from "../../../components/Products/CardList";
import LayoutModeBackground from "../layout/LayoutModeBackground";

const Home = () => {
  return (
    <LayoutModeBackground>
      <HeaderTop />
      <Header />
      <SuggestionsSlide />
      <Banner />
      <ProductsSlide />
      <CardList />
      <ProductsSlide />
      <ScrollButton />
    </LayoutModeBackground>
  );
};

export default Home;
