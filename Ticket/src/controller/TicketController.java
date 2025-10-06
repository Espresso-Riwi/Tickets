package controller;

import domain.Category;
import domain.Ticket;
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

    public String createTicket(Ticket ticket, String dni, String categoryName) {
        return ticketService.createTicket(ticket, dni, categoryName);
    }

    public String assignTicket(int ticketId, String assigneeDni, String reporterDni) {
        return ticketService.assignTicket(ticketId, assigneeDni, reporterDni);
    }

    public String updateTicketStatus(int ticketId, String dni, String newStatus) {
        return ticketService.updateTicketStatus(ticketId, dni, newStatus);
    }

    public List<Ticket> getTicketsByStatusAndCategory(String dni, String status, String categoryName) {
        return ticketService.getTicketsByStatusAndCategory(dni, status, categoryName);
    }

    public List<Ticket> getTicketsByAssignee(String dni) {
        return ticketService.getTicketsByAssignee(dni);
    }

    public List<Category> getTopCategories(String dni) {
        return ticketService.getTopCategories(dni);
    }

}
