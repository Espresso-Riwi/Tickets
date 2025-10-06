package view.Ticket;

import controller.TicketController;
import domain.Ticket;
import domain.User;
import view.ViewMessages;
import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TicketView {

    private TicketController ticketController;

    public TicketView(TicketController ticketController) {
        this.ticketController = ticketController;
    }

    public void showAllTickets() {
        List<Ticket> ticketList = ticketController.getAllTickets();
        String message = "";
        for (Ticket t : ticketList) {
            message += t.toString() + "\n\n";
        }
        ViewMessages.showInfoMessage(message, "All Tickets");
    }

    public void showTicketById() {
        String idInput = ViewMessages.showQuestionMessage("Enter the Ticket ID:", "Ticket Information");
        int id = Integer.parseInt(idInput);
        HashMap<Boolean, Ticket> ticketHashMap = ticketController.getTicketById(id);

        for (Map.Entry<Boolean, Ticket> entry : ticketHashMap.entrySet()) {
            if (!entry.getKey()) {
                ViewMessages.showInfoMessage("There is no ticket with that ID", "Ticket Info");
            } else {
                ViewMessages.showInfoMessage(entry.getValue().toString(), "Ticket Info");
            }
        }
    }

    public void createTicket() {
        String dni = ViewMessages.showQuestionMessage("Enter your DNI:", "Reporter Validation");
        String title = ViewMessages.showQuestionMessage("Enter ticket title:", "Ticket Information");
        String description = ViewMessages.showQuestionMessage("Enter ticket description:", "Ticket Information");
        String categoryIdStr = ViewMessages.showQuestionMessage("Enter category ID:", "Ticket Information");
        int categoryId = Integer.parseInt(categoryIdStr);
        Ticket ticket = new Ticket(title, description, "open", "medium", 0, 0, categoryId);
        String result = ticketController.createTicket(ticket, dni);
        ViewMessages.showInfoMessage(result, "Information");
    }

    public void assignTicket() {
        String dni = ViewMessages.showQuestionMessage("Enter your DNI:", "Reporter Validation");
        int ticketId = Integer.parseInt(ViewMessages.showQuestionMessage("Enter ticket ID to assign:", "Assign Ticket"));
        int assigneeId = Integer.parseInt(ViewMessages.showQuestionMessage("Enter assignee ID:", "Assign Ticket"));
        String result = ticketController.assignTicket(ticketId, assigneeId, dni);
        ViewMessages.showInfoMessage(result, "Information");
    }

    public void updateTicketStatus() {
        String dni = ViewMessages.showQuestionMessage("Enter your DNI:", "Ticket Update");
        String ticketIdStr = ViewMessages.showQuestionMessage("Enter the Ticket ID:", "Ticket Update");
        String newStatus = ViewMessages.showQuestionMessage("Enter the new status (open, in_progress, closed):", "Ticket Update");
        int ticketId = Integer.parseInt(ticketIdStr);
        String result = ticketController.updateTicketStatus(ticketId, dni, newStatus);
        ViewMessages.showInfoMessage(result, "Ticket Update");
    }

    public void showTicketsByStatusAndCategory() {
        String dni = ViewMessages.showQuestionMessage("Enter your DNI:", "Operator Validation");
        String status = ViewMessages.showQuestionMessage("Enter ticket status (open, in_progress, closed):", "Filter Tickets");
        String categoryIdStr = ViewMessages.showQuestionMessage("Enter category ID:", "Filter Tickets");
        int categoryId = Integer.parseInt(categoryIdStr);
        List<Ticket> tickets = ticketController.getTicketsByStatusAndCategory(dni, status, categoryId);
        if (tickets == null || tickets.isEmpty()) {
            ViewMessages.showInfoMessage("No tickets found with that status and category.", "Filter Tickets");
            return;
        }
        String message = "";
        for (Ticket t : tickets) {
            message += t.toString() + "\n\n";
        }
        ViewMessages.showInfoMessage(message, "Filtered Tickets");
    }

    public void showTicketsByAssignee() {
        String dni = ViewMessages.showQuestionMessage("Enter your DNI:", "Assignee Tickets");
        List<Ticket> tickets = ticketController.getTicketsByAssignee(dni);
        if (tickets == null || tickets.isEmpty()) {
            ViewMessages.showInfoMessage("No tickets found or you do not have permission.", "Assignee Tickets");
            return;
        }
        String message = "";
        for (Ticket t : tickets) {
            message += "ID: " + t.getTicketId() +
                    ", Title: " + t.getTitle() +
                    ", Status: " + t.getStatus() +
                    ", Reporter: " + t.getReporterName() +
                    ", Category: " + t.getCategoryName() + "\n\n";
        }
        ViewMessages.showInfoMessage(message, "Assignee Tickets");
    }
}
