package by.bsu.group1.panda.service;

import by.bsu.group1.panda.model.Ticket;

import java.util.Collection;

public interface TicketService {

    Collection<Ticket> getAllTickets();

    Ticket getTicketById(long id);

    Ticket createTicket(String username, String projectKey, Ticket ticket);

    void updateTicket(Ticket ticket);

    void deleteTicket(long id);

    Collection<Ticket> getTicketsByReporter(String reporter);
}
