package com.dikshant.bookmyseat.service;

import com.dikshant.bookmyseat.dto.BookingRequest;
import com.dikshant.bookmyseat.entity.*;
import com.dikshant.bookmyseat.enums.BookingStatus;
import com.dikshant.bookmyseat.repository.BookingRepo;
import com.dikshant.bookmyseat.repository.SeatRepo;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Builder
public class BookingService {
    private final BookingRepo bookingRepository;
    private final SeatRepo seatRepository;
    private final ShowService showService;
    private final UserService userService;

    @Transactional
    public Booking createBooking(BookingRequest bookingRequest) {

        User user = userService.getUserById(bookingRequest.getUserId());
        Show show = showService.getShowById(bookingRequest.getShowId());

        //check if any of the requested seats are already booked
        List<Long> alreadyBookedSeats = bookingRepository
                .findBookedSeatIdByShowId(show.getId());
        for(Long seatId : bookingRequest.getSeatIds()) {
            if(alreadyBookedSeats.contains(seatId)) {
                throw new RuntimeException("Seat " + seatId + " is already booked");
            }
        }

        List<Seat> seats = seatRepository.findAllById(bookingRequest.getSeatIds());

        if(seats.size() != bookingRequest.getSeatIds().size()) {
            throw new RuntimeException("Some seats are not Invalid");
        }

        Double totalPrice = seats.size()*show.getTicketPrice();
        Booking booking = Booking.builder()
                .user(user)
                .show(show)
                .seats(seats)
                .totalPrice(totalPrice)
                .status(BookingStatus.CONFIRMED)
                .build();

        return bookingRepository.save(booking);

    }

    public Booking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Theater not found with id: " + id));
    }

    public  List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public List<Booking> getBookingsByUser(Long userId) {
        return bookingRepository.findByUserId(userId);
    }

    public List<Booking> getBookingsByShow(Long showId) {
        return bookingRepository.findByShowId(showId);
    }

    @Transactional
    public void cancelBooking(Long id) {
        Booking booking = getBookingById(id);
        booking.setStatus(BookingStatus.CANCELLED);
        bookingRepository.save(booking);
    }

    public List<Seat> getAvailableSeats(Long showId) {
        Show show = showService.getShowById(showId);
        List<Seat> seats = seatRepository.findByScreenId(show.getScreen().getId());
        List<Long> bookedSeatIds = bookingRepository.findBookedSeatIdByShowId(showId);
        return seats.stream()
                .filter(seat -> !bookedSeatIds.contains(seat.getId()))
                .toList();

    }

}
