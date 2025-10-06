package app;
import config.DBConnection;
import controller.TicketController;
import controller.UserController;
import dao.Ticket.TicketIMP;
import dao.User.UserIMP;
import service.TicketService;
import service.UserService;
import view.MainMenu;
import view.Ticket.TicketMenu;
import view.Ticket.TicketView;
import view.User.UserMenu;
import view.User.UserView;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        DBConnection dbConnection = new DBConnection();
        UserIMP userIMP = null;
        TicketIMP ticketIMP = null;

        try{
            userIMP = new UserIMP(dbConnection.getConnection());
            ticketIMP = new TicketIMP(dbConnection.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        UserService userService = new UserService(userIMP);
        TicketService ticketService = new TicketService(ticketIMP, userIMP);

        UserController userController = new UserController(userService);
        TicketController ticketController = new TicketController(ticketService);

        TicketView ticketView = new TicketView(ticketController);
        UserView userView = new UserView(userController);


        UserMenu userMenu = new UserMenu(userView);
        TicketMenu ticketMenu = new TicketMenu(ticketView);

        MainMenu mainMenu = new MainMenu(userMenu, ticketMenu);
        mainMenu.menu();
    }
}

//123135848
