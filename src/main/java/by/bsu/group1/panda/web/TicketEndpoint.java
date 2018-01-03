package by.bsu.group1.panda.web;

import by.bsu.group1.panda.model.Ticket;
import by.bsu.group1.panda.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.net.URI;
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
    public Response createTicket() {
        Ticket ticket = ticketService.createTicket();

        return Response.created(URI.create("/tickets/" + ticket.getId())).build();
    }

    @PutMapping("/{id}")
    public Response updateTicket(@PathVariable("id") long id, @RequestBody Ticket ticket) {
        if (ticket.getId() != id) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        if (ticketService.getTicketById(id) == null) {
            Ticket createdTicket = ticketService.createTicket();

            ticket.setId(createdTicket.getId());
            ticketService.updateTicket(ticket);
            return Response.created(URI.create("/tickets/" + ticket.getId())).build();
        }

        ticketService.updateTicket(ticket);
        return Response.ok().build();
    }

    @DeleteMapping("/{id}")
    public void deleteTicket(@PathVariable("id") long id) {
        ticketService.deleteTicket(id);
    }

    @Required
    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }
}
