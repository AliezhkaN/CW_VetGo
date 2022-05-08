package com.nahorniak.controller.servlets.outOfControl.forgotPassword;

import com.nahorniak.DAO.ConnectionPool;
import com.nahorniak.DAO.UserDAO;
import com.nahorniak.DAO.entity.User;
import com.nahorniak.util.mail.MailSender;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Random;

@WebServlet(name = "forgotPassword", value = "/forgotPassword")
public class ForgotPasswordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(404);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        UserDAO userDAO = UserDAO.getInstance();
        HttpSession session = request.getSession();

        String email = request.getParameter("email");

        if(email!=null){
            try (Connection connection = connectionPool.getConnection()){
                User user = userDAO.getUserByEmail(email,connection);
                if(user != null){
                    Random random = new Random();
                    int number = random.nextInt(99999);
                    String code = String.format("%06d", number);

                    String message = "Recovery code to change password :" + code;
                    String subject = "Forgot password";

                    new MailSender(user, message, subject).start();

                    session.setAttribute("email", email);
                    session.setAttribute("code", code);

                }else {
                    session.setAttribute("forgotPasswordError","There is no user with this email !");
                }
                response.sendRedirect("/application");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
