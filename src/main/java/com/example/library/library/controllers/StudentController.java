package com.example.library.library.controllers;

import com.example.library.library.exceptions.InvalidBookException;
import com.example.library.library.exceptions.InvalidStudentException;
import com.example.library.library.models.Book;
import com.example.library.library.models.CartBook;
import com.example.library.library.models.Student;
import com.example.library.library.repositories.BookRepository;
import com.example.library.library.repositories.CartBookRepository;
import com.example.library.library.repositories.StudentRepository;
import com.example.library.library.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CartBookRepository cartBookRepository;

    @Autowired
    StudentService studentService;

    @GetMapping("/{studentId}/book/{bookName}")
    public List<Book> serachBookByName(@PathVariable(name = "studentId") Long studentId,
                                       @PathVariable(name = "bookName") String bookName){
        Optional<Student> student = studentRepository.findById(studentId);
        if(student.isEmpty()){
            throw new InvalidStudentException("No customer with id" + studentId);
        }
        return studentService.searchBooksByName(student.get(), bookName);
    }

    @GetMapping("/{studentId}/book/{authorName}")
    public List<Book> serachBookByAuthor(@PathVariable(name = "studentId") Long studentId,
                                       @PathVariable(name = "authorName") String authorName){
        Optional<Student> student = studentRepository.findById(studentId);
        if(student.isEmpty()){
            throw new InvalidStudentException("No customer with id" + studentId);
        }
        return studentService.searchBooksByAuthor(student.get(), authorName);
    }

    @PostMapping("/{studentId}/book/{bookId}")
    public void checkOutBook(@PathVariable(name = "studentId") Long studentId,
                            @PathVariable(name = "bookId") Long bookId){
        Optional<Student> student = studentRepository.findById(studentId);
        if(student.isEmpty()){
            throw new InvalidStudentException("No customer with id" + studentId);
        }
        Optional<Book> book = bookRepository.findById(bookId);
        if(book.isEmpty()){
            throw new InvalidBookException("No customer with id" + bookId);
        }
        studentService.checkOutBook(student.get(), book.get());
    }

    @DeleteMapping("/{studentId}/book/{cartBookId}")
    public void returnBook(@PathVariable(name = "studentId") Long studentId,
                             @PathVariable(name = "cartBookId") Long cartBookId){
        Optional<Student> student = studentRepository.findById(studentId);
        if(student.isEmpty()){
            throw new InvalidStudentException("No customer with id" + studentId);
        }
        Optional<CartBook> cartBook = cartBookRepository.findById(cartBookId);
        if(cartBook.isEmpty()){
            throw new InvalidBookException("No customer with id" + cartBookId);
        }
        studentService.returnBook(student.get(), cartBook.get());
    }
}
