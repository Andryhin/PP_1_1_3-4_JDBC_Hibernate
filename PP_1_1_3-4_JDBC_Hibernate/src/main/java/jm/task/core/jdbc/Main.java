package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Vadim", "Petrov", (byte) 25);
        userService.saveUser("Petya", "Petrov", (byte) 30);
        userService.saveUser("Kolya", "Petrov", (byte) 35);
        userService.saveUser("Misha", "Petrov", (byte) 40);
        System.out.println(userService.getAllUsers());
        userService.cleanUsersTable();
        for (User user : userService.getAllUsers()) {
            System.out.println(user);
        }
        userService.dropUsersTable();
    }
}
