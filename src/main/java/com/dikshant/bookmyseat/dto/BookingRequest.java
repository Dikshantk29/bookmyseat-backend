package com.dikshant.bookmyseat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequest {

    private Long userId;
    private Long showId;
    @JsonProperty("seatIds")
    private List<Long> seatIds;
    /*“It ensures the JSON field name
    seatIds maps correctly to the Java field.”*/
}
