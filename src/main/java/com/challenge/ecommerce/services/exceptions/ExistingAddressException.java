package com.challenge.ecommerce.services.exceptions;

public class ExistingAddressException extends RuntimeException {

	public ExistingAddressException(String msg) {
		super(msg);
	}
}
