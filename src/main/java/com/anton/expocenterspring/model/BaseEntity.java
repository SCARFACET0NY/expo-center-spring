package com.anton.expocenterspring.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class BaseEntity {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
