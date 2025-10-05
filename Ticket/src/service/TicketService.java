package service;
import java.util.ArrayList;
import java.util.List;
import domain.Ticket;
import dao.TicketIMP;
public class TicketService {
    public boolean asignTicket(int idTicket, int idAssignee) {
    // Validaciones simples
    if (idTicket <= 0 || idAssignee <= 0) {
        System.out.println("IDs inválidos.");
        return false;
    }
    TicketIMP ticketIMP = new TicketIMP();
    return ticketIMP.asignTicket(idTicket, idAssignee);
}

public List<Ticket> searchByStateCategory(String estado, String categoria) {
    if (estado == null || categoria == null) {
        System.out.println("Debe especificar estado y categoría.");
        return new ArrayList<>();
    }
    return TicketRepository.searchByStateCategory(estado, categoria);
}

public List<String> topCategorias(int limit) {
    return TicketRepository.topCategorias(limit);
}



}
