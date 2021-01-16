package com.example.library.library.services.searchParameters;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Setter
@Getter
@AllArgsConstructor
@Service
public class SearchParameterAsName implements SearchParameter {
    private String name;
}

