package view.Ticket;

import java.util.List;
import java.util.Scanner;

public class TicketView {
    public int askTicket() {
    System.out.print("Enter ticket Id : ");
    return new Scanner(System.in).nextInt();
}

public int askIdUserAsigned() {
    System.out.print("Ingrese ID del usuario asignado: ");
    return new Scanner(System.in).nextInt();
}


public String askState() {
    System.out.print("Ingrese estado (Abierto, En Proceso, Resuelto, Cerrado): ");
    return new Scanner(System.in).nextLine();
}

public String askCategory() {
    System.out.print("Ingrese categoría: ");
    return new Scanner(System.in).nextLine();
}

public void showTickets(List<Ticket> lista) {
    lista.forEach(System.out::println);
}

public void showTopCategories(List<String> top) {
    System.out.println("\n🏆 Top Categorías:");
    top.forEach(System.out::println);
}


}
