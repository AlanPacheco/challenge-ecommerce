package com.challenge.ecommerce.dto;

import com.challenge.ecommerce.entities.Address;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

public class AddressDTO {

    private Long id;

    @NotBlank(message = "Campo obrigatório")
    private String publicPlace;

    @NotBlank(message = "Campo obrigatório")
    private String number;

    @NotBlank(message = "Campo obrigatório")
    private String district;

    @NotBlank(message = "Campo obrigatório")
    private String city;

    @NotBlank(message = "Campo obrigatório")
    private String state;

    @NotBlank(message = "Campo obrigatório")
    private String zipCode;

    private long customerId;

    public AddressDTO() {
    }

    public AddressDTO(Long id, String publicPlace, String number, String district, String city, String state, String zipCode, long customerId) {
        this.id = id;
        this.publicPlace = publicPlace;
        this.number = number;
        this.district = district;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.customerId = customerId;
    }

    public AddressDTO(Address address) {
        this(address.getId()
                , address.getPublicPlace()
                , address.getNumber()
                , address.getDistrict()
                , address.getCity()
                , address.getState()
                , address.getZipCode()
                , address.getCustomer().getId());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPublicPlace() {
        return publicPlace;
    }

    public void setPublicPlace(String publicPlace) {
        this.publicPlace = publicPlace;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }
}
