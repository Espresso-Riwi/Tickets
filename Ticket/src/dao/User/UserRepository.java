package dao.User;

import domain.User;

import java.util.List;

public interface UserRepository {
    List<User> getAllUsers();
    User getUserByDni();
    void createUser();
    void updateUser();
    void deleteUser();
}
