package com.projectstarter.ProjectStarter.repository;

import com.projectstarter.ProjectStarter.model.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
}
