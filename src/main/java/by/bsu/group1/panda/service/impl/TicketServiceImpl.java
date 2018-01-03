package by.bsu.group1.panda.service.impl;

import by.bsu.group1.panda.dao.TicketRepository;
import by.bsu.group1.panda.model.Ticket;
import by.bsu.group1.panda.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public Collection<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket getTicketById(long id) {
        return ticketRepository.findOne(id);
    }

    @Override
    public Ticket createTicket() {
        return ticketRepository.save(new Ticket());
    }

    @Override
    public void updateTicket(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    @Override
    public void deleteTicket(long id) {
        ticketRepository.delete(id);
    }

    public void setTicketRepository(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }
}
