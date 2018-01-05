package by.bsu.group1.panda.service;

import by.bsu.group1.panda.model.Ticket;

import java.util.Collection;
import java.util.Map;

public interface TicketService {

    Collection<Ticket> getAllTickets();

    Ticket getTicketById(long id);

    Ticket createTicket(String username, String projectKey, Ticket ticket);

    Ticket updateTicket(long id, Ticket ticket);

    void deleteTicket(long id);

    Collection<Ticket> getTicketsByReporter(String reporter);
}
