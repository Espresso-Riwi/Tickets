package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import domain.Ticket;

import config.DBConnection;

public class TicketIMP implements TicketRepository{
    @Override
public boolean asignTicket(int idTicket, int idAssignee) {
    String sql = "UPDATE tickets SET id_assignee = ? WHERE id_ticket = ?";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, idAssignee);
        stmt.setInt(2, idTicket);
        return stmt.executeUpdate() > 0;

    } catch (SQLException e) {
        System.out.println("Error al asignar ticket: " + e.getMessage());
        return false;
    }
}

@Override
public List<Ticket> searchByStateCategory(String state, String category) {
    List<Ticket> lista = new ArrayList<>();
    String sql = "SELECT t.id_ticket, t.titulo, e.nombre AS estado, " +
                 "c.nombre AS categoria, r.nombre AS reporter, a.nombre AS assignee " +
                 "FROM tickets t " +
                 "JOIN estados e ON t.id_estado = e.id_estado " +
                 "JOIN categorias c ON t.id_categoria = c.id_categoria " +
                 "JOIN usuarios r ON t.id_reporter = r.id_usuario " +
                 "LEFT JOIN usuarios a ON t.id_assignee = a.id_usuario " +
                 "WHERE e.nombre = ? AND c.nombre = ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, state);
        stmt.setString(2, category);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Ticket t = new Ticket();
            t.setId(rs.getInt("id_ticket"));
            t.setTitulo(rs.getString("title"));
            t.setEstado(rs.getString("state"));
            t.setCategoria(rs.getString("category"));
            t.setReporter(rs.getString("reporter"));
            t.setAssignee(rs.getString("assignee"));
            lista.add(t);
        }
    } catch (SQLException e) {
        System.out.println("Search Error a: " + e.getMessage());
    }
    return lista;
}


@Override
public List<String> topCategory(int limit) {
    List<String> category = new ArrayList<>();
    String sql = "SELECT c.nombre, COUNT(*) AS total " +
                 "FROM tickets t " +
                 "JOIN categorias c ON t.id_categoria = c.id_categoria " +
                 "GROUP BY c.nombre " +
                 "ORDER BY total DESC LIMIT ?";

    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, limit);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            category.add(rs.getString("name") + " (" + rs.getInt("total") + ")");
        }
    } catch (SQLException e) {
        System.out.println("Error al obtener top categorías: " + e.getMessage());
    }
    return category;
}



}
