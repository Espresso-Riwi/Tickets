package service;
import dao.User.UserIMP;
import domain.User;

import java.util.List;

public class UserService {

    private UserIMP userIMP;

    public UserService(UserIMP userIMP){
        this.userIMP = userIMP;
    }

    public List<User> getAllUsers(){
        List<User> userList = userIMP.getAllUsers();
        return userList != null ? userList : List.of();
    }

    public User getUserByDni(String dni){
        return userIMP.getUserByDni(dni);
    }

    public String createUser(User user){
        if (userIMP.getUserByDni(user.getDni()) != null){
            return "The user already exist";
        }

        userIMP.createUser(user.getName(), user.getDni(), user.getEmail(), user.getRol());
        return "User created successfully";
    }
}
