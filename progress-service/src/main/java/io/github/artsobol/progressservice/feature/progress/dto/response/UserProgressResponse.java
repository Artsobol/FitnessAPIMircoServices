package io.github.artsobol.progressservice.feature.progress.dto.response;

import java.time.LocalDate;

public record UserProgressResponse(
        int totalWorkouts,
        int currentStreak,
        int longestStreak,
        LocalDate lastWorkoutDate
) {
}
