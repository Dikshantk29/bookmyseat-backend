package com.dikshant.bookmyseat.repository;

import com.dikshant.bookmyseat.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

// Repository for Booking entity
public interface BookingRepo extends JpaRepository<Booking, Long> {

   // Fetch all bookings for a specific user
   List<Booking> findByUserId(Long userId);

   // Fetch all bookings for a specific show
   List<Booking> findByShowId(Long showId);

   // Custom JPQL query to fetch booked seat IDs for a show
   @Query("""
        SELECT s.id
        FROM Booking b
        JOIN b.seats s
        WHERE b.show.id = :showId
        AND b.status = 'CONFIRMED'
    """)
   List<Long> findBookedSeatIdByShowId(@Param("showId") Long showId);
}