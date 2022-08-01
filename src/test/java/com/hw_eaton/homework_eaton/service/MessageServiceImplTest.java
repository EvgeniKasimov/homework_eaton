package com.hw_eaton.homework_eaton.service;

import com.hw_eaton.homework_eaton.controller.dto.DeviceDto;
import com.hw_eaton.homework_eaton.controller.dto.MeasurementDto;
import com.hw_eaton.homework_eaton.model.Device;
import com.hw_eaton.homework_eaton.model.Measurement;
import com.hw_eaton.homework_eaton.repository.DeviceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class MessageServiceImplTest {

    @Autowired
    MessageService messageService;

    @MockBean
    DeviceRepository repository;

    @Test
    void addMeasurementTest_NotExistedDeviceInDb() {
        Measurement measurement = Measurement.builder()
                .type("T1")
                .value("0001")
                .build();
        Device device = new Device("Test_device", List.of(measurement));
        Mockito.when(repository.save(Mockito.any())).thenReturn(device);
        DeviceDto result = messageService.addMeasurement("Test_Device", new MeasurementDto("T1", "0001"));
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1, result.getMeasurements().size());

    }

    @Test
    void addMeasurementTest_ExistedDeviceInDb() {
        Measurement measurement = Measurement.builder()
                .type("T1")
                .value("0001")
                .build();
        List<Measurement> measurementList = new ArrayList<>();
        measurementList.add(measurement);
        Device device = new Device("Test_device", measurementList);
        Mockito.when(repository.findById(Mockito.anyString())).thenReturn(Optional.of(device));
        Mockito.when(repository.save(Mockito.any())).thenAnswer(i -> i.getArguments()[0]);

        DeviceDto result = messageService.addMeasurement("Test_Device", new MeasurementDto("T1", "0002"));
        Assertions.assertNotNull(result);
        Assertions.assertEquals(2, result.getMeasurements().size());

    }

    @Test
    void getByDeviceNameTest_DeviceNotExist() {
        DeviceDto device = messageService.getByDeviceName("Test_Device");
        Assertions.assertEquals(0, device.getMeasurements().size());
    }

    @Test
    void getMessageCount() {
        Mockito.when(repository.findById(Mockito.anyString())).thenReturn(Optional.empty());
        Integer count = messageService.getMessageCount("Test_Device");
        Assertions.assertEquals(0, count);
    }

}