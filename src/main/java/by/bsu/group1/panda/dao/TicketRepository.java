package by.bsu.group1.panda.dao;

import by.bsu.group1.panda.model.Ticket;
import org.springframework.data.repository.CrudRepository;

public interface TicketRepository extends CrudRepository<Ticket, Long> {
}
