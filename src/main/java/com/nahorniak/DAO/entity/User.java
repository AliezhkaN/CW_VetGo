package com.nahorniak.DAO.entity;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = -2244557749450461805L;
    private int id;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Role role;
    private boolean isBlocked;


    public static class Builder {

        private final User person;

        public Builder() {
            person = new User();
        }

        public Builder withEmail(String email) {
            person.email = email;
            return this;
        }

        public Builder withPassword(String password) {
            person.password = password;
            return this;
        }

        public Builder withId(int id) {
            person.id = id;
            return this;
        }

        public Builder withFirstName(String firstName) {
            person.firstName = firstName;
            return this;
        }


        public Builder withLastName(String lastName) {
            person.lastName = lastName;
            return this;
        }


        public Builder withPhoneNumber(String phoneNumber) {
            person.phoneNumber = phoneNumber;
            return this;
        }

        public Builder withStatus(boolean isBlocked) {
            person.isBlocked = isBlocked;
            return this;
        }

        public Builder withRole(String roleName) {
            Role role = null;
            for (Role value : Role.values()) {
                if (value.equalsTo(roleName)) role = value;
            }
            person.role = role;
            return this;
        }

        public User build() {
            return person;
        }

    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean getIsBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", role=" + role +
                '}';
    }
}
