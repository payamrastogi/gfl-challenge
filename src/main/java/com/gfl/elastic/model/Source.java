package com.gfl.elastic.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Source
{
	@JsonProperty("agency")
	public String agency;
	@JsonProperty("id")
	public long id;
	@JsonProperty("stopCode")
	public long stopCode;
}
