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
        return ticketList != null ? ticketList : List.of();
    }

    public Ticket getTicketById(int ticketId) {
        if (ticketId <= 0) return null;
        return ticketIMP.getTicketById(ticketId);
    }

    public String createTicket(Ticket ticket, String dni, String categoryName) {
        User user = userIMP.getUserByDni(dni);
        if (user == null) {
            return "User not found";
        }
        if (!user.getRol().equalsIgnoreCase("reporter")) {
            return "Only reporters can create tickets";
        }

        Integer categoryId = ticketIMP.getCategoryIdByName(categoryName);
        if (categoryId == null) {
            return "Category not found";
        }

        if (Validator.isValidName(ticket.getTitle())) {
            ticket.setReporterId(user.getUser_id());
            ticket.setCategoryId(categoryId);
            System.out.println(user.getUser_id());
            ticketIMP.createTicket(ticket);
            return "Ticket created successfully";
        }
        return "Invalid ticket data";
    }

    public String assignTicket(int ticketId, String assigneeDni, String reporterDni) {
        User reporter = userIMP.getUserByDni(reporterDni);
        if (reporter == null) {
            return "Reporter not found";
        }
        if (!reporter.getRol().equalsIgnoreCase("reporter")) {
            return "Only reporters can assign tickets";
        }

        User assignee = userIMP.getUserByDni(assigneeDni);
        if (assignee == null) {
            return "Assignee not found";
        }
        if (!assignee.getRol().equalsIgnoreCase("assignee")) {
            return "User is not an assignee";
        }

        Ticket ticket = ticketIMP.getTicketById(ticketId);
        if (ticket == null) {
            return "Ticket not found";
        }

        ticketIMP.assignTicket(ticketId, assigneeDni);
        return "Ticket assigned successfully";
    }

    public String updateTicketStatus(int ticketId, String dni, String newStatus) {
        if (!Validator.isValidTicketStatus(newStatus)) {
            return "Invalid status. Valid statuses are: open, in_progress, closed";
        }

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

    public List<Ticket> getTicketsByStatusAndCategory(String dni, String status, String categoryName) {
        if (!Validator.isValidTicketStatus(status)) {
            return List.of();
        }
        List<Ticket> tickets = ticketIMP.getTicketsByStatusAndCategoryName(status, categoryName);
        return tickets != null ? tickets : List.of();
    }

    public List<Ticket> getTicketsByAssignee(String dni) {
        User assignee = userIMP.getUserByDni(dni);
        if (assignee == null || !assignee.getRol().equalsIgnoreCase("assignee")) {
            return List.of();
        }
        List<Ticket> tickets = ticketIMP.getTicketsByAssignee(assignee.getUser_id());
        return tickets != null ? tickets : List.of();
    }

    public List<Category> getTopCategories(String dni) {
        User user = userIMP.getUserByDni(dni);
        if (user == null) {
            return List.of();
        }
        List<Category> categories = ticketIMP.getTopCategories(3);
        return categories != null ? categories : List.of();
    }
}
