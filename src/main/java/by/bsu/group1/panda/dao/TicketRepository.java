package by.bsu.group1.panda.dao;

import by.bsu.group1.panda.model.Ticket;
import by.bsu.group1.panda.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    Collection<Ticket> findTicketsByReporter(User reporter);

    Collection<Ticket> findTicketsByReporterAndAssignee(User reporter, User assignee);

    Collection<Ticket> findTicketsByAssignee(User assignee);

    Collection<Ticket> findTicketsByTicketStatus(long ticketStatus);

    Collection<Ticket> findTicketsByTicketType(long ticketType);
}
