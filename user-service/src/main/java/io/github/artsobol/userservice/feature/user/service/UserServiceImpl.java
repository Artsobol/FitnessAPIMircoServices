package io.github.artsobol.userservice.feature.user.service;

import io.github.artsobol.common.exception.http.ConflictException;
import io.github.artsobol.common.exception.http.NotFoundException;

import io.github.artsobol.userservice.feature.user.dto.request.CreateUserRequest;
import io.github.artsobol.userservice.feature.user.dto.response.UserResponse;
import io.github.artsobol.userservice.feature.user.entity.Role;
import io.github.artsobol.userservice.feature.user.entity.User;
import io.github.artsobol.userservice.feature.user.mapper.UserMapper;
import io.github.artsobol.userservice.feature.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserFinder {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public User createUser(CreateUserRequest request) {
        log.info("Creating user username={}", request.username());
        ensureUniqueUsername(request.username());
        ensureUniqueEmail(request.email());

        User entity = User.create(request.username(), request.email(), request.passwordHash());
        entity.changeRole(Role.USER);
        userRepository.save(entity);

        log.info("User created userId={} username={}", entity.getId(), entity.getUsername());
        return entity;
    }

    @Override
    public User findByIdOrThrow(Long userId) {
        log.debug("Fetching user userId={}", userId);
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundException("user.not.found"));
    }

    @Override
    public User findByEmailOrThrow(String email) {
        log.debug("Fetching user email={}", email);
        return userRepository.findByEmail(email).orElseThrow(() -> new NotFoundException("user.not.found"));
    }

    private void ensureUniqueEmail(String email) {
        log.debug("Checking user uniqueness userEmail={}", email);
        if (userRepository.existsByEmail(email)) {
            throw new ConflictException("user.email.exists");
        }
    }

    @Override
    public User findByUsername(String username) {
        log.debug("Fetching user username={}", username);
        return userRepository.findByUsername(username).orElseThrow(
                () -> new NotFoundException("user.not.found")
        );
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getByIds(List<Long> ids) {
        log.debug("Fetching users ids={}", ids);
        Map<Long, Integer> orderById = new HashMap<>();
        for (int i = 0; i < ids.size(); i++) {
            orderById.putIfAbsent(ids.get(i), i);
        }

        return userRepository.findByIdIn(orderById.keySet()).stream()
                .sorted(Comparator.comparingInt(user -> orderById.get(user.getId())))
                .map(userMapper::toResponse)
                .toList();
    }

    private void ensureUniqueUsername(String username) {
        log.debug("Checking user uniqueness username={}", username);
        if (userRepository.existsByUsername(username)) {
            throw new ConflictException("user.username.exists");
        }
    }
}
