package dao.User;

import domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserIMP implements UserRepository {

    private Connection connection;

    public UserIMP(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM user";
        List<User> userList = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                userList.add(new User(
                        rs.getInt("user_id"),
                        rs.getString("name"),
                        rs.getString("dni"),
                        rs.getString("email"),
                        rs.getString("rol")
                ));
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public User getUserByDni(String dni) {
        String sql = "SELECT * FROM user WHERE dni = ?";

        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            connection.setAutoCommit(false);
            stmt.setString(1, dni);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                return new User(rs.getInt("user_id"),
                        rs.getString("name"),
                        rs.getString("dni"),
                        rs.getString("email"),
                        rs.getString("rol"));
            }
            connection.commit();
        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void createUser(String name, String dni, String email, String rol) {
        String sql = "INSERT INTO user(name, dni, email, rol) VALUES (?, ?, ?, ?)";
        try(PreparedStatement stmt = connection.prepareStatement(sql)){
            connection.setAutoCommit(false);

            stmt.setString(1, name);
            stmt.setString(2, dni);
            stmt.setString(3, email);
            stmt.setString(4, rol);

            stmt.executeUpdate();
            connection.commit();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser() {

    }

    @Override
    public void deleteUser() {

    }
}
