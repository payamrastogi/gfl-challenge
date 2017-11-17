package com.gfl.elastic.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Hits
{
	@JsonProperty("total")
	public int total;
	@JsonProperty("max_score")
	public double maxScore;
	@JsonProperty("hits")
	public List<HitsDetail> hits;
}
