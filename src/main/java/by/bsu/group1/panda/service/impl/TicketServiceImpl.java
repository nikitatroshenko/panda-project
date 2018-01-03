package by.bsu.group1.panda.service.impl;

import by.bsu.group1.panda.dao.TicketRepository;
import by.bsu.group1.panda.model.Ticket;
import by.bsu.group1.panda.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Override
    public Collection<Ticket> getAllTickets() {
        return StreamSupport.stream(ticketRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Ticket getTicketById(long id) {
        return ticketRepository.findOne(id);
    }

    public Ticket createTicket(Ticket ticket) {
        ticketRepository.save(ticket);
        return null;
    }

    public void setTicketRepository(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }
}
