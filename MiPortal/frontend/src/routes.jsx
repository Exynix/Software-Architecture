import React from "react"
import { Routes, Route} from "react-router-dom";
import LoginPage from "./authentication/LoginPage.jsx";
import Checkout from "./payments/Checkout.js";

const AppRoutes = () => (
    <Routes>
        <Route path = "/login" element={<LoginPage />} />
        <Route path = "/checkout" element={<Checkout />} />
    </Routes>
);

export default AppRoutes