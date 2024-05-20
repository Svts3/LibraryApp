package com.example.libraryapp.service;

public interface Persistable<T, ID> {

    T save(T entity);

    T deleteById(ID id);
}
