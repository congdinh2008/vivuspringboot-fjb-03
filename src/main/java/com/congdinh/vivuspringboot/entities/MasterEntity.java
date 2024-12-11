package com.congdinh.vivuspringboot.entities;

import java.time.ZonedDateTime;

import org.hibernate.annotations.TimeZoneColumn;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class MasterEntity extends BaseEntity {
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

    public ZonedDateTime getInsertedAt() {
        return insertedAt;
    }

    public void setInsertedAt(ZonedDateTime insertedAt) {
        this.insertedAt = insertedAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public ZonedDateTime getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(ZonedDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
