package com.projectstarter.ProjectStarter.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "donate_system")
@Getter
@Setter
@NoArgsConstructor
public class DonateSystem {
    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @Column(name = "amount")
    private int amount;

    @Column(name = "description")
    private String description;


}
