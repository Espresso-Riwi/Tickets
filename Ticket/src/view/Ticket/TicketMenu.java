package view.Ticket;

import javax.swing.*;

public class TicketMenu {

    private TicketView ticketView;

    public TicketMenu(TicketView ticketView) {
        this.ticketView = ticketView;
    }

    public void ticketMenu() {
        String[] options = {"All tickets", "Ticket by ID", "Create ticket", "Assign ticket", "Update ticket status", "Search by status and category", "List tickets by assignee", "Top categories"};
        String option = JOptionPane.showInputDialog(null, "Choose an option", "Ticket menu", JOptionPane.QUESTION_MESSAGE, null, options, options[0]).toString();

        switch (option) {
            case "All tickets":
                ticketView.showAllTickets();
                break;
            case "Ticket by ID":
                ticketView.showTicketById();
                break;
            case "Create ticket":
                ticketView.createTicket();
                break;
            case "Assign ticket":
                ticketView.assignTicket();
                break;
            case "Update ticket status":
                ticketView.updateTicketStatus();
                break;
            case "Search by status and category":
                ticketView.showTicketsByStatusAndCategory();
                break;
            case "List tickets by assignee":
                ticketView.showTicketsByAssignee();
                break;
            case "Top categories":
                ticketView.showTopCategories();
                break;
        }
    }

}

