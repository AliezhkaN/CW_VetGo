package com.nahorniak.controller.servlets.admin;

import com.nahorniak.DAO.ConnectionPool;
import com.nahorniak.DAO.UserDAO;
import com.nahorniak.DAO.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * CustomersServlet -> used for getting and setting all customers from database on the page
 *
 * @author Oleh Nahorniak
 */
@WebServlet(name = "customers", value = "/customers")
public class CustomersServlet extends HttpServlet {

    /**
     * doGet method -> gets list of Users with role CUSTOMER from database and set that list as attribute in request and
     * send it on vue layer
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        UserDAO userDAO = UserDAO.getInstance();
        List<User> users;

        try (Connection connection = connectionPool.getConnection()){
            users = userDAO.getAll(connection);
            System.out.println(users);
            request.setAttribute("users",users);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("WEB-INF/jsp/admin/customers.jsp").forward(request,response);
    }

    /**
     * doPost method do same as doGet method
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
