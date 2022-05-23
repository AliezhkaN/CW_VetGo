package com.nahorniak.controller.servlets.common.userEdit;

import com.nahorniak.DAO.ConnectionPool;
import com.nahorniak.DAO.UserDAO;
import com.nahorniak.DAO.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * ChangeFullNameServlet -> used for changing full name in personal profile
 *
 * @author Oleh Nahorniak
 */
@WebServlet(name = "changeFullName", value = "/changeFullName")
public class ChangeFullNameServlet extends HttpServlet {

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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        UserDAO userDAO = UserDAO.getInstance();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");

        if(notNull(firstName,lastName)){
            try (Connection connection = connectionPool.getConnection()){
                user.setFirstName(firstName);
                user.setLastName(lastName);
                userDAO.updateUser(user,connection);
                session.setAttribute("user",user);
                session.setAttribute("message","Full name successfully changed");
                response.sendRedirect("profile");
            } catch (SQLException e) {
                e.printStackTrace();
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
