package com.dikshant.bookmyseat.repository;

import com.dikshant.bookmyseat.entity.Booking;
import com.dikshant.bookmyseat.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookingRepo extends JpaRepository<Booking, Long> {


   List<Booking> findByUserId(Long userId);
   List<Booking> findByShowId(Long showId);

   //find all the seats id that are already booked for given show and user
   List<Long> findBookedSeatIdByShowId(@Param("showId") Long showId);
   //@Query("SELECT b.seatId FROM Booking b WHERE b.showId = :showId AND b.status = 'CONFIRMED'")


}
