package com.dikshant.bookmyseat.entity;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "screens")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Screen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "total_seats", nullable = false)
    private Integer totalSeats;

    @ManyToOne// Many screens belong to one theater
    @JoinColumn(name = "theater_id", nullable = false)
    private Theater theater;
}
/*                MANY TO ONE
+----------------+        --------->       +----------------+
|     Screen     |                         |    Theater     |
+----------------+                         +----------------+
| id (PK)        |                         | id (PK)        |
| name           |                         | name           |
| total_seats    |                         | address        |
| theater_id FK  |                         | city_id FK     |
+----------------+                         +----------------+

     MANY SCREENS               ONE THEATER*/