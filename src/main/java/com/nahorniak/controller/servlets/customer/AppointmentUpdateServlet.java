package com.nahorniak.controller.servlets.customer;

import com.nahorniak.DAO.AppointmentDAO;
import com.nahorniak.DAO.ConnectionPool;
import com.nahorniak.DAO.entity.Appointment;
import com.nahorniak.DAO.entity.Status;
import com.nahorniak.DAO.entity.User;
import com.nahorniak.util.reCaptcha.VerifyUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.function.Predicate;

@WebServlet(name = "appointmentUpdate", value = "/appointmentUpdate")
public class AppointmentUpdateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendError(404);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ConnectionPool connectionPool = ConnectionPool.getInstance();
        AppointmentDAO appointmentDAO = AppointmentDAO.getInstance();
        HttpSession session = request.getSession();
        String id = request.getParameter("appointmentId");
        System.out.println(id);
        User user = (User) session.getAttribute("user");
        String kind = request.getParameter("kind");
        String name = request.getParameter("name");
        String datetime = request.getParameter("datetime");
        String doctor = request.getParameter("doctor");

        if(notNull(kind,name,datetime,doctor,id)){
            try (Connection connection = connectionPool.getConnection()){
                System.out.println(doctor);
                int doctorId = Integer.parseInt(doctor);
                int appointmentId = Integer.parseInt(id);

                final long time = 1800000;

                datetime = datetime.replace("T"," ")+":00";
                Timestamp timestamp = Timestamp.valueOf(datetime);
                Appointment.Builder builder = new Appointment.Builder();
                Appointment appointment = builder
                        .withId(appointmentId)
                        .withUserId(user.getId())
                        .withDoctorId(doctorId)
                        .withStatus("ACTIVE")
                        .withPetKind(kind)
                        .withPetName(name)
                        .withAppointmentDate(timestamp)
                        .build();

                List<Appointment> appointments = appointmentDAO.getAll(doctorId,connection);
                System.out.println(appointments);
                Predicate<Timestamp> predicate = x-> {
                    Long difference = getDifference(x,timestamp);
                    return difference >= 0 && difference<= time;
                };

                long count = appointments.stream().map(Appointment::getAppointmentDate).filter(predicate).count();
                if(count > 0){
                    session.setAttribute("appointmentError","Chosen date is unavailable, please try to pick date " +
                            "with 30 min difference");
                }else {
                    appointmentDAO.updateAppointment(appointment,connection);
                    session.setAttribute("message","Appointment successfully has been edited");
                }

            } catch (IllegalArgumentException e){
                session.setAttribute("appointmentError","Please choose date and time correctly");
            } catch (SQLException e) {
                e.printStackTrace();
            }

        response.sendRedirect("appointment");
    }
    }

    private Long getDifference(Timestamp o1,Timestamp o2){
        return Math.abs(o1.getTime()-o2.getTime());
    }

    private boolean notNull(String ... params){
        for (String param : params){
            if(param == null) return false;
        }
        return true;
    }

}
