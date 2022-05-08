package com.nahorniak.controller.servlets.customer;

import com.nahorniak.DAO.AppointmentDAO;
import com.nahorniak.DAO.ConnectionPool;
import com.nahorniak.DAO.UserDAO;
import com.nahorniak.DAO.entity.Appointment;
import com.nahorniak.DAO.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "appointmentHistory", value = "/appointmentHistory")
public class AppointmentHistoryServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        AppointmentDAO appointmentDAO = AppointmentDAO.getInstance();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        try (Connection connection = connectionPool.getConnection()){
            List<Appointment> appointmentList = appointmentDAO.getAll(user,connection);
            System.out.println(appointmentList);
            request.setAttribute("appointments",appointmentList);
            request.getRequestDispatcher("WEB-INF/jsp/customer/appointment-history.jsp").forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
