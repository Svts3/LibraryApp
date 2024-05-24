package com.example.libraryapp.controller;

import com.example.libraryapp.dto.AuthorDTO;
import com.example.libraryapp.mapper.AuthorMapper;
import com.example.libraryapp.model.Author;
import com.example.libraryapp.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private AuthorService authorService;
    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/")
    public ResponseEntity<List<AuthorDTO>> findAll(){
        List<Author>authors = authorService.findAll();
        List<AuthorDTO> authorDTOs = AuthorMapper.INSTANCE.authorsToAuthorDTOs(authors);
        return ResponseEntity.ok(authorDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDTO>findById(@PathVariable("id")Long id){
        Author author = authorService.findById(id);
        AuthorDTO authorDTO = AuthorMapper.INSTANCE.authorToAuthorDTO(author);
        return ResponseEntity.ok(authorDTO);
    }
    @GetMapping("/book/{id}")
    public ResponseEntity<List<AuthorDTO>> findByBooksId(@PathVariable(name = "id") Long bookId){
        List<Author> authors = authorService.findByBooksId(bookId);
        List<AuthorDTO>authorDTOS = AuthorMapper.INSTANCE.authorsToAuthorDTOs(authors);
        return ResponseEntity.ok(authorDTOS);
    }

    @PostMapping("/")
    public ResponseEntity<AuthorDTO>save(@RequestBody Author author){
        authorService.save(author);
        AuthorDTO authorDTO = AuthorMapper.INSTANCE.authorToAuthorDTO(author);
        return ResponseEntity.ok(authorDTO);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<AuthorDTO>update(@PathVariable("id")Long id, @RequestBody Author author){
        Author updatedAuthor = authorService.update(author, id);
        AuthorDTO authorDTO = AuthorMapper.INSTANCE.authorToAuthorDTO(updatedAuthor);
        return ResponseEntity.ok(authorDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AuthorDTO>deleteById(@PathVariable("id")Long id){
        Author author = authorService.deleteById(id);
        AuthorDTO authorDTO = AuthorMapper.INSTANCE.authorToAuthorDTO(author);
        return ResponseEntity.ok(authorDTO);
    }









}
