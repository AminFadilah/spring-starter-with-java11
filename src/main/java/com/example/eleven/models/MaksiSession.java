package com.example.eleven.models;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "maksi_session")
@NoArgsConstructor
@AllArgsConstructor
public class MaksiSession {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDate sessionDate;

    @ManyToOne
    @JoinColumn(name = "chosen_restaurant_id")
    private Restaurant chosenRestaurant;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}
