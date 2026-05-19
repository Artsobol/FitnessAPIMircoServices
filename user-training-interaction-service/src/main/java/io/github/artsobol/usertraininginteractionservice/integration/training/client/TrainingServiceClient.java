package io.github.artsobol.usertraininginteractionservice.integration.training.client;

public interface TrainingServiceClient {

    RemoteTrainingResponse getTrainingById(Long trainingId);

    default void assertTrainingExists(Long trainingId) {
        getTrainingById(trainingId);
    }
}
