package view;

import view.User.UserMenu;

import javax.swing.*;

public class MainMenu {

    private UserMenu userMenu;

    public MainMenu(UserMenu userMenu){
        this.userMenu = userMenu;
    }

    public void menu(){
        String[] options = {"User", "Exit"};
        boolean flag = true;

        while (flag){
            String option = JOptionPane.showInputDialog(null, "Choose an option", "User menu", JOptionPane.QUESTION_MESSAGE, null, options, options[0]).toString();
            switch (option){
                case "User":
                    userMenu.userMenu();
                    break;
                case "Exit":
                    flag = false;
                    break;

            }
        }
    }
}
