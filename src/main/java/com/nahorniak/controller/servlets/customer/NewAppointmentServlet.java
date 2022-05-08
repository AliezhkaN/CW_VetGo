package com.nahorniak.controller.servlets.customer;

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
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "newAppointment", value = "/newAppointment")
public class NewAppointmentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.sendError(404);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        AppointmentDAO appointmentDAO = AppointmentDAO.getInstance();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String kind = request.getParameter("kind");
        String name = request.getParameter("name");
        String datetime = request.getParameter("datetime");

        if(notNull(kind,name,datetime)){
            try (Connection connection = connectionPool.getConnection()){
                datetime = datetime.replace("T"," ")+":00";
                Timestamp timestamp = Timestamp.valueOf(datetime);
                Appointment.Builder builder = new Appointment.Builder();
                Appointment appointment = builder
                        .withUserId(user.getId())
                        .withPetKind(kind)
                        .withPetName(name)
                        .withAppointmentDate(timestamp)
                        .build();

                appointmentDAO.insertAppointment(appointment,connection);
                session.setAttribute("message","Appointment successfully has been made");

            } catch (IllegalArgumentException e){
                session.setAttribute("appointmentError","Please choose date and time correctly");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        response.sendRedirect("appointment");

    }

    private boolean notNull(String ... params){
        for (String param : params){
            if(param == null) return false;
        }
        return true;
    }
}
