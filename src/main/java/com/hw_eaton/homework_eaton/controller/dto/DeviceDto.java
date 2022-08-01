package com.hw_eaton.homework_eaton.controller.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DeviceDto {
    private String deviceName;
    private List<MeasurementDto> measurements;

    public DeviceDto(String deviceName, MeasurementDto measurement) {
        this.deviceName = deviceName;
        this.measurements = new ArrayList<>();
        measurements.add(measurement);
    }
}
