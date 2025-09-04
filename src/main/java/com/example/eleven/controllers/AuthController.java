package  com.example.eleven.controllers;

import com.example.eleven.dto.*;
import com.example.eleven.models.User;
import com.example.eleven.security.JwtUtil;
import com.example.eleven.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.base-path}/${api.version}/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;

    public AuthController(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @Operation(summary = "Register new user")
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserDto>> register(@RequestBody RegisterRequest request) {
        UserDto createdUser = userService.register(request);

        ApiResponse<UserDto> response = new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "User registered successfully",
                createdUser
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Login user and get JWT token")
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginRequest request) {
        LoginResponse loginResponse = userService.login(request);
        ApiResponse<LoginResponse> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Login successful",
                loginResponse
        );
        return ResponseEntity.ok(response);
    }
}
