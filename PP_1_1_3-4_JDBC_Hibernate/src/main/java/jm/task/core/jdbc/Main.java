package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {

    public static void main(String[] args) {
        // Реализуйте алгоритм здесь
        UserServiceImpl userService = new UserServiceImpl();

        userService.createUsersTable();
        userService.saveUser("Dima1", "Ivanov1", (byte) 36);
        userService.saveUser("Dima2", "Ivanov2", (byte) 36);
        userService.saveUser("Dima3", "Ivanov3", (byte) 36);
        userService.saveUser("Dima4", "Ivanov4", (byte) 36);
        userService.getAllUsers().forEach(System.out::println);
        userService.removeUserById(2);
        userService.getAllUsers().forEach(System.out::println);
        userService.cleanUsersTable();
        userService.getAllUsers().forEach(System.out::println);
        userService.dropUsersTable();
//    }
    }
}

