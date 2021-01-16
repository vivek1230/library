package com.example.library.library.models;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart")
public class Cart extends Auditable{

    @OneToMany(cascade = CascadeType.ALL)
    List<CartBook> cartBooks = new ArrayList<>();

}
