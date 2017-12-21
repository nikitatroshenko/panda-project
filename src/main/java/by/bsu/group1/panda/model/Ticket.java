package by.bsu.group1.panda.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "PP_TICKETS")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String ticketKey;
    private Project project;
    private User assignee;
    private String name;
    private Date dueDate;
    @Column(name = "ticket_status")
    private long ticketStatus; // migrate to enum
    @Column(name = "ticket_type")
    private long ticketType; // --//--
    private String description;
}