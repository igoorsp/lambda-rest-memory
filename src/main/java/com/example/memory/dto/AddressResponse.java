package com.example.memory.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RegisterForReflection
public class AddressResponse {
    private String status;
    private int code;
    private String locale;
    private Integer total;
    private List<Address> data;

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @RegisterForReflection
    public static class Address {
        private int id;
        private String street;
        private String streetName;
        private String buildingNumber;
        private String city;
        private String zipcode;
        private String country;
        private String country_code;
        private double latitude;
        private double longitude;
    }
}
