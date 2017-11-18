package com.gfl.elastic.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ElasticSearchResponse
{
	@JsonProperty("took")
	private int took;
	@JsonProperty("timed_out")
	private boolean timedOut;
	@JsonProperty("_shards")
	private Shards shards;
	@JsonProperty("hits")
	private Hits hits;
	public int getTook() {
		return took;
	}
	public void setTook(int took) {
		this.took = took;
	}
	public boolean isTimedOut() {
		return timedOut;
	}
	public void setTimedOut(boolean timedOut) {
		this.timedOut = timedOut;
	}
	public Shards getShards() {
		return shards;
	}
	public void setShards(Shards shards) {
		this.shards = shards;
	}
	public Hits getHits() {
		return hits;
	}
	public void setHits(Hits hits) {
		this.hits = hits;
	}
}
