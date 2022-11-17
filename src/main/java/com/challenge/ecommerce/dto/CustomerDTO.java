package com.challenge.ecommerce.dto;

import com.challenge.ecommerce.entities.Customer;
import com.challenge.ecommerce.services.validations.CustomerInsertValid;

import java.util.ArrayList;
import java.util.List;

@CustomerInsertValid
public class CustomerDTO {

    private Long id;
    private String email;
    private List<AddressDTO> addresses = new ArrayList<>();

    public CustomerDTO(){
    }

    public CustomerDTO(Long id, String email) {
        this.id = id;
        this.email = email;
    }

    public CustomerDTO(Customer customer) {
        this(customer.getId(), customer.getEmail());
        if(!customer.getAddresses().isEmpty()){
            customer.getAddresses().forEach(address -> addresses.add(new AddressDTO(address)));
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<AddressDTO> getAddresses() {
        return addresses;
    }

}
