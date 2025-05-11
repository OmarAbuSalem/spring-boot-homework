package com.example.demo.controller;

import com.example.demo.model.Author;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class AuthorController {

    // 🧠 In-memory list to hold authors
    private List<Author> authors = new ArrayList<>();

    // Initialize with some authors
    public AuthorController() {
        authors.add(new Author(1L, "George Orwell"));
        authors.add(new Author(2L, "Jane Austen"));
    }

    @GetMapping("/authors")
    public String getAllAuthors(Model model) {
        model.addAttribute("authors", authors);
        return "author/list";
    }

    @GetMapping("/authors/new")
    public String showAddAuthorForm(Model model) {
        model.addAttribute("author", new Author());
        return "author/add";
    }

    @PostMapping("/authors")
    public String addAuthor(@ModelAttribute Author author) {
        // Auto-assign an ID based on list size
        author.setId((long) (authors.size() + 1));
        authors.add(author);
        return "redirect:/authors";
    }
}
