package com.challenge.ecommerce.services;

import com.challenge.ecommerce.dto.AddressDTO;
import com.challenge.ecommerce.dto.CustomerDTO;
import com.challenge.ecommerce.entities.Address;
import com.challenge.ecommerce.entities.Customer;
import com.challenge.ecommerce.repositories.AddressRepository;
import com.challenge.ecommerce.repositories.CustomerRepository;
import com.challenge.ecommerce.services.exceptions.DatabaseException;
import com.challenge.ecommerce.services.exceptions.ExistingAddressException;
import com.challenge.ecommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Transactional(readOnly = true)
    public Page<CustomerDTO> findAll(Pageable pageable) {
        Page<Customer> customers = customerRepository.findAll(pageable);
        return customers.map(customer -> new CustomerDTO(customer));
    }

    @Transactional
    public CustomerDTO insert(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        copyDtoToEntity(customerDTO, customer);
        customer = customerRepository.save(customer);
        return new CustomerDTO(customer);
    }

    @Transactional
    public AddressDTO insertAddress(String email, AddressDTO dto) {
        Customer customer = customerRepository.findByEmail(email);

        if (customer == null)
            throw new ResourceNotFoundException("Customer not found: " + email);

        Address address = addressRepository.findByPublicPlaceAndNumberAndDistrictAndCityAndStateAndZipCodeAndCustomer(dto.getPublicPlace(),
                dto.getNumber(), dto.getDistrict(), dto.getCity(), dto.getState(), dto.getZipCode(), customer);

        if (address != null) {
            throw new ExistingAddressException("Existing address!");
        }

        address = new Address();
        address.setCustomer(customer);
        copyAddressDtoToAddress(dto, address);
        address = addressRepository.save(address);
        return new AddressDTO(address);
    }

    @Transactional(readOnly = true)
    public List<AddressDTO> findAllAddress(String email) {
        Customer customer = customerRepository.findByEmail(email);
        List<Address> addresses = addressRepository.findByCustomer(customer);
        return addresses.stream().map(address -> new AddressDTO(address))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public CustomerDTO findById(Long id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);
        Customer customer = optionalCustomer.orElseThrow(() -> new ResourceNotFoundException("Id not found: " + id));
        return new CustomerDTO(customer);
    }

    @Transactional
    public CustomerDTO update(Long id, CustomerDTO customerDTO) {
        Customer customer = customerRepository.getReferenceById(id);
        customer.setEmail(customerDTO.getEmail());
        customer = customerRepository.save(customer);
        return new CustomerDTO(customer);
    }

    public void delete(Long id) {
        try {
            customerRepository.deleteById(id);
        } catch (EmptyResultDataAccessException error) {
            throw new ResourceNotFoundException("Id not found: " + id);
        } catch (DataIntegrityViolationException error) {
            throw new DatabaseException("Database integrity violation");
        }
    }

    private void copyDtoToEntity(CustomerDTO customerDTO, Customer customer) {
        customer.setEmail(customerDTO.getEmail());
        if (!customerDTO.getAddresses().isEmpty()) {
            for (AddressDTO dto : customerDTO.getAddresses()) {
                Address address = new Address();
                copyAddressDtoToAddress(dto, address);
                customer.addAddress(address);
            }
        }
    }

    private void copyAddressDtoToAddress(AddressDTO dto, Address address) {
        address.setPublicPlace(dto.getPublicPlace());
        address.setNumber(dto.getNumber());
        address.setDistrict(dto.getDistrict());
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        address.setZipCode(dto.getZipCode());
    }
}
