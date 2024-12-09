package com.congdinh.vivuspringboot.entities;

import java.util.UUID;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(unique = true, nullable = false, columnDefinition = "NVARCHAR(255)")
    private String name;

    @Column(columnDefinition = "NVARCHAR(500)")
    private String description;
}
