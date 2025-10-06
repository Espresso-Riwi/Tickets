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
    public void createTicket(Ticket ticket) {
        String sql = "INSERT INTO ticket(title, description, status, priority, reporter_id, assignee_id, category_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            stmt.setString(1, ticket.getTitle());
            stmt.setString(2, ticket.getDescription());
            stmt.setString(3, ticket.getStatus());
            stmt.setString(4, ticket.getPriority());
            stmt.setInt(5, ticket.getReporterId());
            stmt.setInt(6, ticket.getAssigneeId());
            stmt.setInt(7, ticket.getCategoryId());
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

    @Override
    public void assignTicket(int ticketId, int assigneeId) {
        String sql = "UPDATE ticket SET assignee_id = ? WHERE ticket_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            stmt.setInt(1, assigneeId);
            stmt.setInt(2, ticketId);
            stmt.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String updateStatus(int ticketId, String newStatus) {
        String sql = "UPDATE ticket SET status = ? WHERE ticket_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, newStatus);
            ps.setInt(2, ticketId);
            int rows = ps.executeUpdate();
            connection.commit();
            return rows > 0 ? "Ticket status updated successfully." : "No ticket found with that ID.";
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return "Error updating ticket status: " + e.getMessage();
        }
    }
}
