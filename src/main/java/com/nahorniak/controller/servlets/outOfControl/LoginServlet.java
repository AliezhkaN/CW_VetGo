package com.nahorniak.controller.servlets.outOfControl;

import com.nahorniak.DAO.ConnectionPool;
import com.nahorniak.DAO.UserDAO;
import com.nahorniak.DAO.entity.Role;
import com.nahorniak.DAO.entity.User;
import com.nahorniak.util.Encryption;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "login", value = "/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/application");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        UserDAO userDAO = UserDAO.getInstance();

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        if (email != null && password != null){
            try (Connection connection = connectionPool.getConnection()) {
                Role role = Role.UNKNOWN;
                String encryptedPassword = Encryption.md5(password);
                User user = userDAO.getUser(email, encryptedPassword, connection);
                if(user != null){
                    if(user.getIsBlocked() == false){
                        session.setAttribute("user", user);
                        role = user.getRole();
                        moveToPage(request, response,role);
                    }else {
                        session.setAttribute("errorMessage","You are blocked!");
                        response.sendRedirect("/application");
                    }

                }else {
                    session.setAttribute("errorMessage","login or password is not correct");
                    response.sendRedirect("/application");
                }
            } catch (SQLException e) {
            }
        }else {
            session.setAttribute("errorMessage","login or password is not correct");
            response.sendRedirect("/application");
        }
    }

    private void moveToPage(HttpServletRequest req, HttpServletResponse resp, Role role) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (role == Role.CUSTOMER || role == Role.ADMIN || role == Role.DOCTOR) {
            resp.sendRedirect("profile");
        } else if (role == Role.UNKNOWN) {
            session.setAttribute("errorMessage","login or password is not correct");
            resp.sendRedirect("/application");
        }
    }
}
