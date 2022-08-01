package com.hw_eaton.homework_eaton.repository;

import com.hw_eaton.homework_eaton.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, String> {
}
