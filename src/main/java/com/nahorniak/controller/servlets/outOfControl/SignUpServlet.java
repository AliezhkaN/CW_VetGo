package com.nahorniak.controller.servlets.outOfControl;

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

@WebServlet(name = "signUp", value = "/signUp")
public class SignUpServlet extends HttpServlet {
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
        String firstName =request.getParameter("firstName");
        String lastName =request.getParameter("lastName");
        String phoneNumber =request.getParameter("phoneNumber");
        String password =request.getParameter("password");

        if(notNull(email,firstName,lastName,phoneNumber,password)){
            try (Connection connection = connectionPool.getConnection()){

                String encryptedPassword = Encryption.md5(password);

                User.Builder builder = new User.Builder();
                User user = builder.withEmail(email)
                        .withFirstName(firstName)
                        .withLastName(lastName)
                        .withPhoneNumber(phoneNumber)
                        .withPassword(encryptedPassword)
                        .build();

                userDAO.insertUser(user,connection);
                session.setAttribute("message","Account Successfully signed up!");
                response.sendRedirect("/application");
            } catch (SQLException e) {
                String msg = e.getMessage();

                if(msg.contains("email")){
                    session.setAttribute("signUpError","User with such email already exist");
                } else if(msg.contains("phone")){
                    session.setAttribute("signUpError","User with such phone already exist");
                }
                response.sendRedirect("/application");

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
