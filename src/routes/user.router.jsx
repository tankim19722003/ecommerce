import { createBrowserRouter } from "react-router-dom";
import { Suspense, lazy } from "react";
import Loading from "../views/client/pages/Loading";
import ScrollButton from "../components/features/ScrollButton";

const Home = lazy(() => import("../views/client/pages/Home"));
const Login = lazy(() => import("../views/client/auth/Login"));
const Register = lazy(() => import("../views/client/auth/Register"));
const Cart = lazy(() => import("../views/client/pages/Cart"));
const UserInfo = lazy(() => import("../views/client/pages/UserInfo"));
const ProductDetail = lazy(() => import("../views/client/pages/ProductDetail"));
const Account = lazy(() => import("../components/User/Account"));
const Order = lazy(() => import("../components/User/Order"));
const Profile = lazy(() => import("../components/User/Profile"));
const Payment = lazy(() => import("../components/User/Payment"));
const Address = lazy(() => import("../components/User/Address"));
const Voucher = lazy(() => import("../components/User/Voucher"));

const router = createBrowserRouter([
  {
    path: "/",
    element: (
      <Suspense fallback={<Loading />}>
        <ScrollButton />
        <Home />
      </Suspense>
    ),
  },
  {
    path: "/login",
    element: (
      <Suspense fallback={<Loading />}>
        <Login />
      </Suspense>
    ),
  },
  {
    path: "/register",
    element: (
      <Suspense fallback={<Loading />}>
        <Register />
      </Suspense>
    ),
  },
  {
    path: "/loading",
    element: <Loading />,
  },
  {
    path: "/cart",
    element: (
      <Suspense fallback={<Loading />}>
        <Cart />
      </Suspense>
    ),
  },
  {
    path: "/userinfo",
    element: (
      <Suspense fallback={<Loading />}>
        <UserInfo />
      </Suspense>
    ),
    children: [
      {
        path: "order",
        element: (
          <Suspense fallback={<Loading />}>
            <Order />
          </Suspense>
        ),
      },
      {
        path: "voucher",
        element: (
          <Suspense fallback={<Loading />}>
            <Voucher />
          </Suspense>
        ),
      },
      {
        path: "account/profile",
        element: (
          <Suspense fallback={<Loading />}>
            <Profile />
          </Suspense>
        ),
      },
      {
        path: "account/payment",
        element: (
          <Suspense fallback={<Loading />}>
            <Payment />
          </Suspense>
        ),
      },
      {
        path: "account/address",
        element: (
          <Suspense fallback={<Loading />}>
            <Address />
          </Suspense>
        ),
      },
    ],
  },
  {
    path: "/product/:id",
    element: (
      <Suspense fallback={<Loading />}>
        <ProductDetail />
      </Suspense>
    ),
  },
]);

export default router;
