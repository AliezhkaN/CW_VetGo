package com.nahorniak.DAO;

import com.nahorniak.DAO.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.nahorniak.DAO.constants.Entity.*;


public class UserDAO {


    private static String GET_USER = "SELECT u.id, email, password, first_name, last_name, phone,is_blocked, r.role " +
            "FROM users u JOIN roles r ON r.id = u.role_id WHERE email=? AND password =?";

    private static String GET_USER_BY_EMAIl = "SELECT u.id, email, password, first_name, last_name, phone,is_blocked, r.role " +
            "FROM users u JOIN roles r ON r.id = u.role_id WHERE email=?";

    private static String GET_ALL = "SELECT u.id, email, password, first_name, last_name, phone,is_blocked, r.role " +
            "FROM users u JOIN roles r ON r.id = u.role_id WHERE r.role = 'CUSTOMER'";

    private static String UPDATE_USER = "UPDATE users " +
            "SET email=?, password=?, first_name=?, last_name=?, phone=? ,is_blocked=?" +
            "WHERE id=?";

    private static String INSERT_USER = "INSERT INTO users(email, password, first_name, last_name, phone) " +
            "VALUES (?,?,?,?,?)";

    private static UserDAO userDAO;

    public static synchronized UserDAO getInstance() {
        if (userDAO == null) {
            userDAO = new UserDAO();
        }
        return userDAO;
    }

    private UserDAO() {
    }

    public User getUser(String email, String password, Connection connection) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(GET_USER)) {
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            User user = null;
            if (rs.next()) {
                UserMapper userMapper = new UserMapper();
                user = userMapper.map(rs);
            }
            ConnectionPool.close(rs);
            return user;
        } catch (SQLException e) {
            ConnectionPool.rollback(connection);
            e.printStackTrace();
            throw e;
        } finally {
            ConnectionPool.commit(connection);
        }
    }

    public User getUserByEmail(String email,Connection connection)throws SQLException{
        try (PreparedStatement ps = connection.prepareStatement(GET_USER_BY_EMAIl)) {
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            User user = null;
            if (rs.next()) {
                UserMapper userMapper = new UserMapper();
                user = userMapper.map(rs);
            }
            ConnectionPool.close(rs);
            return user;
        } catch (SQLException e) {
            ConnectionPool.rollback(connection);
            e.printStackTrace();
            throw e;
        } finally {
            ConnectionPool.commit(connection);
        }
    }

    public List<User> getAll(Connection connection) throws SQLException {
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(GET_ALL);
            UserMapper userMapper = new UserMapper();
            while (rs.next()) {
                User person = userMapper.map(rs);
                users.add(person);
            }
            ConnectionPool.close(rs);
        } catch (SQLException e) {
            ConnectionPool.rollback(connection);
            throw e;
        } finally {
        ConnectionPool.commit(connection);
        }
        return users;
    }

    public void updateUser(User user, Connection connection) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(UPDATE_USER)){
            ps.setString(1,user.getEmail());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getFirstName());
            ps.setString(4,user.getLastName());
            ps.setString(5,user.getPhoneNumber());
            ps.setBoolean(6,user.getIsBlocked());
            ps.setInt(7,user.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            ConnectionPool.rollback(connection);
            e.printStackTrace();
            throw e;
        }finally {
            ConnectionPool.commit(connection);
        }
    }

    public void insertUser(User user,Connection connection) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(INSERT_USER)){
            ps.setString(1,user.getEmail());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getFirstName());
            ps.setString(4,user.getLastName());
            ps.setString(5,user.getPhoneNumber());

            ps.executeUpdate();
        } catch (SQLException e) {
            ConnectionPool.rollback(connection);
            e.printStackTrace();
            throw e;
        }finally {
            ConnectionPool.commit(connection);
        }
    }

    private static class UserMapper implements EntityMapper<User> {

        @Override
        public User map(ResultSet rs) {
            User.Builder builder = new User.Builder();
            User person = null;
            try {
                person = builder.withId(rs.getInt(ENTITY__ID))
                        .withFirstName(rs.getString(USER__FIRST_NAME))
                        .withLastName(rs.getString(USER__LAST_NAME))
                        .withPassword(rs.getString(USER__PASSWORD))
                        .withRole(rs.getString(USER__ROLE))
                        .withEmail(rs.getString(USER__EMAIL))
                        .withPhoneNumber(rs.getString(USER__PHONE_NUMBER))
                        .withStatus(rs.getBoolean(USER__IS__BLOCKED))
                        .build();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return person;
        }
    }
}
