package com.example.libraryapp.service;

import java.util.List;

public interface Findable<T, ID> {

    List<T> findAll();

    T findById(ID id);
}
