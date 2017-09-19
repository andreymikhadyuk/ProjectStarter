package com.projectstarter.ProjectStarter.model;

import com.projectstarter.ProjectStarter.model.enums.ProjectStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@ToString
@Entity
@Table(name = "projects")
@Getter
@Setter
@NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "title")
    private String title;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "description")
    private String description;

    @Column(name = "target_amount")
    private int targetAmount;

    @Column(name = "current_amount")
    private int currentAmount;

    @Column(name = "rating")
    private double rating;

    @Column(name = "start_date")
    private Timestamp startDate;

    @Column(name = "end_date")
    private Timestamp endDate;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ProjectStatus status;

    @OneToMany(mappedBy = "project",
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<News> newsList;

    @OneToMany(mappedBy = "project",
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Comments> commentsList;

    @OneToMany(mappedBy = "project",
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Goal> goalList;

    @OneToMany(mappedBy = "project",
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DonateSystem> donateSystemList;

    @OneToMany(mappedBy = "project",
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Subscription> subscriptionList;

    @OneToMany(mappedBy = "project",
            cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Donate> donateList;
}
