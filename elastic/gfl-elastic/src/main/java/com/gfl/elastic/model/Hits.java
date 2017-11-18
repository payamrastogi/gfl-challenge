package com.gfl.elastic.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Hits
{
	@JsonProperty("total")
	private int total;
	@JsonProperty("max_score")
	private double maxScore;
	@JsonProperty("hits")
	private List<HitsDetail> hits;
	
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public double getMaxScore() {
		return maxScore;
	}
	public void setMaxScore(double maxScore) {
		this.maxScore = maxScore;
	}
	public List<HitsDetail> getHits() {
		return hits;
	}
	public void setHits(List<HitsDetail> hits) {
		this.hits = hits;
	}
}
