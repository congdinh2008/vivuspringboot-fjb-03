package com.congdinh.vivuspringboot.entities;

import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class Role extends MasterEntity {
    @Column(unique = true, nullable = false, columnDefinition = "NVARCHAR(50)")
    private String name;

    @Column(columnDefinition = "NVARCHAR(500)")
    private String description;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    //#region Getter & Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //#endregion

    //#region Constructors
    public Role(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Role() {
    }
    //#endregion
}
