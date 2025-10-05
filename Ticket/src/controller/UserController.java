package controller;
import domain.User;
import service.UserService;

import java.util.HashMap;
import java.util.List;

public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    public HashMap<Boolean, User> getUserByDni(String dni){
        User user = userService.getUserByDni(dni);
        HashMap<Boolean, User> userHashMap = new HashMap<>();
        if (user == null){
            userHashMap.put(false, null);
            return userHashMap;
        }else{
            userHashMap.put(true, user);
            return userHashMap;
        }
    }

    public String createUser(User user){
        return userService.createUser(user);
    }
}
