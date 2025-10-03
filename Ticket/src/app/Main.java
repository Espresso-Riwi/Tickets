package app;
import config.DBConnection;
import controller.UserController;
import dao.User.UserIMP;
import service.UserService;
import view.User.UserMenu;
import view.User.UserView;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        DBConnection dbConnection = new DBConnection();
        UserIMP userIMP = null;

        try{
            userIMP = new UserIMP(dbConnection.getConnection());
        } catch (SQLException e) {
            e.printStackTrace();
        }

        UserService userService = new UserService(userIMP);
        UserController userController = new UserController(userService);
        UserView userView = new UserView(userController);
        UserMenu userMenu = new UserMenu(userView);
    }
}
