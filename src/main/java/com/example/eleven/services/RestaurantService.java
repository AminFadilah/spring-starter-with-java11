package com.example.eleven.services;

import com.example.eleven.dto.RestaurantDto;
import com.example.eleven.dto.RestaurantRequest;
import com.example.eleven.models.Restaurant;
import com.example.eleven.models.User;
import com.example.eleven.repositories.RestaurantRepository;
import com.example.eleven.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final UserRepository userRepository;

    public Restaurant createRestaurant(RestaurantRequest restaurantRequest,
                                       MultipartFile image
    ) throws Exception {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantRequest.getName());
        restaurant.setCategory(restaurantRequest.getCategory());
        restaurant.setPriceRange(restaurantRequest.getPriceRange());
        restaurant.setLocation(restaurantRequest.getLocation());
        restaurant.setCreatedAt(LocalDateTime.now());

        Optional<User> userOpt = userRepository.findById(restaurantRequest.getCreatedById());
        if (userOpt.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        restaurant.setCreatedBy(userOpt.get());

        if (image != null && !image.isEmpty()) {
            restaurant.setImage(image.getBytes());
        }

        return restaurantRepository.save(restaurant);
    }

    public List<RestaurantDto> getAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurants.stream().map(r -> {
            RestaurantDto dto = new RestaurantDto();
            dto.setId(r.getId());
            dto.setName(r.getName());
            dto.setCategory(r.getCategory());
            dto.setPriceRange(r.getPriceRange());
            dto.setLocation(r.getLocation());
            dto.setCreatedByUsername(r.getCreatedBy() != null ? r.getCreatedBy().getUsername() : null);
            return dto;
        }).collect(Collectors.toList());
    }
}
