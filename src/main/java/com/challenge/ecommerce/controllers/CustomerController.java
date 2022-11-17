package com.challenge.ecommerce.controllers;

import com.challenge.ecommerce.dto.AddressDTO;
import com.challenge.ecommerce.dto.CustomerDTO;
import com.challenge.ecommerce.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/{email}/addresses")
    public ResponseEntity<List<AddressDTO>> findAllAddress(@PathVariable String email){
        List<AddressDTO> addressDTOS = customerService.findAllAddress(email);
        return ResponseEntity.ok(addressDTOS);
    }

    @PostMapping(value = "/{email}/addresses")
    public ResponseEntity<AddressDTO> insertAddress(@PathVariable String email, @Valid @RequestBody AddressDTO addressDTO){
        AddressDTO AddressDTO = customerService.insertAddress(email, addressDTO);
        return ResponseEntity.ok().body(AddressDTO);
    }

    @GetMapping
    public ResponseEntity<Page<CustomerDTO>> findAll(
            @PageableDefault(page = 0, size = 10, sort = "email", direction = Sort.Direction.ASC) Pageable pageable) {
        Page<CustomerDTO> customerDTOS = customerService.findAll(pageable);
        return ResponseEntity.ok().body(customerDTOS);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CustomerDTO> findById(@PathVariable Long id) {
        CustomerDTO dto = customerService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<CustomerDTO> insert(@Valid @RequestBody CustomerDTO customerDTO) {
        customerDTO = customerService.insert(customerDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(customerDTO.getId()).toUri();
        return ResponseEntity.created(uri).body(customerDTO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CustomerDTO> update(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
        customerDTO = customerService.update(id, customerDTO);
        return ResponseEntity.ok().body(customerDTO);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        customerService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
