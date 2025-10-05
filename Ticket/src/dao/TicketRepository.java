package dao;

import java.util.List;
import domain.Ticket;
public interface TicketRepository {
    boolean asignTicket(int idTicket, int idAssignee);
    List<Ticket> searchByStateCategory(String estado, String categoria);
    List<String> topCategory(int limit);


}


