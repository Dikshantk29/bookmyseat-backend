package com.dikshant.bookmyseat.repository;

import com.dikshant.bookmyseat.entity.Screen;
import com.dikshant.bookmyseat.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScreenRepo extends JpaRepository<Screen, Long> {

   List<Screen> findByTheaterId(Long theaterId);

}
