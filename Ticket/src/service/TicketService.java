package service;

import dao.Ticket.TicketIMP;
import dao.User.UserIMP;
import domain.Category;
import domain.Ticket;
import domain.User;
import java.util.List;
import util.Validator;

public class TicketService {

    private TicketIMP ticketIMP;
    private UserIMP userIMP;

    public TicketService(TicketIMP ticketIMP, UserIMP userIMP) {
        this.ticketIMP = ticketIMP;
        this.userIMP = userIMP;
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

    public String createTicket(Ticket ticket, String dni) {
        User user = userIMP.getUserByDni(dni);
        if (user == null) {
            return "User not found";
        }
        if (!user.getRol().equalsIgnoreCase("reporter")) {
            return "Only reporters can create tickets";
        }
        if (Validator.isValidName(ticket.getTitle()) && Validator.isValidName(ticket.getDescription())) {
            ticket.setReporterId(user.getUser_id());
            ticketIMP.createTicket(ticket);
            return "Ticket created successfully";
        }
        return "Invalid ticket data";
    }

    public String assignTicket(int ticketId, int assigneeId, String dni) {
        User user = userIMP.getUserByDni(dni);
        if (user == null) {
            return "User not found";
        }
        if (!user.getRol().equalsIgnoreCase("reporter")) {
            return "Only reporters can assign tickets";
        }
        Ticket ticket = ticketIMP.getTicketById(ticketId);
        if (ticket == null) {
            return "Ticket not found";
        }
        ticketIMP.assignTicket(ticketId, assigneeId);
        return "Ticket assigned successfully";
    }

    public String updateTicketStatus(int ticketId, String dni, String newStatus) {
        User assignee = userIMP.getUserByDni(dni);
        if (assignee == null || !assignee.getRol().equalsIgnoreCase("assignee")) {
            return "You do not have permission to change the ticket status.";
        }
        Ticket ticket = ticketIMP.getTicketById(ticketId);
        if (ticket == null) {
            return "Ticket not found.";
        }
        if (ticket.getAssigneeId() != assignee.getUser_id()) {
            return "You can only update tickets assigned to you.";
        }
        return ticketIMP.updateStatus(ticketId, newStatus);
    }

    public List<Ticket> getTicketsByStatusAndCategory(String dni, String status, int categoryId) {
        User user = userIMP.getUserByDni(dni);
        if (user == null || !user.getRol().equalsIgnoreCase("operator")) {
            return null;
        }
        List<Ticket> tickets = ticketIMP.getTicketsByStatusAndCategory(status, categoryId);
        return tickets.isEmpty() ? null : tickets;
    }

    public List<Ticket> getTicketsByAssignee(String dni) {
        User assignee = userIMP.getUserByDni(dni);
        if (assignee == null || !assignee.getRol().equalsIgnoreCase("assignee")) {
            return null;
        }
        List<Ticket> tickets = ticketIMP.getTicketsByAssignee(assignee.getUser_id());
        return tickets.isEmpty() ? null : tickets;
    }

    public List<Category> getTopCategories(String dni) {
        User user = userIMP.getUserByDni(dni);
        if (user == null) {
            return null;
        }
        List<Category> categories = ticketIMP.getTopCategories(3);
        return categories.isEmpty() ? null : categories;
    }
}
