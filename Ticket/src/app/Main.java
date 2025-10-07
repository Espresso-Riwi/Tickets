package app;
import config.DBConnection;
import controller.CommentController;
import controller.TicketController;
import controller.UserController;
import dao.Comment.CommentIMP;
import dao.Ticket.TicketIMP;
import dao.User.UserIMP;
import service.CommentService;
import service.TicketService;
import service.UserService;
import view.MainMenu;
import view.Comment.CommentMenu;
import view.Comment.CommentView;
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
        CommentIMP commentIMP = null;

        try{
            userIMP = new UserIMP(dbConnection.getConnection());
            ticketIMP = new TicketIMP(dbConnection.getConnection());
            commentIMP = new CommentIMP(dbConnection.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        UserService userService = new UserService(userIMP);
        TicketService ticketService = new TicketService(ticketIMP, userIMP);
        CommentService commentService = new CommentService(commentIMP, userIMP, ticketIMP);

        UserController userController = new UserController(userService);
        TicketController ticketController = new TicketController(ticketService);
        CommentController commentController = new CommentController(commentService);

        TicketView ticketView = new TicketView(ticketController);
        UserView userView = new UserView(userController);
        CommentView commentView = new CommentView(commentController);

        UserMenu userMenu = new UserMenu(userView);
        TicketMenu ticketMenu = new TicketMenu(ticketView);
        CommentMenu commentMenu = new CommentMenu(commentView);

        MainMenu mainMenu = new MainMenu(userMenu, ticketMenu, commentMenu);
        mainMenu.menu();
    }
}

//123135848
