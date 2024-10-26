package com.example.guvi.controller;

import com.example.guvi.model.User;
import com.example.guvi.service.UserService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/user")
public class UserController extends HttpServlet {
    private UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("register".equals(action)) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setEmail(email);
            try {
                if (userService.registerUser(user)) {
                    response.sendRedirect("login.jsp");
                } else {
                    response.sendRedirect("register.jsp?error=Registration failed");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if ("login".equals(action)) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            try {
                User user = userService.loginUser(username, password);
                if (user != null) {
                    request.getSession().setAttribute("user", user);
                    response.sendRedirect("profile.jsp");
                } else {
                    response.sendRedirect("login.jsp?error=Invalid credentials");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
