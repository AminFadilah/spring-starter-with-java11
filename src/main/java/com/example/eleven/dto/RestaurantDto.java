package com.example.eleven.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDto {
    private Long id;
    private String name;
    private String category;
    private String priceRange;
    private String location;
    private String createdByUsername;
}
