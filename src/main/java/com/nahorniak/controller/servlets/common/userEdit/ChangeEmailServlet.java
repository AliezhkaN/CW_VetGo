package com.nahorniak.controller.servlets.common.userEdit;

import com.nahorniak.DAO.ConnectionPool;
import com.nahorniak.DAO.UserDAO;
import com.nahorniak.DAO.entity.User;
import com.nahorniak.util.Encryption;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "changeEmail", value = "/changeEmail")
public class ChangeEmailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(404);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        UserDAO userDAO = UserDAO.getInstance();
        HttpSession session = request.getSession();

        User user = (User) session.getAttribute("user");

        String newEmail = request.getParameter("newEmail");
        String confirmNewEmail = request.getParameter("confirmNewEmail");
        String confirmPassword = request.getParameter("confirmPassword");

        if(notNull(newEmail,confirmNewEmail,confirmPassword)) {
            String encryptedPassword = Encryption.md5(confirmPassword);

            if (!newEmail.equals(confirmNewEmail)) {
                session.setAttribute("changeEmailMessage", "Please Confirm your email");
                response.sendRedirect("profile");
            } else {

                if (!encryptedPassword.equals(user.getPassword())) {
                    session.setAttribute("changeEmailMessage", "Password is not correct");
                    response.sendRedirect("profile");
                }else {
                    String oldEmail = user.getEmail();
                    try (Connection connection = connectionPool.getConnection()){
                        user.setEmail(newEmail);
                        userDAO.updateUser(user,connection);
                        session.removeAttribute("user");
                        session.setAttribute("message","Email successfully changed");
                        response.sendRedirect("/application");
                    } catch (SQLException e) {
                        String message = e.getMessage();
                        if(message.contains("email")){
                            user.setEmail(oldEmail);
                            session.setAttribute("changeEmailMessage", "User with this email already exists");
                            response.sendRedirect("profile");
                        }
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private boolean notNull(String ... params){
        for (String param : params){
            if(param == null) return false;
            }
        return true;
    }

}
