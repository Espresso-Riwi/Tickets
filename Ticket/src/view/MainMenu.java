package view;

import view.User.UserMenu;
import view.Ticket.TicketMenu;

import javax.swing.*;

public class MainMenu {

    private UserMenu userMenu;
    private TicketMenu ticketMenu;

    public MainMenu(UserMenu userMenu, TicketMenu ticketMenu){
        this.userMenu = userMenu;
        this.ticketMenu = ticketMenu;
    }

    public void menu(){
        String[] options = {"User", "Ticket","Exit"};
        boolean flag = true;

        while (flag){
            String option = JOptionPane.showInputDialog(null, "Choose an option", "User menu", JOptionPane.QUESTION_MESSAGE, null, options, options[0]).toString();
            switch (option){
                case "User":
                    userMenu.userMenu();
                    break;
                case "Ticket":
                    ticketMenu.ticketMenu();
                    break;
                case "Exit":
                    flag = false;
                    break;

            }
        }
    }
}
