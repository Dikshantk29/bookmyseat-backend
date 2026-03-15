package com.dikshant.bookmyseat.Controller;


import com.dikshant.bookmyseat.dto.ShowRequest;
import com.dikshant.bookmyseat.entity.Show;
import com.dikshant.bookmyseat.entity.Theater;
import com.dikshant.bookmyseat.service.ShowService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/shows")
@RequiredArgsConstructor
public class ShowController {


    private final ShowService showService;

    @PostMapping
    public ResponseEntity<Show> createShow(@RequestBody ShowRequest showRequest) {
        return ResponseEntity.ok(showService.addShow(showRequest));
    }

    @GetMapping
    public ResponseEntity<List<Show>> getAllShows() {
        return ResponseEntity.ok(showService.getAllShows());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Show> getShowById(@PathVariable Long id) {
        return ResponseEntity.ok(showService.getShowById(id));
    }

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<Show>> getShowByMovie(@PathVariable Long movieId) {
        return ResponseEntity.ok(showService.getShowsByMovieId(movieId));
    }

    @GetMapping("/screen/{screenId}")
    public ResponseEntity<List<Show>> getShowByScreen(@PathVariable Long screenId) {
        return ResponseEntity.ok(showService.getShowsByScreenId(screenId));
    }

    @GetMapping("/movie/{movieId}/date/{date}")
    public ResponseEntity<List<Show>> getShowByMovieAndDate(
            @PathVariable Long movieId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        return ResponseEntity.ok(showService.getShowByMovieAndDate(movieId, date));
    }

    @GetMapping("/screen/{screenId}/date/{date}")
    public ResponseEntity<List<Show>> getShowByScreenAndDate(
            @PathVariable Long screenId,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        return ResponseEntity.ok(showService.getShowByScreenAndDate(screenId, date));
    }


}
