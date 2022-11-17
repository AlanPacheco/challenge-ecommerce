package com.challenge.ecommerce.services.validations;


import com.challenge.ecommerce.controllers.exceptions.FieldMessage;
import com.challenge.ecommerce.dto.CustomerDTO;
import com.challenge.ecommerce.entities.Customer;
import com.challenge.ecommerce.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class CustomerInsertValidator implements ConstraintValidator<CustomerInsertValid, CustomerDTO> {
	
	@Autowired
	private CustomerRepository repository;
	
	@Override
	public void initialize(CustomerInsertValid ann) {
	}

	@Override
	public boolean isValid(CustomerDTO dto, ConstraintValidatorContext context) {
		
		List<FieldMessage> list = new ArrayList<>();
		
		Customer customer = repository.findByEmail(dto.getEmail());
		if (customer != null) {
			list.add(new FieldMessage("email", "Email já existe"));
		}

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
