package com.challenge.ecommerce.utils;

import com.challenge.ecommerce.dto.ZipCodeDTO;

public class Factory {

    public static ZipCodeDTO zipCodeCreate() {
        ZipCodeDTO zipCode = new ZipCodeDTO();
        zipCode.setCep("80730-360");
        zipCode.setLogradouro("Rua Euclides da Cunha");
        zipCode.setComplemento("até 1579/1580");
        zipCode.setBairro("Bigorrilho");
        zipCode.setLocalidade("Curitiba");
        zipCode.setUf("PR");
        zipCode.setIbge("4106902");
        zipCode.setGia("");
        zipCode.setDdd("41");
        zipCode.setSiafi("7535");
        return zipCode;
    }



}
