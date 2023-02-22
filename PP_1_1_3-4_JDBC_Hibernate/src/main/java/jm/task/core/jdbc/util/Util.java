package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private final static String URL= "jdbc:mysql://localhost:3306/xxx";
    private final static String NAME = "root";
    private final static String PASSWORD = "root";
    private static Connection connection = null;

    public static Connection getConnectionMy() {
        try {
            connection = DriverManager.getConnection(URL, NAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}

