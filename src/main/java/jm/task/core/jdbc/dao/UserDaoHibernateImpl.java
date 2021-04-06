package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static UserDao userDao;

    private UserDaoHibernateImpl() {
    }

    public static UserDao getUserDao() {
        if (userDao == null) {
            userDao = new UserDaoHibernateImpl();
        }
        return userDao;
    }

    @Override
    public void createUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        session.createSQLQuery("create table if not exists USERS (ID bigint not null AUTO_INCREMENT, " +
                "NAME varchar(50), LAST_NAME varchar(50), AGE integer, primary key (ID))")
                .executeUpdate();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        session.createSQLQuery("drop table if exists USERS").executeUpdate();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        Session session = Util.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.persist(user);
        tx.commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        User user = new User();
        user.setId(id);

        Session session = Util.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.delete(user);
        tx.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSessionFactory().openSession();
        List<User> users = (List<User>) session.createQuery("From User").list();
        session.close();
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.createQuery("delete from User").executeUpdate();
        tx.commit();
        session.close();
    }
}
