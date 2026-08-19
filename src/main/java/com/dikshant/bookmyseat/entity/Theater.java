package com.dikshant.bookmyseat.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "theaters")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Theater {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @ManyToOne// Many theaters can belong to one city
    @JoinColumn(name = "city_id", nullable = false) // Foreign key column in 'theaters' table referencing 'city'
    private City city;

}
/*
* +----------------+        MANY TO ONE        +----------------+
|    Theater     | ----------------------->  |      City      |
+----------------+                          +----------------+
| id (PK)        |                          | id (PK)        |
| name           |                          | name           |
| address        |                          | state          |
| city_id (FK)   |                          | country        |
+----------------+                          +----------------+

        MANY                                  ONE
   (Many Theaters)                   (One City)*/