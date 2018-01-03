package by.bsu.group1.panda.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "pp_tickets")
public class Ticket implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ticket_id")
    private long id;
    @Basic
    @Column(name = "ticket_key", unique = true)
    private String ticketKey;
    @ManyToOne
    @JoinColumn(name="project_id")
    private Project project;
    private User assignee;
    private String name;
    @Temporal(TemporalType.DATE)
    @Column(name = "due_date")
    private Date dueDate;
    @Basic
    @Column(name = "ticket_status_id")
    private long ticketStatus; // migrate to enum
    @Basic
    @Column(name = "ticket_type_id")
    private long ticketType; // --//--
    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTicketKey() {
        return ticketKey;
    }

    public void setTicketKey(String ticketKey) {
        this.ticketKey = ticketKey;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public long getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(long ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    public long getTicketType() {
        return ticketType;
    }

    public void setTicketType(long ticketType) {
        this.ticketType = ticketType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;
        Ticket ticket = (Ticket) o;
        return getId() == ticket.getId();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId());
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", ticketKey='" + ticketKey + '\'' +
                ", project=" + project +
                ", assignee=" + assignee +
                ", name='" + name + '\'' +
                ", dueDate=" + dueDate +
                ", ticketStatus=" + ticketStatus +
                ", ticketType=" + ticketType +
                ", description='" + description + '\'' +
                '}';
    }
}