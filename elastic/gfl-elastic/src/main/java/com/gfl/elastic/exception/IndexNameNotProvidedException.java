package com.gfl.elastic.exception;

public class IndexNameNotProvidedException extends Exception
{
	private static final long serialVersionUID = 1L;

	public IndexNameNotProvidedException(String message)
	{
		super(message);
	}
}
