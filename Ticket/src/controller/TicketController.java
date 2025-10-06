package controller;

import domain.Ticket;
import domain.User;
import service.TicketService;
import java.util.HashMap;
import java.util.List;

public class TicketController {

    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    public HashMap<Boolean, Ticket> getTicketById(int ticketId) {
        Ticket ticket = ticketService.getTicketById(ticketId);
        HashMap<Boolean, Ticket> ticketHashMap = new HashMap<>();
        if (ticket == null) {
            ticketHashMap.put(false, null);
            return ticketHashMap;
        } else {
            ticketHashMap.put(true, ticket);
            return ticketHashMap;
        }
    }

    public String createTicket(Ticket ticket, User user) {
        return ticketService.createTicket(ticket, user);
    }
}
