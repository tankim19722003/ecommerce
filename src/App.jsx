import "./App.css";
import { Suspense, lazy } from "react";
import { Routes, Route } from "react-router-dom";
import Loading from "./pages/Loading";
import ScrollButton from "./components/features/ScrollButton";
import UserInfo from "./pages/UserInfo";

const Home = lazy(() => import("./pages/Home"));
const Login = lazy(() => import("./components/auth/Login"));
const Register = lazy(() => import("./components/auth/Register"));
const Cart = lazy(() => import("./pages/Cart"));
const ProductDetail = lazy(() => import("./pages/ProductDetail"));

function App() {
  return (
    <Suspense fallback={<Loading />}>
      <ScrollButton />
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/login" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/loading" element={<Loading />} />
        <Route path="/cart" element={<Cart />} />
        <Route path="/userinfo" element={<UserInfo />} />
        <Route path="/product/:id" element={<ProductDetail />} />
      </Routes>
    </Suspense>
  );
}

export default App;
