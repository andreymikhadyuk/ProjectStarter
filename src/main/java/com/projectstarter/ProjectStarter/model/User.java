package com.projectstarter.ProjectStarter.model;

import com.projectstarter.ProjectStarter.model.enums.BlockStatus;
import com.projectstarter.ProjectStarter.model.enums.Role;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String email;

    private String password;

    @OneToOne()
    @JoinColumn(name = "biography_id")
    private Biography biography;


    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "block_status")
    @Enumerated(EnumType.STRING)
    private BlockStatus blockStatus;

    @Column(name = "last_log_in")
    private Date lastLogIn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Biography getBiography() {
        return biography;
    }

    public void setBiography(Biography biography) {
        this.biography = biography;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public BlockStatus getBlockStatus() {
        return blockStatus;
    }

    public void setBlockStatus(BlockStatus blockStatus) {
        this.blockStatus = blockStatus;
    }

    public Date getLastLogIn() {
        return lastLogIn;
    }

    public void setLastLogIn(Date lastLogIn) {
        this.lastLogIn = lastLogIn;
    }
}
