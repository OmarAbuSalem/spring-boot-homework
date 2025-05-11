package com.example.demo.controller;

import com.example.demo.model.Author;
import com.example.demo.model.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BookController {

    private List<Author> authors = new ArrayList<>();
    private List<Book> books = new ArrayList<>();

    public BookController() {
        // Sample authors
        authors.add(new Author(1L, "George Orwell"));
        authors.add(new Author(2L, "Jane Austen"));

        // Sample books
        books.add(new Book(1L, "1984", authors.get(0)));
        books.add(new Book(2L, "Pride and Prejudice", authors.get(1)));
    }

    @GetMapping("/books")
    public String getAllBooks(Model model) {
        model.addAttribute("books", books);
        return "book/list";
    }

    @GetMapping("/books/new")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authors);
        return "book/add";
    }

    @PostMapping("/books")
    public String addBook(@ModelAttribute Book book, @RequestParam Long authorId) {
        Author selectedAuthor = authors.stream()
                .filter(a -> a.getId().equals(authorId))
                .findFirst()
                .orElse(null);

        book.setId((long) (books.size() + 1));
        book.setAuthor(selectedAuthor);
        books.add(book);

        return "redirect:/books";
    }
}
