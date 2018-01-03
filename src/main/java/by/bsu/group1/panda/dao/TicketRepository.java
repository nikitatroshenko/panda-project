package by.bsu.group1.panda.dao;

import by.bsu.group1.panda.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
