package controller;
import domain.User;
import service.UserService;
import util.Validator;

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
        // Validaciones de entrada en el Controller
        if (dni == null || dni.trim().isEmpty()) {
            HashMap<Boolean, User> userHashMap = new HashMap<>();
            userHashMap.put(false, null);
            return userHashMap;
        }

        if (!Validator.isInteger(dni) || !Validator.isPositive(dni)) {
            HashMap<Boolean, User> userHashMap = new HashMap<>();
            userHashMap.put(false, null);
            return userHashMap;
        }

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
        // Validaciones de entrada en el Controller
        if (user == null) {
            return "User data is required";
        }

        if (user.getName() == null || user.getDni() == null ||
            user.getEmail() == null || user.getRol() == null) {
            return "All user fields are required";
        }

        if (!Validator.isValidName(user.getName())) {
            return "Invalid name format";
        }

        if (!Validator.isInteger(user.getDni()) || !Validator.isPositive(user.getDni())) {
            return "Invalid DNI format";
        }

        if (!Validator.isValidEmail(user.getEmail())) {
            return "Invalid email format";
        }

        if (!Validator.isValidName(user.getRol())) {
            return "Invalid role format";
        }

        return userService.createUser(user);
    }
}
