package com.nahorniak.controller.servlets.common.userEdit;

import com.nahorniak.DAO.ConnectionPool;
import com.nahorniak.DAO.UserDAO;
import com.nahorniak.DAO.entity.User;
import com.nahorniak.util.Encryption;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.Console;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "changePassword", value = "/changePassword")
public class ChangePasswordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(404);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        UserDAO userDao = UserDAO.getInstance();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmNewPassword = request.getParameter("confirmNewPassword");

        if(notNull(currentPassword,newPassword,confirmNewPassword)){
            if(!Encryption.md5(currentPassword).equals(user.getPassword())){
                session.setAttribute("changePasswordMessage", "Password is not correct");
                response.sendRedirect("profile");
            }else {
                if(!newPassword.equals(confirmNewPassword)){
                    session.setAttribute("changePasswordMessage", "Please confirm your new password");
                    response.sendRedirect("profile");
                }else {
                    String encryptedPassword = Encryption.md5(newPassword);
                    try (Connection connection = connectionPool.getConnection()){
                        user.setPassword(encryptedPassword);
                        userDao.updateUser(user,connection);
                        session.removeAttribute("user");
                        session.setAttribute("message","Password successfully changed");
                        response.sendRedirect("/application");
                    } catch (SQLException e) {
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
