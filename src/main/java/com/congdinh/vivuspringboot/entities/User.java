package com.congdinh.vivuspringboot.entities;

import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User extends MasterEntity {
    @Column(nullable = false, columnDefinition = "NVARCHAR(50)")
    private String firstName;

    @Column(nullable = false, columnDefinition = "NVARCHAR(50)")
    private String lastName;

    @Transient
    private String displayName;

    @Column(unique = true, nullable = false, length = 50)
    private String username;

    @Column(unique = true, nullable = false, columnDefinition = "VARCHAR(12)")
    private String phoneNumber;

    private String avatar;

    @Column(nullable = false)
    private String password;

    @ManyToMany
    @JoinTable(
        name = "user_roles",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    //#region Constructors
    public User(String firstName, String lastName, String username, String phoneNumber, String avatar,
            String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.phoneNumber = phoneNumber;
        this.avatar = avatar;
        this.password = password;
    }

    public User() {
    }
    //#endregion

    //#region Getter & Setter

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDisplayName() {
        return this.firstName + " " + this.lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //#endregion
}
