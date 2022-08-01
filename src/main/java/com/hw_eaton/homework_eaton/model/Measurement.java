package com.hw_eaton.homework_eaton.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "measurements")

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    private String type;
    private String value;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

}
