package com.projectstarter.ProjectStarter.repository;

import com.projectstarter.ProjectStarter.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    Project findById(Long id);
    List<Project> findAllByUserId(Long userId);
}