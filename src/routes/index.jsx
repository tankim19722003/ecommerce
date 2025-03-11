import { createBrowserRouter } from "react-router-dom";
import { Suspense, lazy } from "react";
import Loading from "../views/client/pages/Loading";
import ErrorPage from "../components/Middleware/ErrorPage";
import ProtectedRoute from "./ProtectedRoute";
import ProtectedUser from "./ProtectedUser";
import SalesRegistaton from "../views/client/pages/Shop/SalesRegistaton";
import FormRegisterShop from "../components/SallerShop/FormRegisterShop";
import RegisterSuccess from "../components/SallerShop/RegisterSuccess";
import ShopDashBoard from "../views/client/pages/Shop/ShopDashBoard";
import ProtectedRouteShop from "./ProtectedRouteShop";
import AdminLogin from "../views/admin/auth/AdminLogin";
import UsersManager from "../components/Admin/display/UsersManager";
import HomeAdmin from "../components/Admin/display/HomeAdmin";
import SelllersManager from "../components/Admin/display/SelllersManager";
import ProtectedRouteAdmin from "./ProtectedRouteAdmin";
import ProductsManager from "../components/Admin/display/ProductsManager";
import CategoriesManager from "../components/Admin/display/CategoriesManager";
const AdminDashBoard = lazy(() =>
  import("../views/admin/pages/AdminDashBoard")
);
const Home = lazy(() => import("../views/client/pages/Home"));
const Login = lazy(() => import("../views/client/auth/Login"));
const Register = lazy(() => import("../views/client/auth/Register"));
const Cart = lazy(() => import("../views/client/pages/User/Cart"));
const UserInfo = lazy(() => import("../views/client/pages/User/UserInfo"));
const ProductDetail = lazy(() =>
  import("../views/client/pages/User/ProductDetail")
);
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
        <Home />
      </Suspense>
    ),
    errorElement: <ErrorPage />,
  },

  {
    path: "/login",
    element: (
      <ProtectedUser>
        <Suspense fallback={<Loading />}>
          <Login />
        </Suspense>
      </ProtectedUser>
    ),
    errorElement: <ErrorPage />,
  },
  {
    path: "/register",
    element: (
      <ProtectedUser>
        <Suspense fallback={<Loading />}>
          <Register />
        </Suspense>
      </ProtectedUser>
    ),
    errorElement: <ErrorPage />,
  },

  {
    path: "/cart",
    element: (
      <Suspense fallback={<Loading />}>
        <Cart />
      </Suspense>
    ),
    errorElement: <ErrorPage />,
  },
  {
    path: "/userinfo",
    element: (
      <ProtectedRoute>
        <Suspense fallback={<Loading />}>
          <UserInfo />
        </Suspense>
      </ProtectedRoute>
    ),
    errorElement: <ErrorPage />,
    children: [
      {
        path: "order",
        element: (
          <Suspense fallback={<Loading />}>
            <Order />
          </Suspense>
        ),
        errorElement: <ErrorPage />,
      },
      {
        path: "voucher",
        element: (
          <ProtectedRoute>
            <Suspense fallback={<Loading />}>
              <Voucher />
            </Suspense>
          </ProtectedRoute>
        ),
        errorElement: <ErrorPage />,
      },
      {
        path: "account/profile",
        element: (
          <ProtectedRoute>
            <Suspense fallback={<Loading />}>
              <Profile />
            </Suspense>
          </ProtectedRoute>
        ),
        errorElement: <ErrorPage />,
      },
      {
        path: "account/payment",
        element: (
          <ProtectedRoute>
            <Suspense fallback={<Loading />}>
              <Payment />
            </Suspense>
          </ProtectedRoute>
        ),
        errorElement: <ErrorPage />,
      },
      {
        path: "account/address",
        element: (
          <ProtectedRoute>
            <Suspense fallback={<Loading />}>
              <Address />
            </Suspense>
          </ProtectedRoute>
        ),
        errorElement: <ErrorPage />,
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
    errorElement: <ErrorPage />,
  },
  {
    path: "/salesregistation",
    element: (
      <ProtectedRoute>
        <Suspense fallback={<Loading />}>
          <SalesRegistaton />
        </Suspense>
      </ProtectedRoute>
    ),
    errorElement: <ErrorPage />,
    children: [
      {
        path: "formregister",
        element: (
          <ProtectedRoute>
            <Suspense fallback={<Loading />}>
              <FormRegisterShop />
            </Suspense>
          </ProtectedRoute>
        ),
        errorElement: <ErrorPage />,
      },
      {
        path: "registersuccess/:userId",
        element: (
          <Suspense fallback={<Loading />}>
            <RegisterSuccess />
          </Suspense>
        ),
        errorElement: <ErrorPage />,
      },
    ],
  },
  {
    path: "/shopdashboard",
    element: (
      <ProtectedRouteShop>
        <Suspense fallback={<Loading />}>
          <ShopDashBoard />
        </Suspense>
      </ProtectedRouteShop>
    ),
    errorElement: <ErrorPage />,
  },
  {
    path: "/admin",
    element: (
      <ProtectedRouteAdmin>
        <Suspense fallback={<Loading />}>
          <AdminDashBoard />
        </Suspense>
      </ProtectedRouteAdmin>
    ),
    children: [
      {
        path: "",
        element: (
          <ProtectedRouteAdmin>
            <Suspense fallback={<Loading />}>
              <HomeAdmin />
            </Suspense>
          </ProtectedRouteAdmin>
        ),
        errorElement: <ErrorPage />,
      },
      {
        path: "usersmanager",
        element: (
          <ProtectedRouteAdmin>
            <Suspense fallback={<Loading />}>
              <UsersManager />
            </Suspense>
          </ProtectedRouteAdmin>
        ),
        errorElement: <ErrorPage />,
      },
      {
        path: "sellersmanager",
        element: (
          <ProtectedRouteAdmin>
            <Suspense fallback={<Loading />}>
              <SelllersManager />
            </Suspense>
          </ProtectedRouteAdmin>
        ),
        errorElement: <ErrorPage />,
      },
      {
        path: "categoriesmanager",
        element: (
          <ProtectedRouteAdmin>
            <Suspense fallback={<Loading />}>
              <CategoriesManager />
            </Suspense>
          </ProtectedRouteAdmin>
        ),
        errorElement: <ErrorPage />,
      },
      {
        path: "productsmanager",
        element: (
          <ProtectedRouteAdmin>
            <Suspense fallback={<Loading />}>
              <ProductsManager />
            </Suspense>
          </ProtectedRouteAdmin>
        ),
        errorElement: <ErrorPage />,
      },
    ],
    errorElement: <ErrorPage />,
  },
  {
    path: "/admin/login",
    element: (
      <Suspense fallback={<Loading />}>
        <AdminLogin />
      </Suspense>
    ),
    errorElement: <ErrorPage />,
  },
]);

export default router;
