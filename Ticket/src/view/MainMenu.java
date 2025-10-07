package view;

import view.User.UserMenu;
import view.Ticket.TicketMenu;
import view.Comment.CommentMenu;

import javax.swing.*;

public class MainMenu {

    private UserMenu userMenu;
    private TicketMenu ticketMenu;
    private CommentMenu commentMenu;

    public MainMenu(UserMenu userMenu, TicketMenu ticketMenu, CommentMenu commentMenu){
        this.userMenu = userMenu;
        this.ticketMenu = ticketMenu;
        this.commentMenu = commentMenu;
    }

    public void menu(){
        String[] options = {"User", "Ticket", "Comment", "Exit"};
        boolean flag = true;

        while (flag){
            String option = JOptionPane.showInputDialog(null, "Choose an option", "Main menu", JOptionPane.QUESTION_MESSAGE, null, options, options[0]).toString();
            switch (option){
                case "User":
                    userMenu.userMenu();
                    break;
                case "Ticket":
                    ticketMenu.ticketMenu();
                    break;
                case "Comment":
                    commentMenu.commentMenu();
                    break;
                case "Exit":
                    flag = false;
                    break;

            }
        }
    }
}
