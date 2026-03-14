package com.dikshant.bookmyseat.repository;

import com.dikshant.bookmyseat.entity.Seat;
import com.dikshant.bookmyseat.entity.Show;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface SeatRepo extends JpaRepository<Seat, Long> {

   List<Seat> findByScreenId(Long screenId);

}
