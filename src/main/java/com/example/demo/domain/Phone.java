package com.example.demo.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer number;

    private Byte cityCode;

    private Byte countryCode;

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;

    public Phone(Integer number, Byte cityCode, Byte countryCode, Person person) {
        this.number = number;
        this.countryCode = countryCode;
        this.cityCode = cityCode;
        this.person = person;
    }
}
