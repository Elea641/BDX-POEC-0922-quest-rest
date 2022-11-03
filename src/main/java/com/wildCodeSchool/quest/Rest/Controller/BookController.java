package com.wildCodeSchool.quest.Rest.Controller;

import com.wildCodeSchool.quest.Rest.Models.Book;
import com.wildCodeSchool.quest.Rest.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @GetMapping("/books")
    public List<Book> index() {
        return bookRepository.findAll();
    }

    @GetMapping("/books/{id}")
    public Book showBook(@PathVariable Long id) {
        return bookRepository.findById(id).get();
    }

    @PostMapping("/books/search")
    public List<Book> search(@RequestBody Map<String, String> body) {
        String searchTerm = body.get("text");
        return  bookRepository.findByTitleContainingOrDescriptionContaining(searchTerm, searchTerm);
    }

    @PostMapping("/books")
    public Book create(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @PutMapping("/blogs/{id}")
    public Book update(@PathVariable Long id, @RequestBody Book book) {
        Book bookToUpdate = bookRepository.findById(id).get();
        bookToUpdate.setTitle(book.getTitle());
        bookToUpdate.setAuthor(book.getAuthor());
        bookToUpdate.setDescription((book.getDescription()));
        return bookRepository.save(bookToUpdate);
    }

    @DeleteMapping("books/{id}")
    public boolean delete(@PathVariable Long id) {
       bookRepository.deleteById(id);
       return true;
    }
}
