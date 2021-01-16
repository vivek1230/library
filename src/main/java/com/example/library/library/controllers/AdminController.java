package com.example.library.library.controllers;

import com.example.library.library.exceptions.InvalidAdminException;
import com.example.library.library.exceptions.InvalidBookException;
import com.example.library.library.models.Admin;
import com.example.library.library.models.Book;
import com.example.library.library.repositories.AdminRepository;
import com.example.library.library.repositories.BookRepository;
import com.example.library.library.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminRepository adminRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AdminService adminService;



    @PostMapping("/{adminId}/Book")
    public void addBook(@PathVariable(name = "adminId") Long adminId,
                        @RequestBody Book data){
        Optional<Admin> admin = adminRepository.findById(adminId);
        if(admin.isEmpty()){
            throw new InvalidAdminException("No customer with id" + adminId);
        }
        Book book = Book.builder().author(data.getAuthor())
                .name(data.getName())
                .price(data.getPrice())
                .stockCount(data.getStockCount())
                .build();
        adminService.addBook(admin.get(), book);
    }

    @DeleteMapping("/{adminId}/Book/{bookId}")
    public void deleteBook(@PathVariable(name = "adminId") Long adminId,
                        @PathVariable(name = "bookId") Long bookId){
        Optional<Admin> admin = adminRepository.findById(adminId);
        if(admin.isEmpty()){
            throw new InvalidAdminException("No customer with id" + adminId);
        }
        Optional<Book> book = bookRepository.findById(bookId);
        if(book.isEmpty()){
            throw new InvalidBookException("No customer with id" + bookId);
        }
        adminService.deleteBook(admin.get(), book.get());
    }
}
