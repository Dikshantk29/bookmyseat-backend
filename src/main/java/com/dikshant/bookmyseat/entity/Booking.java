package com.dikshant.bookmyseat.entity;

import com.dikshant.bookmyseat.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "bookings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne  // Many bookings can be made by one user
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne// Many bookings can belong to one show
    @JoinColumn(name = "show_id", nullable = false)
    private Show show;

    @ManyToMany
    @JoinTable(
            name = "booking_seats",
            joinColumns = @JoinColumn(name = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "seat_id")
    )
    // A booking can have multiple
    // seats, and a seat can appear
    // in multiple bookings
    // (different shows)
    private List<Seat> seats;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;

    @Column(name = "total_price")
    private Double totalPrice;

    @Column(name = "booked_at")
    private LocalDateTime bookedAt;

    @PrePersist
    private void onCreate() {
        this.bookedAt = LocalDateTime.now();
        if (this.status == null) {
            this.status = BookingStatus.CONFIRMED;
        }
    }
}
/*
*
* User --------< Booking >-------- Show
                |
                |
                v
             Seats
        (via booking_seats)

+-------------+       +----------------+       +-------------+
|    User     |       |    Booking     |       |    Show     |
+-------------+       +----------------+       +-------------+
| id (PK)     |       | id (PK)        |       | id (PK)     |
| name        |       | user_id (FK)   |       | movie_id FK |
+-------------+       | show_id (FK)   |       +-------------+
                      | status         |
                      | total_price    |
                      | booked_at      |
                      +----------------+
                               |
                               |
                       booking_seats
                               |
                               v
                         +-----------+
                         |   Seat    |
                         +-----------+
                         | id (PK)   |
                         | screen_id |
                         +-----------+*/