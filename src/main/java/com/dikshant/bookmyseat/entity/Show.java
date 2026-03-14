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

    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie; //bhot sare show  ek movie ke ho sakte hai.

    @ManyToOne
    @JoinColumn(name = "screen_id", nullable = false)
    private Screen screen;//Many shows can belong to one screen

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    private LocalTime endTime;

    private Double ticketPrice;


}
