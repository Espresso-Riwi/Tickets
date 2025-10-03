package view.User;

import javax.swing.*;

public class UserMenu {

    private UserView userView;

    public UserMenu(UserView userView){
        this.userView = userView;
    }

    public void userMenu(){
        String[] options = {"All users", "User by id", "Create user"};
        String option = JOptionPane.showInputDialog(null, "Choose an option", "User menu", JOptionPane.QUESTION_MESSAGE, null, options, options[0]).toString();

        switch (option){
            case "All users":
                userView.showAllUsers();
                break;
            case "User by id":
                break;
            case "Create user":
                break;
        }
    }

}
