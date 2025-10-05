package view.User;
import domain.User;

import controller.UserController;
import view.ViewMessages;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserView {

    private UserController userController;

    public UserView(UserController userController){
        this.userController = userController;
    }

    public void showAllUsers(){
        List<User> userList = userController.getAllUsers();
        String message = "";

        for (User u : userList){
            message += u.toString()+"\n\n";
        }

        ViewMessages.showInfoMessage(message, "All users");
    }

    public void showUserByDni(){
        String dni = ViewMessages.showQuestionMessage("Enter the user DNI", "User DNI");
        HashMap<Boolean, User> userHashMap = userController.getUserByDni(dni);

        for (Map.Entry<Boolean, User> entry : userHashMap.entrySet()) {
            if (!entry.getKey()){
                ViewMessages.showInfoMessage("There is no a user with that DNI", "User info");
            }else{
                ViewMessages.showInfoMessage(entry.getValue().toString(), "User info");
            }

        }
    }

    public void createUser(){
        String name = ViewMessages.showQuestionMessage("Enter your name:", "User Information");
        String dni = ViewMessages.showQuestionMessage("Enter your ID (DNI):", "User Information");
        String email = ViewMessages.showQuestionMessage("Enter your email address:", "User Information");
        String rol = ViewMessages.showQuestionMessage("Enter your role:", "User Information");

        User user = new User(name, dni, email, rol);
        String result = userController.createUser(user);
        ViewMessages.showInfoMessage(result, "Information");
    }
}
