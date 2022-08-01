package com.hw_eaton.homework_eaton.controller;


import com.hw_eaton.homework_eaton.controller.dto.DeviceDto;
import com.hw_eaton.homework_eaton.controller.dto.MeasurementDto;
import com.hw_eaton.homework_eaton.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("message")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(final MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping(value = "{device-name}")
    public void addMessage(@PathVariable("device-name") String deviceName,
                           @RequestBody @NonNull MeasurementDto measurement) {
        messageService.addMeasurement(deviceName, measurement);
    }

    @GetMapping(value = "{device-name}")
    public DeviceDto getMessagesByDeviceName(@PathVariable("device-name") String deviceName) {
        return messageService.getByDeviceName(deviceName);
    }

    @GetMapping(value = "{device-name}/count")
    public int numberOfMessagesFromTheDevice(@PathVariable("device-name") String deviceName) {
        return messageService.getMessageCount(deviceName);
    }

    @GetMapping("all")
    public List<DeviceDto> getAllMessages() {
        return messageService.getAll();
    }

}
