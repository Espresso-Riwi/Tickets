package controller;

import java.util.List;
import view.Ticket.TicketView;

public class TicketController {

    TicketView ticket  = new TicketView(); 
    public void asignTicket() {
    int idTicket = ticket.askTicket();
    int idAssignee = ticket.askIdUserAsigned();
    boolean ok = ticketService.asignTicket(idTicket, idAssignee);

    if (ok) ticket.mostrarMensaje("✅ Ticket asignado correctamente.");
    else ticket.mostrarMensaje("❌ No se pudo asignar el ticket.");
}

public void searchByStateCategory() {
    String estado = ticket.askState();
    String categoria = ticket.askCategory();

    List<Ticket> resultados = ticketService.buscarPorEstadoYCategoria(estado, categoria);
    ticket.mostrarTickets(resultados);
}


public void watchTopCategorias() {
    List<String> top = ticketService.topCategorias(3);
    ticket.showTopCategories(top);
}

}
