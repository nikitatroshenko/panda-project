package by.bsu.group1.panda.web;

import by.bsu.group1.panda.model.Ticket;
import by.bsu.group1.panda.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.util.Collection;

@RestController
//@RequestMapping("/tickets")
public class TicketEndpoint {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/tickets")
    public Collection<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/tickets/{id}")
    public Ticket getTicket(@PathVariable("id") long id) {
        return ticketService.getTicketById(id);
    }

    @PostMapping("/{projectKey}/tickets")
    public ResponseEntity<?> createTicket(@PathVariable String projectKey, @RequestBody Ticket ticket) {
        ticket = ticketService.createTicket(projectKey, ticket);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(ticket.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/tickets/{id}")
    public ResponseEntity<?> updateTicket(@PathVariable("id") long id, @RequestBody Ticket ticket) {
        if (ticket.getId() != id) {
            return ResponseEntity.badRequest().build();
        }

        ticketService.updateTicket(ticket);
        return ResponseEntity.ok(ticket);
    }

    @DeleteMapping("/tickets/{id}")
    public void deleteTicket(@PathVariable("id") long id) {
        ticketService.deleteTicket(id);
    }

    @ExceptionHandler({EntityNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void notFound() {
        // nothing to d
    }

    public void setTicketService(TicketService ticketService) {
        this.ticketService = ticketService;
    }
}
