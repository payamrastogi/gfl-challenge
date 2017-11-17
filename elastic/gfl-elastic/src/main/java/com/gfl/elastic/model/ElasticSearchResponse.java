package com.gfl.elastic.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ElasticSearchResponse
{
	@JsonProperty("took")
	public int took;
	@JsonProperty("timed_out")
	public boolean timedOut;
	@JsonProperty("_shards")
	public Shards shards;
	@JsonProperty("hits")
	public Hits hits;
}
