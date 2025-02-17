import "./App.css";
import { Suspense, lazy } from "react";
import { Routes, Route } from "react-router-dom";
import Loading from "./views/client/pages/Loading";
import ScrollButton from "./components/features/ScrollButton";
import UserInfo from "./views/client/pages/UserInfo";

const Home = lazy(() => import("./views/client/pages/Home"));
const Login = lazy(() => import("./views/client/auth/Login"));
const Register = lazy(() => import("./views/client/auth/Register"));
const Cart = lazy(() => import("./views/client/pages/Cart"));
const ProductDetail = lazy(() => import("./views/client/pages/ProductDetail"));

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
