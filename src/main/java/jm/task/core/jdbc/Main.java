package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.Connection;
import java.sql.SQLException;

import static jm.task.core.jdbc.util.Util.getMySQLConnection;

public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь

//        UserDao userDao = new UserDaoHibernateImpl();
//        //userDao.dropUsersTable();
//        userDao.createUsersTable();
//        userDao.saveUser("Scot", "Sullivan", (byte) 27);
//        //System.out.println(userDao.getAllUsers());

        UserService us = new UserServiceImpl();
        us.createUsersTable();
        us.saveUser("Tom", "Smith", (byte) 40);
        System.out.println("User с именем – Tom добавлен в базу данных");

        us.saveUser("Scot", "Sullivan", (byte) 27);
        System.out.println("User с именем – Scot добавлен в базу данных");

        us.saveUser("Edward", "Jackson", (byte) 32);
        System.out.println("User с именем – Edward добавлен в базу данных");

        us.saveUser("Emory", "Shelton", (byte) 28);
        System.out.println("User с именем – Emory добавлен в базу данных");

        us.removeUserById(125L);

        System.out.println(us.getAllUsers());

//        us.createUsersTable();
//        us.dropUsersTable();
    }
}
