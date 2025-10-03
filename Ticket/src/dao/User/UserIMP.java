package dao.User;

import domain.User;

import java.sql.Connection;
import java.util.List;

public class UserIMP implements UserRepository {

    private Connection connection;

    public UserIMP(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<User> getAllUsers() {
        return List.of();
    }

    @Override
    public User getUserByDni() {
        return null;
    }

    @Override
    public void createUser() {

    }

    @Override
    public void updateUser() {

    }

    @Override
    public void deleteUser() {

    }
}
