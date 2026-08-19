package com.dikshant.bookmyseat.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;


@Entity
@Table(name = "shows")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "show_date", nullable = false)
    private LocalDate showDate;

    @ManyToOne// Many shows can belong to one movie
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie; //bhot sare show  ek movie ke ho sakte hai.

    @ManyToOne//Many shows can be scheduled on one screen
    @JoinColumn(name = "screen_id", nullable = false)
    private Screen screen;//Many shows can belong to one screen

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    private LocalTime endTime;

    private Double ticketPrice;


}
/*
*                 MANY TO ONE                    MANY TO ONE
+----------------+       --------->     +----------------+
|      Show      |                      |     Movie       |
+----------------+                      +----------------+
| id (PK)        |                      | id (PK)        |
| show_date      |                      | name           |
| start_time     |                      +----------------+
| end_time       |
| ticket_price   |
| movie_id (FK)  |
| screen_id (FK) |
+----------------+
        |
        | MANY TO ONE
        v
+----------------+
|    Screen      |
+----------------+
| id (PK)        |
| name           |
| theater_id FK  |
+----------------+*/
