package com.dikshant.bookmyseat.service;

import com.dikshant.bookmyseat.dto.SeatRequest;
import com.dikshant.bookmyseat.entity.Seat;
import com.dikshant.bookmyseat.entity.Screen;
import com.dikshant.bookmyseat.repository.SeatRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SeatService {

    private final SeatRepo seatRepository;
    private final ScreenService screenService;

    // Add Seat
    public Seat addSeat(Long screenId, SeatRequest seatRequest) {

        Screen screen = screenService.getScreenById(screenId);

        Seat seat = Seat.builder()
                .seatNumber(seatRequest.getSeatNumber())
                .row(seatRequest.getRow())
                .col(seatRequest.getCol())
                .seatType(seatRequest.getSeatType())
                .screen(screen)
                .build();

        return seatRepository.save(seat);
    }

    // Get All Seats
    public List<Seat> getAllSeats() {
        return seatRepository.findAll();
    }

    // Get seats by screen id
    public List<Seat> getSeatsByScreenId(Long screenId) {
        return seatRepository.findByScreenId(screenId);
    }

    // Get Seat By Id
    public Seat getSeatById(Long id) {
        return seatRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Seat not found with id: " + id));
    }

    // Delete Seat
    public void deleteSeat(Long id) {
        seatRepository.deleteById(id);
    }
}