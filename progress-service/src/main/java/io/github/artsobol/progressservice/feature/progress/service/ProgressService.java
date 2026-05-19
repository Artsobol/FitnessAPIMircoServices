package io.github.artsobol.progressservice.feature.progress.service;

import io.github.artsobol.progressservice.feature.progress.dto.response.UserProgressResponse;

import java.time.LocalDate;

public interface ProgressService {

    UserProgressResponse getProgress(Long userId);

    void recordWorkoutCompleted(Long userId, LocalDate completedDate);
}
