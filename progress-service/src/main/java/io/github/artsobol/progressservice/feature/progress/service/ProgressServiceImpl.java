package io.github.artsobol.progressservice.feature.progress.service;

import io.github.artsobol.progressservice.feature.progress.dto.response.UserProgressResponse;
import io.github.artsobol.progressservice.feature.progress.entity.UserProgress;
import io.github.artsobol.progressservice.feature.progress.repository.UserProgressRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProgressServiceImpl implements ProgressService {

    private final UserProgressRepository userProgressRepository;

    @Override
    @Transactional(readOnly = true)
    public UserProgressResponse getProgress(Long userId) {
        log.debug("Fetching progress userId={}", userId);

        return userProgressRepository.findById(userId)
                .map(this::toResponse)
                .orElseGet(() -> new UserProgressResponse(0, 0, 0, null));
    }

    @Override
    @Transactional
    public void recordWorkoutCompleted(Long userId, LocalDate completedDate) {
        UserProgress entity = userProgressRepository.findById(userId)
                .orElseGet(() -> UserProgress.create(userId));

        entity.recordWorkoutCompleted(completedDate);
        userProgressRepository.save(entity);
    }

    private UserProgressResponse toResponse(UserProgress entity) {
        return new UserProgressResponse(
                entity.getTotalWorkouts(),
                entity.getCurrentStreak(),
                entity.getLongestStreak(),
                entity.getLastWorkoutDate()
        );
    }
}
