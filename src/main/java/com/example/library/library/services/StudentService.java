package com.example.library.library.services;

import com.example.library.library.exceptions.InvalidBookException;
import com.example.library.library.exceptions.InvalidStudentException;
import com.example.library.library.models.Book;
import com.example.library.library.models.CartBook;
import com.example.library.library.models.Student;
import com.example.library.library.services.searchParameters.SearchParameter;
import com.example.library.library.services.searchParameters.SearchParameterAsAuthor;
import com.example.library.library.services.searchParameters.SearchParameterAsName;
import com.example.library.library.services.serachStrtegies.SearchStrategy;
import com.example.library.library.services.serachStrtegies.SearchStrategyByAuthor;
import com.example.library.library.services.serachStrtegies.SearchStrategyByName;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    public List<Book> searchBooksByName(Student student, String bookName) {
        SearchStrategy searchStrategyByName = new SearchStrategyByName();
        SearchParameter searchParameterAsName = new SearchParameterAsName(bookName);
        return searchStrategyByName.search(student, searchParameterAsName);
    }

    public List<Book> searchBooksByAuthor(Student student, String authorName) {
        SearchStrategy searchStrategyByAuthor = new SearchStrategyByAuthor();
        SearchParameter searchParameterAsAuthor = new SearchParameterAsAuthor(authorName);
        return searchStrategyByAuthor.search(student, searchParameterAsAuthor);
    }

    public void checkOutBook(Student student, Book book) {
        if(student.getLibrary().getBooks().contains(book)){
            student.getLibrary().getBooks().forEach(libraryBook -> {
                if(libraryBook.equals(book)){
                    if(libraryBook.getStockCount() > 0){
                        libraryBook.setStockCount(libraryBook.getStockCount() - 1);
                        if(student.getCart().getCartBooks().contains(libraryBook)){
                            student.getCart().getCartBooks().forEach(cartBook -> {
                                cartBook.setStockCount(cartBook.getStockCount() + 1);
                            });
                        }
                        else {
                            student.getCart().getCartBooks()
                                    .add(CartBook.builder()
                                            .name(libraryBook.getName())
                                            .author(libraryBook.getAuthor())
                                            .price(libraryBook.getPrice())
                                            .stockCount(1)
                                            .build()
                                    );
                        }
                    }
                    else{
                        throw new InvalidBookException("Library does't have sufficent number of quantity of book with id " + book.getId());
                    }
                }
            });
        }
        else{
            throw new InvalidBookException("Library does't have book with id " + book.getId());
        }
    }

    public void returnBook(Student student, CartBook cartBook) {
        if(student.getCart().getCartBooks().contains(cartBook)){
            student.getCart().getCartBooks().forEach(studentCartBook -> {
                if(studentCartBook.equals(cartBook)){
                    if(student.getLibrary().getBooks().contains(studentCartBook)){
                        student.getLibrary().getBooks().forEach(libraryBook -> {
                            libraryBook.setStockCount(libraryBook.getStockCount() + studentCartBook.getStockCount());
                        });
                    }
                    else {
                        student.getLibrary().getBooks().add(
                                Book.builder()
                                        .name(studentCartBook.getName())
                                        .author(studentCartBook.getAuthor())
                                        .price(studentCartBook.getPrice())
                                        .stockCount(studentCartBook.getStockCount())
                                        .build()
                        );
                    }
                    student.getCart().getCartBooks().remove(cartBook);
                }
            });
        }
        else{
            throw new InvalidStudentException("No book is there in student cart with id " + cartBook.getId());
        }
    }
}
