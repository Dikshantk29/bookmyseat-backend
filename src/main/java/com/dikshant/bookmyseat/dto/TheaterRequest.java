package com.dikshant.bookmyseat.dto;

import com.dikshant.bookmyseat.enums.SeatType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TheaterRequest {

    private String name;
    private Long cityId;
    private String address;
}
