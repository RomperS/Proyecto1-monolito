package com.oswaldo.proyecto1.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DateBetweenDTO {

    private LocalDate initDate;
    private LocalDate endDate;
}
