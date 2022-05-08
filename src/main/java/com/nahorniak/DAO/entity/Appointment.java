package com.nahorniak.DAO.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Appointment implements Serializable {
    private static final long serialVersionUID = 4748376300012472146L;
    private int id;
    private int userId;
    private Status status;
    private String petKind;
    private String petName;
    private Timestamp appointmentDate;

    public static class Builder {

        private final Appointment appointment;

        public Builder() {
            appointment = new Appointment();
        }

        public Appointment.Builder withId(int id) {
            appointment.id = id;
            return this;
        }

        public Appointment.Builder withUserId(int userId) {
            appointment.userId = userId;
            return this;
        }

        public Appointment.Builder withStatus(String status) {
            appointment.status = Status.valueOf(status);
            return this;
        }


        public Appointment.Builder withPetKind(String petKind) {
            appointment.petKind = petKind;
            return this;
        }


        public Appointment.Builder withPetName(String petName) {
            appointment.petName = petName;
            return this;
        }

        public Appointment.Builder withAppointmentDate(Timestamp appointmentDate) {
            appointment.appointmentDate = appointmentDate;
            return this;
        }

        public Appointment build() {
            return appointment;
        }

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getPetKind() {
        return petKind;
    }

    public void setPetKind(String petKind) {
        this.petKind = petKind;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public Timestamp getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Timestamp appointmentDate) {
        this.appointmentDate = appointmentDate;
    }
}
