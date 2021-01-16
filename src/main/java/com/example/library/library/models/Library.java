package com.example.library.library.models;


import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "library")
public class Library extends Auditable{

    @OneToMany(cascade = CascadeType.ALL)
    List<Book> books = new ArrayList<>();

}

