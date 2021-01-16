package com.example.library.library.services.serachStrtegies;

import com.example.library.library.models.Book;
import com.example.library.library.models.Student;
import com.example.library.library.services.searchParameters.SearchParameter;

import java.util.List;

public interface SearchStrategy {
    public List<Book> search(Student student, SearchParameter searchParameter);
}
