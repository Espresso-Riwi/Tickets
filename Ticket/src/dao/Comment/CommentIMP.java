package dao.Comment;

import domain.Comment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentIMP implements CommentRepository {
    private Connection connection;

    public CommentIMP(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Comment> getAllComments() {
        List<Comment> comments = new ArrayList<>();
        String query = "SELECT c.comment_id, c.ticket_id, c.user_id, c.content, u.name as user_name " +
                      "FROM comment c JOIN user u ON c.user_id = u.user_id";

        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Comment comment = new Comment(
                    rs.getInt("comment_id"),
                    rs.getInt("ticket_id"),
                    rs.getInt("user_id"),
                    rs.getString("content"),
                    rs.getString("user_name")
                );
                comments.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }

    @Override
    public List<Comment> getCommentsByTicketId(int ticketId) {
        List<Comment> comments = new ArrayList<>();
        String query = "SELECT c.comment_id, c.ticket_id, c.user_id, c.content, u.name as user_name " +
                      "FROM comment c JOIN users u ON c.user_id = u.user_id " +
                      "WHERE c.ticket_id = ? ORDER BY c.comment_id ASC";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, ticketId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Comment comment = new Comment(
                    rs.getInt("comment_id"),
                    rs.getInt("ticket_id"),
                    rs.getInt("user_id"),
                    rs.getString("content"),
                    rs.getString("user_name")
                );
                comments.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return comments;
    }

    @Override
    public Comment getCommentById(int commentId) {
        String query = "SELECT c.comment_id, c.ticket_id, c.user_id, c.content, u.name as user_name " +
                      "FROM comment c JOIN user u ON c.user_id = u.user_id " +
                      "WHERE c.comment_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, commentId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Comment(
                    rs.getInt("comment_id"),
                    rs.getInt("ticket_id"),
                    rs.getInt("user_id"),
                    rs.getString("content"),
                    rs.getString("user_name")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void createComment(Comment comment) {
        String query = "INSERT INTO comment (ticket_id, user_id, content) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, comment.getTicketId());
            stmt.setInt(2, comment.getUserId());
            stmt.setString(3, comment.getContent());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
