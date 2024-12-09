package com.congdinh.vivuspringboot.entities;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.UUID;

import org.hibernate.annotations.TimeZoneColumn;

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

    @TimeZoneColumn
    @Column(nullable = false, columnDefinition = "DATETIMEOFFSET")
    private ZonedDateTime insertedAt;

    @TimeZoneColumn
    @Column(columnDefinition = "DATETIMEOFFSET")
    private ZonedDateTime updatedAt;

    @TimeZoneColumn
    @Column(columnDefinition = "DATETIMEOFFSET")
    private ZonedDateTime deletedAt;

    @Column(nullable = false)
    private boolean active;
}
