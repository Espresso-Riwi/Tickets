package service;
import dao.User.UserIMP;
import domain.User;
import util.Validator;

import java.util.List;

public class UserService {

    private UserIMP userIMP;

    public UserService(UserIMP userIMP){
        this.userIMP = userIMP;
    }

    public List<User> getAllUsers(){
        List<User> userList = userIMP.getAllUsers();
        if (userList.isEmpty()){
            return null;
        }else{
            return userList;
        }
    }

    public User getUserByDni(String dni){
        User user = userIMP.getUserByDni(dni);
        //Logic if we need.
        return user;
    }

    public String createUser(User user){

        if (userIMP.getUserByDni(user.getDni()) != null){
            return "The user already exist";
        }else{
            if (userIMP.getUserByDni(user.getDni()) == null){
                //validate  inputs
                if (Validator.isValidName(user.getName()) || Validator.isInteger(user.getDni()) || Validator.isPositive(user.getDni())
                        || Validator.isValidEmail(user.getEmail()) || Validator.isValidName(user.getRol())){
                    userIMP.createUser(user.getName(), user.getDni(), user.getEmail(), user.getRol());
                    return "User created successfully";
                }else{
                    return "Invalid user data";
                }
            }
        }
        return null;
    }
}
