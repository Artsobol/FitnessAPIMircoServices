package io.github.artsobol.usertraininginteractionservice.integration.training.client;

import java.util.List;

public record RemoteTrainingResponse(
        Long id,
        List<RemoteTrainingExerciseResponse> exercises
) {
}
