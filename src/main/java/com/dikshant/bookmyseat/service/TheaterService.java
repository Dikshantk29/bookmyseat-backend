package com.dikshant.bookmyseat.service;

import com.dikshant.bookmyseat.dto.TheaterRequest;
import com.dikshant.bookmyseat.entity.City;
import com.dikshant.bookmyseat.entity.Theater;
import com.dikshant.bookmyseat.repository.CityRepo;
import com.dikshant.bookmyseat.repository.TheaterRepo;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Builder
public class TheaterService {

    private final TheaterRepo theaterRepository;
    private final CityService cityService;


    //Add theater
    public Theater addTheater(TheaterRequest theaterRequest) {

        City city = cityService.getCityById(theaterRequest.getCityId());

        Theater theater = Theater.builder()
                .name(theaterRequest.getName())
                .city(city)
                .address(theaterRequest.getAddress())
                .build();

        return theaterRepository.save(theater);
    }


    //Get all theaters
    public List<Theater> getAllTheaters() {
        return theaterRepository.findAll();
    }

    //Get theater by id
    public Theater getTheaterById(Long id) {
        return theaterRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Theater not found with id: " + id));
    }

    //Get theaters by city id
    public List<Theater> getTheatersByCityId(Long cityId) {
        return theaterRepository.findByCityId(cityId);
    }
}
