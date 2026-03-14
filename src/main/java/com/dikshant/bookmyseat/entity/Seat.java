package com.dikshant.bookmyseat.entity;

import com.dikshant.bookmyseat.enums.SeatType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "seats")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "seat_number", nullable = false)
    private String seatNumber;

    @Column(name = "seat_row", nullable = false)
    private String row;

    @Column(name = "seat_col", nullable = false)
    private Integer col;

    @Enumerated(EnumType.STRING)
    private SeatType seatType;

    @ManyToOne //persepective entity
    @JoinColumn(name = "screen_id", nullable = false)
    private Screen screen;



}
