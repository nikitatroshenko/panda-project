package by.bsu.group1.panda.service.impl;

import by.bsu.group1.panda.dao.ProjectRepository;
import by.bsu.group1.panda.dao.TicketRepository;
import by.bsu.group1.panda.dao.UserRepository;
import by.bsu.group1.panda.model.Project;
import by.bsu.group1.panda.model.Ticket;
import by.bsu.group1.panda.model.User;
import by.bsu.group1.panda.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Collection<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket getTicketById(long id) {
        return ticketRepository.getOne(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Ticket createTicket(String username, String projectKey, Ticket ticket) {
        Project project = projectRepository.findProjectByProjectKey(projectKey);
        User reporter = userRepository.findUserByUsername(username);

        if (reporter == null) {
            throw new RuntimeException("User with username '" + username + "' not found");
        }

//        if (ticket.getAssignee() != null) {
//            User assignee = userRepository.getOne(ticket.getAssignee().getId());
//
//            ticket.setAssignee(assignee);
//        }

        ticket.setReporter(reporter);
        ticket.setProject(project);
        return ticketRepository.save(ticket);
    }

    @Override
    public void updateTicket(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    @Override
    public void deleteTicket(long id) {
        ticketRepository.delete(id);
    }

    @Override
    public Collection<Ticket> getTicketsByReporter(String reporter) {
        User reporterUser = userRepository.findUserByUsername(reporter);

        if (reporterUser == null) {
            throw new RuntimeException("User with username '" + reporter + "' not found");
        }

        return ticketRepository.findTicketsByReporter(reporterUser);
    }
}
