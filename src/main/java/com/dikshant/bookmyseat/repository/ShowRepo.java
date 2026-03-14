package com.dikshant.bookmyseat.repository;

import com.dikshant.bookmyseat.entity.Show;
import com.dikshant.bookmyseat.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ShowRepo extends JpaRepository<Show, Long> {

   List<Show> findByMovieId(Long movieId);
   List<Show> findByScreenId(Long screenId);
   List<Show> findByMovieIdAndShowDate(Long movieId, LocalDate showDate);
   List<Show> findByScreenIdAndShowDate(Long screenId, LocalDate showDate);

}
