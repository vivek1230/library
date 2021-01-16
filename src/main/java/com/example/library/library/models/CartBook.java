package com.example.library.library.models;



import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "book")
public class CartBook extends Auditable{

    private String name;

    private String author;

    private Double price;

    private Integer stockCount;

}

