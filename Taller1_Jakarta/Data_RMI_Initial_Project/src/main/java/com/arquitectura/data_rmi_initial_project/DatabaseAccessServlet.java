package com.arquitectura.data_rmi_initial_project;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/databaseAccess")
public class DatabaseAccessServlet extends HttpServlet {

    @Resource(lookup = "java:/MySqlDS")
    private DataSource dataSource;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM sakila.actor");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                out.println("<p>Data: " + rs.getString("actor_id") + "</p>");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        out.println("</body></html>");
    }
}