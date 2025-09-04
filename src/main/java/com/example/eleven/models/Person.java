package com.example.eleven.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "persons")
@Data
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "summary")
    private String summary;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

}
