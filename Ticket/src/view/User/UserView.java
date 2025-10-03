package view.User;
import domain.User;

import controller.UserController;
import view.ViewMessages;

import java.util.List;

public class UserView {

    private UserController userController;

    public UserView(UserController userController){
        this.userController = userController;
    }

    public void showAllUsers(){
        List<User> userList = userController.getAllUsers();
        String message = "";

        for (User u : userList){
            message = u.toString()+"\n\n";
        }

        ViewMessages.showInfoMessage(message, "All users");
    }
}
