package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Aleksandr", "Elshin", (byte) 24);
        userService.saveUser("Aleksandr", "Elshin", (byte) 24);
        userService.saveUser("Aleksandr", "Elshin", (byte) 24);
        List<User> users = userService.getAllUsers();
        userService.removeUserById(0);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
