package com.nahorniak.controller.servlets.doctor;

import com.nahorniak.DAO.AppointmentDAO;
import com.nahorniak.DAO.ConnectionPool;
import com.nahorniak.DAO.entity.Appointment;
import com.nahorniak.DAO.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "timetable", value = "/timetable")
public class TimetableServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        AppointmentDAO appointmentDAO = AppointmentDAO.getInstance();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        try (Connection connection = connectionPool.getConnection()){
            List<Appointment> appointments = appointmentDAO.getAll(user.getId(),connection);
            System.out.println(appointments);
            session.setAttribute("appointments",appointments);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("WEB-INF/jsp/doctor/timetable.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
