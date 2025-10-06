package dao.Ticket;

import domain.Ticket;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketIMP implements TicketRepository {

    private Connection connection;

    public TicketIMP(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Ticket> getAllTickets() {
        String sql = "SELECT * FROM ticket";
        List<Ticket> ticketList = new ArrayList<>();

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ticketList.add(new Ticket(
                        rs.getInt("ticket_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("status"),
                        rs.getString("priority"),
                        rs.getInt("reporter_id"),
                        rs.getObject("assignee_id") != null ? rs.getInt("assignee_id") : null,
                        rs.getInt("category_id")
                ));
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticketList;
    }

    @Override
    public Ticket getTicketById(int ticketId) {
        String sql = "SELECT * FROM ticket WHERE ticket_id = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            stmt.setInt(1, ticketId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Ticket(
                        rs.getInt("ticket_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("status"),
                        rs.getString("priority"),
                        rs.getInt("reporter_id"),
                        rs.getObject("assignee_id") != null ? rs.getInt("assignee_id") : null,
                        rs.getInt("category_id")
                );
            }
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void createTicket(String title, String description, String status, String priority, int reporterId, Integer assigneeId, int categoryId) {
        String sql = "INSERT INTO ticket(title, description, status, priority, reporter_id, assignee_id, category_id) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            stmt.setString(1, title);
            stmt.setString(2, description);
            stmt.setString(3, status);
            stmt.setString(4, priority);
            stmt.setInt(5, reporterId);

            if (assigneeId != null) {
                stmt.setInt(6, assigneeId);
            } else {
                stmt.setNull(6, Types.INTEGER);
            }

            stmt.setInt(7, categoryId);
            stmt.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTicket() {
    }

    @Override
    public void deleteTicket() {
    }
}
