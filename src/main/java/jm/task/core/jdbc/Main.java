package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService service = new UserServiceImpl();
        service.createUsersTable();

        User user = new User("Slon", "Vaskin", (byte) 20);
        service.saveUser(user.getName(), user.getLastName(), user.getAge());
        System.out.println(user.getName());

        user = new User("Popa", "Dracona", (byte) 12);
        service.saveUser(user.getName(), user.getLastName(), user.getAge());
        System.out.println(user.getName());

        user = new User("Iaicha", "Dracona", (byte) 12);
        service.saveUser(user.getName(), user.getLastName(), user.getAge());
        System.out.println(user.getName());

        List<User> users = service.getAllUsers();
        users.forEach(System.out::println);

        service.cleanUsersTable();
        service.dropUsersTable();
    }
}
