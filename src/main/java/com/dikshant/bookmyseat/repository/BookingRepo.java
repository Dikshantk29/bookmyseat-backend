package com.dikshant.bookmyseat.repository;

import com.dikshant.bookmyseat.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookingRepo extends JpaRepository<Booking, Long> {

   List<Booking> findByUserId(Long userId);

   List<Booking> findByShowId(Long showId);

   // Get all booked seat IDs for a show
   @Query("""
        SELECT s.id
        FROM Booking b
        JOIN b.seats s
        WHERE b.show.id = :showId
        AND b.status = 'CONFIRMED'
    """)
   List<Long> findBookedSeatIdByShowId(@Param("showId") Long showId);
}