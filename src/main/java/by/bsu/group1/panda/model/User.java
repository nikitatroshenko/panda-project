package by.bsu.group1.panda.model;

import javax.persistence.*;

@Entity
@Table(name = "PP_USERS",
        catalog="PANDA_PROJECT",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"username"})
        })
public class User {

    @Id
    private long id;
    private String username;
    private long role; // TODO: migrate to enum
    @OneToMany
    @JoinColumn(name = "project_id")
    private Project project;
}