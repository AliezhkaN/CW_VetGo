package com.nahorniak.controller.servlets.customer;

import com.nahorniak.DAO.AppointmentDAO;
import com.nahorniak.DAO.ConnectionPool;
import com.nahorniak.DAO.UserDAO;
import com.nahorniak.DAO.entity.Appointment;
import com.nahorniak.DAO.entity.Status;
import com.nahorniak.DAO.entity.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "appointment", value = "/appointment")
public class AppointmentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        ConnectionPool connectionPool = ConnectionPool.getInstance();
        AppointmentDAO appointmentDAO = AppointmentDAO.getInstance();
        UserDAO userDAO = UserDAO.getInstance();

        try (Connection connection = connectionPool.getConnection()){
            Appointment appointment = appointmentDAO.getActive(user,connection);
            List<User> doctors = userDAO.getAllDoctors(connection);
            request.setAttribute("doctors",doctors);
            if(appointment != null){
                request.setAttribute("appointment",appointment);
                User doctor = userDAO.getDoctorById(appointment.getDoctorId(),connection);
                request.setAttribute("doctor",doctor);
            }
            request.getRequestDispatcher("WEB-INF/jsp/customer/appointment.jsp").forward(request,response);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        AppointmentDAO appointmentDAO = AppointmentDAO.getInstance();
        HttpSession session = request.getSession();
        String id = request.getParameter("appointmentId");
        System.out.println(id);
        if(id != null){
            int appointmentId = Integer.parseInt(id);
            try (Connection connection = connectionPool.getConnection()){
                Appointment appointment = appointmentDAO.getById(appointmentId,connection);
                if(appointment!= null){
                    appointment.setStatus(Status.valueOf("CLOSED"));
                    appointmentDAO.updateAppointment(appointment,connection);
                    session.setAttribute("message","Appointment successfully canceled");

                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        response.sendRedirect("appointment");
    }
}
