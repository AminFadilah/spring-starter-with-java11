package com.example.eleven.controllers;

import com.example.eleven.dto.ApiResponse;
import com.example.eleven.dto.RestaurantDto;
import com.example.eleven.dto.RestaurantRequest;
import com.example.eleven.dto.UserDto;
import com.example.eleven.models.Restaurant;
import com.example.eleven.services.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("${api.base-path}/${api.version}/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping(path = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ApiResponse<Restaurant>> createRestaurant( @RequestBody RestaurantRequest restaurantRequest,
                                                                     @RequestPart("file") MultipartFile image)  throws Exception{
        Restaurant restaurant = restaurantService.createRestaurant(
                restaurantRequest, image
        );
        ApiResponse<Restaurant> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Successfully created",
                restaurant
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getAll")
    public ResponseEntity<ApiResponse<List<RestaurantDto>>> getAllRestaurants() {
        List<RestaurantDto> restaurants = restaurantService.getAllRestaurants();

        ApiResponse<List<RestaurantDto>> response = new ApiResponse<>(
                HttpStatus.OK.value(),
                "Successfully retrieved restaurants",
                restaurants
        );

        return ResponseEntity.ok(response);
    }
}
