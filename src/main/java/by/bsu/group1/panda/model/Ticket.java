package by.bsu.group1.panda.model;

import java.util.Date;

public class Ticket {

	private String ticketKey;
	private Project project;
	private User assignee;
	private String name;
	private Date dueDate;
	private long ticketStatus; // migrate to enum
	private long ticketType; // --//--
	private String description;
}