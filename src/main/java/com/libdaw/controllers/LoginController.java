package com.libdaw.controllers;

import com.libdaw.models.Login;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "LoginController", value = "/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Login log = new Login();
        log.setUsername(username);
        log.setPassword(password);

        boolean status = log.validateLogin();

        if (status) {
            // Create session and store user information
            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            session.setAttribute("status", true);

            // Set session timeout (30 minutes)
            session.setMaxInactiveInterval(30*60);

            // Redirect to list-libro.jsp through the appropriate controller
            resp.sendRedirect("lista?action=list");
        } else {
            // Add error message to request
            req.setAttribute("error", "Usuario o contraseña incorrectos");

            // Forward back to login page
            RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
            rd.forward(req, resp);
        }
    }

}
