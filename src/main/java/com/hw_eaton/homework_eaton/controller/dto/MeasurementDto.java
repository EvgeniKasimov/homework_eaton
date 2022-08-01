package com.hw_eaton.homework_eaton.controller.dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MeasurementDto {
    private String type;
    private String value;
    private Date date;

    public MeasurementDto(String type, String value) {
        this.type = type;
        this.value = value;
    }
}
