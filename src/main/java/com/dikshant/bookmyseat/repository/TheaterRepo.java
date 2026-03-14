package com.dikshant.bookmyseat.repository;

import com.dikshant.bookmyseat.entity.Theater;
import com.dikshant.bookmyseat.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TheaterRepo extends JpaRepository<Theater, Long> {

   List<Theater> findByCityId(Long cityId);

}
