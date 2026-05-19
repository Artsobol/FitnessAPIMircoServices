package io.github.artsobol.usertraininginteractionservice.integration.training.projection;

public record TrainingCatalogExerciseSnapshot(
        Long trainingExerciseId,
        Long exerciseId,
        int orderIndex
) {
}
