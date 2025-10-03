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
        //Logic if we want.
        return userList;
    }




}
