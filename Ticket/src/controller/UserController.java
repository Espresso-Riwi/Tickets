package controller;
import domain.User;
import service.UserService;

import java.util.List;

public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    public List<User> getAllUsers(){
        List<User> userList = userService.getAllUsers();
        if (userList == null){
            //joptionPaneMessage
        }else{
            return  userList;
        }

        return null;
    }

    public User getUserByDni(String dni){
        User user = userService.getUserByDni(dni);
        if (user == null){
            //joptionPaneMessage
        }else{
            return user;
        }

        return null;
    }

    public void createUser(User user){
        userService.createUser(user);
    }
}
