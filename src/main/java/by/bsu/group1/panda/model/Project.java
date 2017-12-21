package by.bsu.group1.panda.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Project {

    @Id
    private long id;
    @OneToMany
    @JoinColumn(name = "manager")
    private User manager;
    private String projectKey;
    private String description;
}