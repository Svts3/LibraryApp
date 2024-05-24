package com.example.libraryapp.model;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BaseEntity {

    private Boolean deleted=false;
}
