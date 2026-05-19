package io.github.artsobol.trainingservice.feature.exercise.dto.response;

import io.github.artsobol.trainingservice.feature.exercise.entity.MuscleGroup;
import io.github.artsobol.trainingservice.feature.training.training.entity.TrainingLevel;

import java.time.Instant;
import java.util.List;
import java.util.Set;

public record ExerciseResponse(
        Long id,
        String title,
        String description,
        List<ExerciseVideoResponse> videos,
        MuscleGroup muscleGroup,
        TrainingLevel trainingLevel,
        Long authorId,
        Instant createdAt,
        Instant updatedAt
) {
}
