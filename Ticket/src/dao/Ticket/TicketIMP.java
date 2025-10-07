package dao.Ticket;

import domain.Category;
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
        String sql = "INSERT INTO ticket(title, description, status, priority, reporter_id, category_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            stmt.setString(1, ticket.getTitle());
            stmt.setString(2, ticket.getDescription());
            stmt.setString(3, ticket.getStatus());
            stmt.setString(4, ticket.getPriority());
            stmt.setInt(5, ticket.getReporterId());
            stmt.setInt(6, ticket.getCategoryId());
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
    public void assignTicket(int ticketId, String assigneeDni) {
        String sql = "UPDATE ticket SET assignee_id = (SELECT user_id FROM user WHERE dni = ?) WHERE ticket_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            stmt.setString(1, assigneeDni);
            stmt.setInt(2, ticketId);
            int rowsAffected = stmt.executeUpdate();

            if (rowsAffected > 0) {
                connection.commit();
            } else {
                connection.rollback();
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
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

    public List<Ticket> getTicketsByStatusAndCategory(String status, int categoryId) {
        String sql = "SELECT * FROM ticket WHERE status = ? AND category_id = ?";
        List<Ticket> ticketList = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            stmt.setString(1, status);
            stmt.setInt(2, categoryId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ticketList.add(new Ticket(
                        rs.getInt("ticket_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("status"),
                        rs.getString("priority"),
                        rs.getInt("reporter_id"),
                        rs.getInt("assignee_id"),
                        rs.getInt("category_id")
                ));
            }
            connection.commit();
        } catch (SQLException e) {
            try { connection.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            e.printStackTrace();
        }
        return ticketList;
    }

    public List<Ticket> getTicketsByAssignee(int assigneeId) {
        String sql = "SELECT t.ticket_id, t.title, t.description, t.status, t.priority, t.reporter_id, t.assignee_id, t.category_id, " +
                "r.name AS reporter_name, c.name AS category_name " +
                "FROM ticket t " +
                "LEFT JOIN user r ON t.reporter_id = r.user_id " +
                "LEFT JOIN category c ON t.category_id = c.category_id " +
                "WHERE t.assignee_id = ?";
        List<Ticket> ticketList = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            stmt.setInt(1, assigneeId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Ticket t = new Ticket(
                        rs.getInt("ticket_id"),
                        rs.getString("title"),
                        rs.getString("description"),
                        rs.getString("status"),
                        rs.getString("priority"),
                        rs.getInt("reporter_id"),
                        rs.getInt("assignee_id"),
                        rs.getInt("category_id")
                );
                t.setReporterName(rs.getString("reporter_name"));
                t.setCategoryName(rs.getString("category_name"));
                ticketList.add(t);
            }
            connection.commit();
        } catch (SQLException e) {
            try { connection.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            e.printStackTrace();
        }
        return ticketList;
    }

    public List<Category> getTopCategories(int limit) {
        String sql = "SELECT c.category_id, c.name AS category_name, COUNT(t.ticket_id) AS ticket_count " +
                "FROM category c " +
                "LEFT JOIN ticket t ON c.category_id = t.category_id " +
                "GROUP BY c.category_id, c.name " +
                "ORDER BY ticket_count DESC " +
                "LIMIT ?";
        List<Category> topCategories = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            stmt.setInt(1, limit);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                topCategories.add(new Category(
                        rs.getInt("category_id"),
                        rs.getString("category_name"),
                        rs.getInt("ticket_count")
                ));
            }
            connection.commit();
        } catch (SQLException e) {
            try { connection.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            e.printStackTrace();
        }
        return topCategories;
    }

    @Override
    public Integer getCategoryIdByName(String categoryName) {
        String sql = "SELECT category_id FROM category WHERE name = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            stmt.setString(1, categoryName);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                connection.commit();
                return rs.getInt("category_id");
            } else {
                connection.commit();
                return null;
            }
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Ticket> getTicketsByStatusAndCategoryName(String status, String categoryName) {
        String sql = "SELECT t.* FROM ticket t " +
                    "INNER JOIN category c ON t.category_id = c.category_id " +
                    "WHERE t.status = ? AND c.name = ?";
        List<Ticket> ticketList = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            connection.setAutoCommit(false);
            stmt.setString(1, status);
            stmt.setString(2, categoryName);
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
            try { connection.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
            e.printStackTrace();
        }
        return ticketList;
    }
}
