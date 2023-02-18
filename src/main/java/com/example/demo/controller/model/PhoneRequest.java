package com.example.demo.controller.model;

import com.example.demo.domain.Person;
import com.example.demo.domain.Phone;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PhoneRequest {

    Integer number;

    @JsonProperty("citycode")
    Byte cityCode;
    @JsonProperty("contrycode")
    Byte countryCode;

    public Phone toDomain(Person person) {
        return new Phone(number, cityCode, countryCode, person);
    }
}
