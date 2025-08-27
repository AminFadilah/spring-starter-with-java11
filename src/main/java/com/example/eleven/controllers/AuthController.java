package  com.example.eleven.controllers;

import com.example.eleven.dto.ApiResponse;
import com.example.eleven.dto.LoginRequest;
import com.example.eleven.dto.LoginResponse;
import com.example.eleven.dto.RegisterRequest;
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
    public ResponseEntity<ApiResponse<User>> register(@RequestBody RegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());

        User createdUser = userService.register(user);

        ApiResponse<User> response = new ApiResponse<>(
                HttpStatus.CREATED.value(),
                "User registered successfully",
                createdUser
        );

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(summary = "Login user and get JWT token")
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@RequestBody LoginRequest request) {
        return userService.login(request.getUsername(), request.getPassword())
                .map(user -> {
                    LoginResponse loginResponse = new LoginResponse(jwtUtil.generateToken(user.getUsername()));
                    ApiResponse<LoginResponse> response = new ApiResponse<>(
                            HttpStatus.OK.value(),
                            "Login successful",
                            loginResponse
                    );
                    return ResponseEntity.ok(response);
                })
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));
    }
}
