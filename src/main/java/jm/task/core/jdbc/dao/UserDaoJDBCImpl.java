package jm.task.core.jdbc.dao;

import com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    private static UserDao userDao;

    private UserDaoJDBCImpl() {
    }

    public static UserDao getUserDao() {
        if (userDao == null) {
            userDao = new UserDaoJDBCImpl();
        }
        return userDao;
    }

    public void createUsersTable() {
        try (Connection connection = Util.getMySQLConnection()) {
            String sql = "create table if not exists USERS (ID bigint not null AUTO_INCREMENT, NAME varchar(50), " +
                    "LAST_NAME varchar(50), AGE integer, primary key (ID))";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection connection = Util.getMySQLConnection()) {
            String sql = "drop table if exists USERS";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getMySQLConnection()) {
            String sql = "insert into USERS (name, last_name, age) values (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getMySQLConnection()) {
            String sql = "delete from USERS where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Connection connection = Util.getMySQLConnection()) {
            String sql = "select * from USERS";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String name = rs.getString("name");
                String lastName = rs.getString("last_name");
                byte age = rs.getByte("age");
                list.add(new User(name, lastName, age));
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getMySQLConnection()) {
            String sql = "delete from USERS";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
