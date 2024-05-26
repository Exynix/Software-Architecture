import React from "react"
import { Routes, Route} from "react-router-dom";
import LoginPage from "./authentication/LoginPage.jsx";
import Checkout from "./payments/Checkout.tsx"
import SignupPage from "./authentication/SignupPage.jsx";

const AppRoutes = () => (
    <Routes>
        <Route path = "/login" element={<LoginPage />} />
        <Route path = "/signup" element={<SignupPage />} />
        <Route path = "/checkout" element={<Checkout />} />
    </Routes>
);

export default AppRoutes