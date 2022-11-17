package com.challenge.ecommerce.controllers;

import com.challenge.ecommerce.dto.ZipCodeDTO;
import com.challenge.ecommerce.services.ZipCodeService;
import com.challenge.ecommerce.services.exceptions.ZipCodeException;
import com.challenge.ecommerce.utils.Factory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ZipCodeController.class)
public class ZipCodeControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ZipCodeService zipCodeService;

    private String existingZipCode;
    private ZipCodeDTO zipCodeDTO;
    private String invalidZipCode;

    @BeforeEach
    void setUp() {
        existingZipCode = "80730360";
        invalidZipCode = "9999999999";
        zipCodeDTO = Factory.zipCodeCreate();

    //findByZipCode
        Mockito.when(zipCodeService.findByZipCode(existingZipCode)).thenReturn(zipCodeDTO);
        Mockito.doThrow(ZipCodeException.class).when(zipCodeService).findByZipCode(invalidZipCode);
    }

    @Test
    public void findByZipCodeShouldReturnZipCodeDTOWhenZipCodeExists() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/cep/{id}", existingZipCode)
                .accept(MediaType.APPLICATION_JSON));
        resultActions.andExpect(status().isOk());
        resultActions.andExpect(jsonPath("$.logradouro").exists());
        resultActions.andExpect(jsonPath("$.uf").exists());
    }

    @Test
    public void findByZipCodeShouldThrowZipCodeExceptionWhenInvalidZipCode() throws Exception {
        ResultActions resultActions = mockMvc.perform(get("/cep/{id}", invalidZipCode)
                .accept(MediaType.APPLICATION_JSON));
        resultActions.andExpect(status().isBadRequest());
    }

}
