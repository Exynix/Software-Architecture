import React from "react"
import { Routes, Route} from "react-router-dom";
import LoginPage from "./authentication/LoginPage.jsx";

const AppRoutes = () => (
    <Routes>
        <Route path = "/login" element={<LoginPage />} />
    </Routes>
);

export default AppRoutes