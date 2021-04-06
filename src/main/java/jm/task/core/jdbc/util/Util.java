package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {

    private static final String HOST_NAME = "localhost";
    private static final String DB_NAME = "myschema";
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "Qwert12345";

    private static SessionFactory sessionFactory;


    public static Connection getMySQLConnection() throws SQLException {
        return getMySQLConnection(HOST_NAME, DB_NAME, USER_NAME, PASSWORD);
    }

    public static Connection getMySQLConnection(String hostName, String dbName,
                                                String userName, String password) throws SQLException {

        String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName + "?useSSL=false&amp";
        Connection conn = DriverManager.getConnection(connectionURL, userName, password);
        return conn;
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Configuration configuration = new Configuration();

            Properties properties = new Properties();
            String connectionURL = "jdbc:mysql://" + HOST_NAME + ":3306/" + DB_NAME + "?useSSL=false&amp";
            properties.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
            properties.setProperty("hibernate.connection.url", connectionURL);
            properties.setProperty("hibernate.connection.user", USER_NAME);
            properties.setProperty("hibernate.connection.password", PASSWORD);
            properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
            configuration.setProperties(properties);

            configuration.addAnnotatedClass(User.class);
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
        }
        return sessionFactory;
    }
}
