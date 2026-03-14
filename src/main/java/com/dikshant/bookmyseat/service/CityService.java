package com.dikshant.bookmyseat.service;

import com.dikshant.bookmyseat.entity.City;
import com.dikshant.bookmyseat.repository.CityRepo;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Builder
public class CityService {
    private final CityRepo cityRepository;

    public City addCity(City city) {

        return cityRepository.save(city);
    }

    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    public City getCityById(Long id) {
        return cityRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("City not found with id: " + id));
    }
}
