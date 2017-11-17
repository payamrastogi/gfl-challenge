package com.gfl.elastic.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Shards
{
	@JsonProperty("total")
	public int total;
	@JsonProperty("successful")
	public int successful;
	@JsonProperty("failed")
	public int failed;
}
