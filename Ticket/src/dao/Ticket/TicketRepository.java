package dao.Ticket;

import domain.Ticket;
import java.util.List;

public interface TicketRepository {
    List<Ticket> getAllTickets();
    Ticket getTicketById(int ticketId);
    void createTicket(Ticket ticket);
    void updateTicket();
    void deleteTicket();
    void assignTicket(int ticketId, int assigneeId);
}
