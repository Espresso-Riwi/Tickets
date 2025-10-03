package app;
import config.DBConnection;
import dao.User.UserIMP;
import service.UserService;

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
    }
}
