package com.challenge.ecommerce.repositories;

import com.challenge.ecommerce.entities.Address;
import com.challenge.ecommerce.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findByCustomer(Customer customer);

    //Consulta para validar se o endereço já existe
    Address findByPublicPlaceAndNumberAndDistrictAndCityAndStateAndZipCodeAndCustomer(String publicPlace,
                                                                                      String number,
                                                                                      String district,
                                                                                      String city,
                                                                                      String state,
                                                                                      String zipCode,
                                                                                      Customer customer);

}
