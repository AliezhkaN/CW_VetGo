package com.nahorniak.DAO.constants;

/**
 * all database fields
 *
 * @author Oleh Nahorniak.
 */
public final class Entity {

    public static final String ENTITY__ID = "id";
    public static final String USER__EMAIL = "email";
    public static final String USER__PASSWORD = "password";
    public static final String USER__FIRST_NAME = "first_name";
    public static final String USER__LAST_NAME = "last_name";
    public static final String USER__PHONE_NUMBER = "phone";
    public static final String USER__ROLE = "role";
    public static final String USER__IS__BLOCKED = "is_blocked";

    public static final String APPOINTMENT__USER__ID = "user_id";
    public static final String APPOINTMENT__DOCTOR__ID = "doctor_id";
    public static final String APPOINTMENT__DATE = "appointment_date";
    public static final String APPOINTMENT__KIND__OF__PET = "kind_of_pet";
    public static final String APPOINTMENT__PET__NAME = "pet_name";
    public static final String APPOINTMENT__STATUS = "status";

}
