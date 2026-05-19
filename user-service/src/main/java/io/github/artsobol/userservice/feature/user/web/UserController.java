package io.github.artsobol.userservice.feature.user.web;

import io.github.artsobol.userservice.feature.user.dto.response.UserResponse;
import io.github.artsobol.userservice.feature.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Validated
@RestController
@Tag(name = "User")
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping(params = "ids")
    @Operation(summary = "Get users by ids")
    @ApiResponses({
            @ApiResponse(responseCode = "200")
    })
    public List<UserResponse> getByIds(
            @Parameter(description = "User identifiers", example = "1,2,3")
            @RequestParam @NotEmpty @Size(max = 100) List<@Positive Long> ids
    ) {
        return userService.getByIds(ids);
    }
}
