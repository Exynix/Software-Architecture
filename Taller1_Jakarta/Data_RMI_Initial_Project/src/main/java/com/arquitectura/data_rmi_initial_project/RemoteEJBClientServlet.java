package com.arquitectura.data_rmi_initial_project;

import com.arquitectura.data_rmi_initial_project.ExampleService;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/testRemoteEJB")
public class RemoteEJBClientServlet extends HttpServlet {

    @EJB
    private ExampleService remoteService;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<String> data = remoteService.fetchDatabaseData();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Remote EJB Data:</h1>");
        for (String item : data) {
            out.println("<p>" + item + "</p>");
        }
        out.println("</body></html>");
    }
}