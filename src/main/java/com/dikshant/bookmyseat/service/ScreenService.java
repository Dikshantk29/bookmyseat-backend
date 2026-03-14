package com.dikshant.bookmyseat.service;

import com.dikshant.bookmyseat.entity.Screen;
import com.dikshant.bookmyseat.entity.Theater;
import com.dikshant.bookmyseat.repository.ScreenRepo;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Builder
public class ScreenService {
    private final ScreenRepo screenRepository;
    private final TheaterService theaterService;

    //Add Screen
    public Screen addScreen(Screen screen) {
        return screenRepository.save(screen);
    }

    //Get all screens
    public List<Screen> getAllScreens() {
        return screenRepository.findAll();
    }


    public Screen getScreenById(Long id) {
        return screenRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Theater not found with id: " + id));
    }

    //Get screens by theater id
    public List<Screen> getScreensByTheater(Long theaterId) {
        return screenRepository.findByTheaterId(theaterId);
    }


}
