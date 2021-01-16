package com.example.library.library.services.serachStrtegies;

import com.example.library.library.models.Book;
import com.example.library.library.models.Student;
import com.example.library.library.services.searchParameters.SearchParameter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchStrategyByName implements SearchStrategy {
    @Override
    public List<Book> search(Student student, SearchParameter searchParameter) {
        return student.getLibrary().getBooks().stream().filter(book -> {
            return book.getName().equals(searchParameter.toString());
        }).collect(Collectors.toList());
    }
}
