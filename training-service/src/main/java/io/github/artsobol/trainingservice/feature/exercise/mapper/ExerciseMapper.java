package io.github.artsobol.trainingservice.feature.exercise.mapper;

import io.github.artsobol.common.config.persistence.MapStructConfig;
import io.github.artsobol.trainingservice.feature.exercise.dto.response.ExerciseResponse;
import io.github.artsobol.trainingservice.feature.exercise.dto.response.ExerciseVideoResponse;
import io.github.artsobol.trainingservice.feature.exercise.entity.Exercise;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(config = MapStructConfig.class)
public interface ExerciseMapper {

    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "entity.id")
    @Mapping(target = "title", source = "entity.title")
    @Mapping(target = "description", source = "entity.description")
    @Mapping(target = "videos", source = "videos")
    @Mapping(target = "muscleGroup", source = "entity.muscleGroup")
    @Mapping(target = "trainingLevel", source = "entity.trainingLevel")
    @Mapping(target = "authorId", source = "entity.authorId")
    @Mapping(target = "createdAt", source = "entity.createdAt")
    @Mapping(target = "updatedAt", source = "entity.updatedAt")
    ExerciseResponse toResponse(Exercise entity, List<ExerciseVideoResponse> videos);
}
