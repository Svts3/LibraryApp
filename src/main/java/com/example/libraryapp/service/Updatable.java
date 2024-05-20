package com.example.libraryapp.service;

public interface Updatable<T, ID> {

    T update(T entity, ID id);


}
