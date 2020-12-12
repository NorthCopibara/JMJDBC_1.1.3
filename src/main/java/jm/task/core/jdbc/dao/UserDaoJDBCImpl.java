package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private final String sqlCreate      = "CREATE TABLE IF NOT EXISTS users (Id INT PRIMARY KEY AUTO_INCREMENT, Name VARCHAR(20), LastName VARCHAR(20), Age SMALLINT(3) )";
    private final String sqlDrop        = "DROP TABLE users";
    private final String sqlAddUser     = "INSERT Users(Name, LastName, Age) VALUES (\'%s\', \'%s\', %d)";
    private final String sqlRemoveUser  = "DELETE FROM Products WHERE Id = %d";
    private final String sqlGetUser     = "SELECT * FROM users";
    private final String sqlClearUser   = "TRUNCATE TABLE Users";

    private Util util;

    public UserDaoJDBCImpl() {
        util = new Util();
    }

    public void createUsersTable() {
        try(Statement statement = util.getConnection().createStatement()) {
            statement.executeUpdate(sqlCreate);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try(Statement statement = util.getConnection().createStatement()) {
            statement.executeUpdate(sqlDrop);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try(Statement statement = util.getConnection().createStatement()) {
            statement.executeUpdate(String.format(sqlAddUser, name, lastName, age));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try(Statement statement = util.getConnection().createStatement()) {
            statement.executeUpdate(String.format(sqlRemoveUser, id));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = null;
        try(Statement statement = util.getConnection().createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlGetUser);
            users = new ArrayList<>();
            while(resultSet.next()){
                Long id = resultSet.getLong(1);
                String name = resultSet.getString(2);
                String lastName = resultSet.getString(3);
                byte age = resultSet.getByte(4);

                User user = new User(name, lastName, age);
                user.setId(id);

                users.add(user);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void cleanUsersTable() {
        try(Statement statement = util.getConnection().createStatement()) {
            statement.executeUpdate(sqlClearUser);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
