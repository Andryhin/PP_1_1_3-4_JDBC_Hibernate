package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Statement statement = Util.getConnectionMy().createStatement()) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users \n" +
                    "(id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(255)," +
                    "lastname VARCHAR(255)," +
                    "age TINYINT(150))");
            System.out.println("Таблица создана");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = Util.getConnectionMy().createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS users");
            System.out.println("Таблица удалена");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement ps = Util.getConnectionMy()
                .prepareStatement("INSERT INTO users " +
                        "(name, lastname, age) VALUES (?,?,?)")) {
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setByte(3, age);
            ps.executeUpdate();
            System.out.println("Строка добавлена");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {

        try (PreparedStatement ps = Util
                .getConnectionMy().prepareStatement(
                        "DELETE users FROM users where id = ?")) {
            ps.setInt(1, (int) id);
            ps.executeUpdate();
            System.out.println("Строка удалена");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        User user;
        List<User> list = new ArrayList<>();
        try (Statement statement = Util.getConnectionMy().createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM users")) {
            while (resultSet.next()) {
                user = new User();
                user.setId((long) resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setLastName(resultSet.getString(3));
                user.setAge((byte) resultSet.getInt(4));
                list.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void cleanUsersTable() {
        int num;
        try (Statement sDelete = Util.getConnectionMy().createStatement();
             Statement sNull = Util.getConnectionMy().createStatement()) {
            num = sDelete.executeUpdate("DELETE FROM users");
            System.out.println("Удалено строк " + num);
            sNull.executeUpdate("ALTER TABLE users AUTO_INCREMENT = 1");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
