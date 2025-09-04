package com.example.eleven.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantRequest {
    private String name;
    private String category;
    private String priceRange;
    private String location;
    private MultipartFile image;
    private Long createdById;    // ID user yang membuat
}
