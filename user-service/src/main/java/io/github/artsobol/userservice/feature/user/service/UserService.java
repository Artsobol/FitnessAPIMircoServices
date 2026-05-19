package io.github.artsobol.userservice.feature.user.service;

import io.github.artsobol.userservice.feature.user.dto.request.CreateUserRequest;
import io.github.artsobol.userservice.feature.user.dto.response.UserResponse;
import io.github.artsobol.userservice.feature.user.entity.User;

import java.util.List;

public interface UserService {

    User createUser(CreateUserRequest request);

    User findByUsername(String username);

    List<UserResponse> getByIds(List<Long> ids);
}
