package com.hw_eaton.homework_eaton.service;

import com.hw_eaton.homework_eaton.controller.dto.DeviceDto;
import com.hw_eaton.homework_eaton.controller.dto.MeasurementDto;

import java.util.List;

public interface MessageService {

    DeviceDto addMeasurement(String deviceName, MeasurementDto measurement);

    DeviceDto getByDeviceName(String deviceName);

    Integer getMessageCount(String deviceName);

    List<DeviceDto> getAll();
}
