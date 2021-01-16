package com.example.library.library.services;

import com.example.library.library.models.Admin;
import com.example.library.library.models.Book;
import org.springframework.stereotype.Service;


@Service
public class AdminService {
    public void addBook(Admin admin, Book book) {
        admin.getLibrary().getBooks().add(book);
    }

    public void deleteBook(Admin admin, Book book) {
        admin.getLibrary().getBooks().remove(book);
    }
}
