package by.bsu.group1.panda.web;

import by.bsu.group1.panda.model.Ticket;
import by.bsu.group1.panda.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.Collection;

@RestController("/tickets")
public class TicketEndpoint {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/")
    public Collection<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/{id}")
    public Ticket getTicket(@PathVariable("id") long id) {
        return ticketService.getTicketById(id);
    }

    @PostMapping("/")
    public Response createTicket(@QueryParam("ticket") Ticket ticket) {
        return null;
    }

    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }
}
