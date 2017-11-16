package com.gfl.elastic.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HitsDetail
{
	@JsonProperty("_index")
	public String index;
	@JsonProperty("_type")
	public String type;
	@JsonProperty("_id")
	public String id;
	@JsonProperty("_score")
	public double score;
	@JsonProperty("_source")
	public Source source;
}
