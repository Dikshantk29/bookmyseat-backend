package com.dikshant.bookmyseat.service;

import com.dikshant.bookmyseat.dto.ShowRequest;
import com.dikshant.bookmyseat.entity.Movie;
import com.dikshant.bookmyseat.entity.Screen;
import com.dikshant.bookmyseat.entity.Show;
import com.dikshant.bookmyseat.repository.ShowRepo;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
@RequiredArgsConstructor
@Builder
public class ShowService {

    private final ShowRepo showRepository;
    private final MovieService movieService;
    private final ScreenService screenService;

    //Add show
    public Show addShow(ShowRequest showRequest) {
        Movie movie = movieService.getMovieById(showRequest.getMovieId());
        Screen screen = screenService.getScreenById(showRequest.getScreenId());

        Show show = Show.builder()
                .movie(movie)
                .screen(screen)
                .showDate(showRequest.getShowDate())
                .startTime(showRequest.getStartTime())
                .endTime(showRequest.getEndTime())
                .ticketPrice(showRequest.getTicketPrice())
                .build();

        return showRepository.save(show);

    }


    public List<Show> getAllShows() {
        return showRepository.findAll();
    }

    public Show getShowById(Long id) {
        return showRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Show not found with id: " + id));
    }

    public List<Show> getShowsByMovieId(Long movieId) {
        return showRepository.findByMovieId(movieId);
    }

    public List<Show> getShowsByScreenId(Long screenId) {
        return showRepository.findByScreenId(screenId);
    }

    public List<Show> getShowByMovieAndDate(Movie movie, LocalDate date) {
        return showRepository.findByMovieIdAndShowDate(movie.getId(), date);
    }

    public List<Show> getShowByScreenAndDate(Screen screen, LocalDate date) {
        return showRepository.findByScreenIdAndShowDate(screen.getId(), date);
    }





}
