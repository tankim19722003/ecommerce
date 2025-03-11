import { createRoot } from "react-dom/client";
import { RouterProvider } from "react-router-dom";
import "./index.css";
import App from "./App.jsx";
import { ThemeProvider } from "./Provider/ThemeProvider.jsx";
import { AuthContextProvider } from "./contexts/User/AuthContext.jsx";
import { ShopContextProvider } from "./contexts/User/ShopContext.jsx";
import router from "./routes/index.jsx";
import { AddressProvider } from "./contexts/layout/AddressContext.jsx";

createRoot(document.getElementById("root")).render(
  <AuthContextProvider>
    <ShopContextProvider>
      <AddressProvider>
        <ThemeProvider>
          <RouterProvider router={router} />
        </ThemeProvider>
      </AddressProvider>
    </ShopContextProvider>
  </AuthContextProvider>
);
