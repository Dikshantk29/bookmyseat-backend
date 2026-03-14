package com.dikshant.bookmyseat.repository;

import com.dikshant.bookmyseat.entity.Movie;
import com.dikshant.bookmyseat.entity.Screen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MovieRepo extends JpaRepository<Movie, Long> {


   List<Movie> findByGenre(String genre);
   List<Movie> findByLanguage(String language);
   List<Movie> findByTitleContaingIgnoreCase(String title);


}
