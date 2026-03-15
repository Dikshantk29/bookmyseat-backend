package com.dikshant.bookmyseat.Controller;



import com.dikshant.bookmyseat.dto.TheaterRequest;
import com.dikshant.bookmyseat.entity.Theater;
import com.dikshant.bookmyseat.service.TheaterService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/theaters")
@RequiredArgsConstructor
public class TheaterController {
    private final TheaterService theaterService;

    @PostMapping
    public ResponseEntity<Theater> createTheater(@RequestBody TheaterRequest theaterRequest) {
        return ResponseEntity.ok(theaterService.addTheater(theaterRequest));
    }

    @GetMapping
    public ResponseEntity<List<Theater>> getAllTheaters() {
        return ResponseEntity.ok(theaterService.getAllTheaters());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Theater> getTheaterById(@PathVariable Long id) {
        return ResponseEntity.ok(theaterService.getTheaterById(id));
    }


    @GetMapping("/city/{cityId}")
    public ResponseEntity<List<Theater>> getTheatersByCity(@PathVariable Long cityId) {
        return ResponseEntity.ok(theaterService.getTheatersByCityId(cityId));
    }
}
