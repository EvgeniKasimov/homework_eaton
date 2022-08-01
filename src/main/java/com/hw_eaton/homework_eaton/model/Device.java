package com.hw_eaton.homework_eaton.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "devices")

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Device {
    @Id
    @Column(name = "device_name")
    private String deviceName;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Measurement> measurements;
}
