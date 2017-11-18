package com.gfl.elastic.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Shards
{
	@JsonProperty("total")
	private int total;
	@JsonProperty("successful")
	private int successful;
	@JsonProperty("failed")
	private int failed;
}
