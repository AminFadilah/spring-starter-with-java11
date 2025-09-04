package com.example.eleven.controllers;

import com.example.eleven.dto.ApiResponse;
import com.example.eleven.dto.UserDto;
import com.example.eleven.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("${api.base-path}/${api.version}/user")
public class UserController {
    private UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<List<UserDto>>> getAllUser() {
        List<UserDto> users = userService.getAllUser();

        ApiResponse<List<UserDto>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Successfully retrieved users",
                users
        );

        return ResponseEntity.ok(response);
    }
}
