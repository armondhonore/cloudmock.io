package com.cloudmock.constants;

public enum Client
{
	CLI("cli"), REST("rest");

	private String value;

	private Client(String value)
	{
		this.value = value;
	}

	public String getValue()
	{
		return value;
	}

}
