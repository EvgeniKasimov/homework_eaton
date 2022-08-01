package com.hw_eaton.homework_eaton.service;

import com.hw_eaton.homework_eaton.controller.dto.DeviceDto;
import com.hw_eaton.homework_eaton.controller.dto.MeasurementDto;
import com.hw_eaton.homework_eaton.model.Device;
import com.hw_eaton.homework_eaton.model.Measurement;
import com.hw_eaton.homework_eaton.repository.DeviceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageServiceImpl implements MessageService {

    Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

    private final DeviceRepository repository;

    @Autowired
    public MessageServiceImpl(DeviceRepository repository) {
        this.repository = repository;
    }

    @Override
    public DeviceDto addMeasurement(String deviceName, MeasurementDto measurement) {
        Device device = repository.findById(deviceName).orElse(new Device(deviceName, new ArrayList<>()));
        device.getMeasurements().add(mapMeasurementDtoToMeasurement(measurement));
        return mapDeviceToDeviceDto(repository.save(device));
    }

    @Override
    public DeviceDto getByDeviceName(String deviceName) {
        return mapDeviceToDeviceDto(repository.findById(deviceName).orElse(new Device(deviceName, new ArrayList<>())));
    }

    @Override
    public Integer getMessageCount(String deviceName) {
        Integer count = getByDeviceName(deviceName).getMeasurements().size();
        logger.info("Measurements count of device: {} is: {}", deviceName, count);
        return count;
    }

    @Override
    public List<DeviceDto> getAll() {
        List<Device> devices = repository.findAll();
        return devices.stream()
                .map(this::mapDeviceToDeviceDto)
                .collect(Collectors.toList());
    }

    private DeviceDto mapDeviceToDeviceDto(Device device) {
        return DeviceDto.builder()
                .deviceName(device.getDeviceName())
                .measurements(mapMeasurementToMeasurementDto(device.getMeasurements()))
                .build();
    }

    private List<MeasurementDto> mapMeasurementToMeasurementDto(List<Measurement> measurements) {
        return measurements.stream()
                .map(m -> MeasurementDto.builder()
                        .type(m.getType())
                        .value(m.getValue())
                        .date(m.getDate())
                        .build())
                .collect(Collectors.toList());
    }

    private Measurement mapMeasurementDtoToMeasurement(MeasurementDto measurement) {
        return Measurement.builder()
                .type(measurement.getType())
                .value(measurement.getValue())
                .build();
    }


}
