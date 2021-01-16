package com.example.library.library.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "namedlocation")
public class NamedLocation extends Auditable {

    private String name;
    private String zipCode;
    private String city;
    private String country;
    private String state;
}
