package service;

import dao.Ticket.TicketIMP;
import domain.Ticket;
import domain.User;
import java.util.List;

public class TicketService {

    private TicketIMP ticketIMP;

    public TicketService(TicketIMP ticketIMP) {
        this.ticketIMP = ticketIMP;
    }

    public List<Ticket> getAllTickets() {
        List<Ticket> ticketList = ticketIMP.getAllTickets();
        if (ticketList.isEmpty()) {
            return null;
        } else {
            return ticketList;
        }
    }

    public Ticket getTicketById(int ticketId) {
        return ticketIMP.getTicketById(ticketId);
    }

    public String createTicket(Ticket ticket, User user) {
        if (!user.getRol().equalsIgnoreCase("reporter")) {
            return "Only reporters can create tickets";
        }
        ticketIMP.createTicket(ticket.getTitle(), ticket.getDescription(), ticket.getStatus(), ticket.getPriority(), ticket.getReporterId(), ticket.getAssigneeId(), ticket.getCategoryId());
        return "Ticket created successfully";
    }
}
