package com.nahorniak.controller.servlets.admin.changeUserStatus;

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


/**
 * UnblockCustomer -> used for unblocking the appropriate user
 *
 * @author Oleh Nahorniak
 */

@WebServlet(name = "unblockCustomer", value = "/unblockCustomer")
public class UnblockCustomer extends HttpServlet {

    /**
     * doGet method - sendRedirect to error-page
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(404);
    }


    /**
     * doPost method - unblocks appropriate customer by his id
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        UserDAO userDAO = UserDAO.getInstance();
        HttpSession session = request.getSession();

        String email = request.getParameter("email");
        if (email!= null){
            try (Connection connection = connectionPool.getConnection()){
                User user = userDAO.getUserByEmail(email,connection);
                user.setBlocked(false);
                userDAO.updateUser(user,connection);
                session.setAttribute("message",user.getEmail() + " has been unblocked");
                String message = "Administrator unblocked you !";
                String subject = "Account unblocked";
                new MailSender(user,message,subject).start();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        response.sendRedirect("customers");
    }
}
