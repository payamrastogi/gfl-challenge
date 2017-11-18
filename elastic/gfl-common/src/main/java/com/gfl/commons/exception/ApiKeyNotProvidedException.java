package com.gfl.commons.exception;

public class ApiKeyNotProvidedException extends Exception{
	private static final long serialVersionUID = 1L;

	public ApiKeyNotProvidedException(String message)
	{
		super(message);
	}
}
