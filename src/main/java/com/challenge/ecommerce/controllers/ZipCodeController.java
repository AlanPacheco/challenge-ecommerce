package com.challenge.ecommerce.controllers;

import com.challenge.ecommerce.dto.ZipCodeDTO;
import com.challenge.ecommerce.services.ZipCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/cep")
public class ZipCodeController {

    @Autowired
    private ZipCodeService zipCodeService;

    @GetMapping(value = "/{zipCode}")
    public ResponseEntity<ZipCodeDTO> findByZipCode(@PathVariable String zipCode){
        ZipCodeDTO zipCodeDTO = zipCodeService.findByZipCode(zipCode);
        return ResponseEntity.ok(zipCodeDTO);
    }
}
