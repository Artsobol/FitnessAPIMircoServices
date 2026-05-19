package io.github.artsobol.progressservice.feature.progress.repository;

import io.github.artsobol.progressservice.feature.progress.entity.UserProgress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProgressRepository extends JpaRepository<UserProgress, Long> {
}
