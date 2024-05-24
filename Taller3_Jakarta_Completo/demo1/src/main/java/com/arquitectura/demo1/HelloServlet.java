package com.arquitectura.demo1;

import java.io.*;

import jakarta.faces.bean.ManagedBean;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
    }

    @ManagedBean
    @SessionScoped
    public static class LoginBean implements Serializable {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String login() {
            // Add your login logic here (authentication against a database, for example)
            if (username.equals("user") && password.equals("pass")) {
                return "Products"; // Navigate to Products page upon successful login
            } else {
                return "Login"; // Stay on the login page if authentication fails
            }
        }
    }
}