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
        //Logic if we need.
        return userList;
    }

    public User getUserByDni(String dni){
        User user = userIMP.getUserByDni(dni);
        //Logic if we need.
        return user;
    }

    public void createUser(String name, String dni, String email, String rol){
        //Validation logic

        if (userIMP.getUserByDni(dni) == null){
            //error message here
        }else{
            if (Validator.isValidName(name) || Validator.isInteger(dni) || Validator.isPositive(dni)
                    || Validator.isValidEmail(email) || Validator.isValidName(rol)){
                userIMP.createUser(name, dni, email, rol);
            }
        }



    }




}
