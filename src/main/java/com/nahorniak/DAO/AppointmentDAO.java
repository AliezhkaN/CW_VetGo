package com.nahorniak.DAO;

import com.nahorniak.DAO.entity.Appointment;
import com.nahorniak.DAO.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.nahorniak.DAO.constants.Entity.*;

public class AppointmentDAO {

    private static final String GET_ACTIVE_APPOINTMENT = "SELECT a.id, user_id, appointment_date, kind_of_pet, pet_name, s.status ,doctor_id " +
            "FROM appointment a join statuses s on s.id = a.status_id WHERE s.status = 'ACTIVE' and user_id = ?";

    private static final String GET_APPOINTMENT_BY_ID = "SELECT a.id, user_id, appointment_date, kind_of_pet, pet_name, s.status,doctor_id " +
            "FROM appointment a join statuses s on s.id = a.status_id WHERE a.id = ?";

    private static final String GET_ALL_BY_USER_ID = "SELECT a.id, user_id, appointment_date, kind_of_pet, pet_name, s.status,doctor_id " +
            "FROM appointment a join statuses s on s.id = a.status_id WHERE user_id = ?";

    private static final String INSERT_APPOINTMENT = "INSERT INTO appointment(user_id, appointment_date, kind_of_pet, pet_name,doctor_id) " +
            " VALUES (?, ?, ?, ?,?)";

    private static final String UPDATE_APPOINTMENT = "UPDATE public.appointment " +
            " SET user_id=?, appointment_date=?, kind_of_pet=?, pet_name=?, status_id= (select id from statuses where status =?),doctor_id=? " +
            " WHERE id=?;";

    private static final String GET_ALL_ACTIVE = "SELECT a.id, user_id, appointment_date, kind_of_pet, pet_name, s.status, doctor_id " +
            "FROM appointment a join statuses s on s.id = a.status_id WHERE s.status = 'ACTIVE' and doctor_id = ?";


    private static AppointmentDAO appointmentDAO;
    public static synchronized AppointmentDAO getInstance() {
        if (appointmentDAO == null) {
            appointmentDAO = new AppointmentDAO();
        }
        return appointmentDAO;
    }

    private AppointmentDAO() {
    }

    public Appointment getActive(User user, Connection connection) throws SQLException {

        try (PreparedStatement ps = connection.prepareStatement(GET_ACTIVE_APPOINTMENT)){
            ps.setInt(1,user.getId());
            Appointment appointment = null;
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                AppointmentMapper appointmentMapper = new AppointmentMapper();
                appointment = appointmentMapper.map(rs);
            }
            return appointment;
        } catch (SQLException e) {
            ConnectionPool.rollback(connection);
            e.printStackTrace();
            throw e;
        } finally {
            ConnectionPool.commit(connection);
        }
    }

    public Appointment getById(int id, Connection connection) throws SQLException {

        try (PreparedStatement ps = connection.prepareStatement(GET_APPOINTMENT_BY_ID)){
            ps.setInt(1,id);
            Appointment appointment = null;
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                AppointmentMapper appointmentMapper = new AppointmentMapper();
                appointment = appointmentMapper.map(rs);
            }
            return appointment;
        } catch (SQLException e) {
            ConnectionPool.rollback(connection);
            e.printStackTrace();
            throw e;
        } finally {
            ConnectionPool.commit(connection);
        }
    }

    public List<Appointment> getAll(int doctorId,Connection connection) throws SQLException {
        List<Appointment> appointments = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_ACTIVE)) {
            ps.setInt(1,doctorId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AppointmentMapper mapper = new AppointmentMapper();
                Appointment appointment = mapper.map(rs);
                appointments.add(appointment);
            }
            ConnectionPool.close(rs);
            return appointments;
        } catch (SQLException e) {
            ConnectionPool.rollback(connection);
            throw e;
        } finally {
            ConnectionPool.commit(connection);
        }
    }

    public List<Appointment> getAll(User user, Connection connection) throws SQLException {
        List<Appointment> appointments = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(GET_ALL_BY_USER_ID)) {
            ps.setInt(1,user.getId());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                AppointmentMapper mapper = new AppointmentMapper();
                Appointment appointment = mapper.map(rs);
                appointments.add(appointment);
            }
            ConnectionPool.close(rs);
            return appointments;
        } catch (SQLException e) {
            ConnectionPool.rollback(connection);
            throw e;
        } finally {
            ConnectionPool.commit(connection);
        }
    }

    public void updateAppointment(Appointment appointment,Connection connection) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_APPOINTMENT)){
            System.out.println(1111);
            ps.setInt(1,appointment.getUserId());
            ps.setTimestamp(2,appointment.getAppointmentDate());
            ps.setString(3,appointment.getPetKind());
            ps.setString(4, appointment.getPetName());
            ps.setString(5,appointment.getStatus().toString());
            ps.setInt(6,appointment.getDoctorId());
            ps.setInt(7,appointment.getId());
            ps.executeUpdate();
            System.out.println(222);
        } catch (SQLException e) {
            ConnectionPool.rollback(connection);
            e.printStackTrace();
            throw e;
        }finally {
            ConnectionPool.commit(connection);
        }
    };

    public void insertAppointment(Appointment appointment, Connection connection){
        try (PreparedStatement ps = connection.prepareStatement(INSERT_APPOINTMENT)){
            ps.setInt(1,appointment.getUserId());
            ps.setTimestamp(2,appointment.getAppointmentDate());
            ps.setString(3,appointment.getPetKind());
            ps.setString(4,appointment.getPetName());
            ps.setInt(5,appointment.getDoctorId());
            ps.executeUpdate();
        } catch (SQLException e) {
            ConnectionPool.rollback(connection);
            e.printStackTrace();
        }finally {
            ConnectionPool.commit(connection);
        }
    }

    private static class AppointmentMapper implements EntityMapper<Appointment> {

        @Override
        public Appointment map(ResultSet rs) {
            Appointment.Builder builder = new Appointment.Builder();
            Appointment appointment = null;
            try {
                appointment = builder.withId(rs.getInt(ENTITY__ID))
                        .withUserId(rs.getInt(APPOINTMENT__USER__ID))
                        .withAppointmentDate(rs.getTimestamp(APPOINTMENT__DATE))
                        .withPetKind(rs.getString(APPOINTMENT__KIND__OF__PET))
                        .withPetName(rs.getString(APPOINTMENT__PET__NAME))
                        .withAppointmentDate(rs.getTimestamp(APPOINTMENT__DATE))
                        .withStatus(rs.getString(APPOINTMENT__STATUS))
                        .withDoctorId(rs.getInt(APPOINTMENT__DOCTOR__ID))
                        .build();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return appointment;
        }
    }
}
