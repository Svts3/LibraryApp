package com.example.libraryapp.service.impl;

import com.example.libraryapp.exception.AuthorNotFoundException;
import com.example.libraryapp.mapper.AuthorMapper;
import com.example.libraryapp.model.Author;
import com.example.libraryapp.repository.AuthorRepository;
import com.example.libraryapp.service.AuthorService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository  authorRepository;


    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> findByBooksId(Long bookId) {
        return authorRepository.findByBooksId(bookId);
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author save(Author entity) {
        return authorRepository.save(entity);
    }

    @Override
    public Author findById(Long aLong) {
        return authorRepository.findById(aLong).orElseThrow(
                ()->new AuthorNotFoundException(String.format("Author[ID=%d] was not found!", aLong)));
    }

    @Transactional
    @Override
    public Author update(Author entity, Long aLong) {
        Author author = findById(aLong);
        AuthorMapper.INSTANCE.updateAuthor(author, entity);
        return author;
    }

    @Transactional
    @Override
    public Author deleteById(Long aLong) {
       Author author = findById(aLong);
       authorRepository.deleteById(aLong);
       return author;
    }
}
