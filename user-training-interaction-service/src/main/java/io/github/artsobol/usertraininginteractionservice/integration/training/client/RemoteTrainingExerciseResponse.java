package io.github.artsobol.usertraininginteractionservice.integration.training.client;

public record RemoteTrainingExerciseResponse(
        Long id,
        Long exerciseId,
        int orderIndex
) {
}
