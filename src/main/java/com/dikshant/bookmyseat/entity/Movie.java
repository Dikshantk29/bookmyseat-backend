package com.dikshant.bookmyseat.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "movies")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    private String description;


    private String genre;
    private String language;
    private Integer durationInMinutes;
    private Double rating;
    private LocalDate releaseDate;
    private String posterUrl;


}
