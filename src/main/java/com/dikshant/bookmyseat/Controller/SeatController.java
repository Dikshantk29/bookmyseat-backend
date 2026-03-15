package com.dikshant.bookmyseat.Controller;

import com.dikshant.bookmyseat.dto.SeatRequest;
import com.dikshant.bookmyseat.entity.Seat;
import com.dikshant.bookmyseat.service.SeatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
@RequiredArgsConstructor
public class SeatController {

    private final SeatService seatService;

    // Add seat to a screen
    @PostMapping("/screen/{screenId}")
    public ResponseEntity<Seat> addSeat(@PathVariable Long screenId,
                                        @RequestBody SeatRequest seatRequest) {
        return ResponseEntity.ok(seatService.addSeat(screenId, seatRequest));
    }

    // Get all seats
    @GetMapping
    public ResponseEntity<List<Seat>> getAllSeats() {
        return ResponseEntity.ok(seatService.getAllSeats());
    }

    // Get seat by id
    @GetMapping("/{id}")
    public ResponseEntity<Seat> getSeatById(@PathVariable Long id) {
        return ResponseEntity.ok(seatService.getSeatById(id));
    }

    // Get seats by screen id
    @GetMapping("/screen/{screenId}")
    public ResponseEntity<List<Seat>> getSeatsByScreen(@PathVariable Long screenId) {
        return ResponseEntity.ok(seatService.getSeatsByScreenId(screenId));
    }

    // Delete seat
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSeat(@PathVariable Long id) {
        seatService.deleteSeat(id);
        return ResponseEntity.ok("Seat deleted successfully");
    }
}