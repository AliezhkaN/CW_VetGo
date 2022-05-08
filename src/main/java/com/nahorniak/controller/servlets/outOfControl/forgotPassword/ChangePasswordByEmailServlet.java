package com.nahorniak.controller.servlets.outOfControl.forgotPassword;

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

@WebServlet(name = "changePasswordByEmail", value = "/changePasswordByEmail")
public class ChangePasswordByEmailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(404);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        UserDAO userDAO = UserDAO.getInstance();

        String email = (String) session.getAttribute("email");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        if(notNull(email,newPassword,confirmPassword)){
            if(!newPassword.equals(confirmPassword)){
                session.setAttribute("changePasswordError","Please confirm your password");
                session.setAttribute("changePassword",true);
            }else {
                try (Connection connection = connectionPool.getConnection()){
                    User user = userDAO.getUserByEmail(email,connection);
                    String encryptedPassword = Encryption.md5(newPassword);
                    user.setPassword(encryptedPassword);
                    userDAO.updateUser(user,connection);
                    session.setAttribute("message","Password successfully changed");
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
        response.sendRedirect("/application");
    }

    private boolean notNull(String ... params){
        for (String param : params){
            if(param == null) return false;
        }
        return true;
    }
}
