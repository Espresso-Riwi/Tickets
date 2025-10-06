package dao.Ticket;

import domain.Ticket;
import java.util.List;

public interface TicketRepository {
    List<Ticket> getAllTickets();
    Ticket getTicketById(int ticketId);
    void createTicket(String title, String description, String status, String priority, int reporterId, Integer assigneeId, int categoryId);
    void updateTicket();
    void deleteTicket();
}
