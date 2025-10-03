package dao.User;

import domain.User;

import java.util.List;

public interface UserRepository {
    List<User> getAllUsers();
    User getUserByDni(String dni);
    void createUser(String name, String dni, String email, String rol);
    void updateUser();
    void deleteUser();
}
