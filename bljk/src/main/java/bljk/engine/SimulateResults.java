package bljk.engine;

import java.util.List;

import bljk.strategy.BettingStrategy;

public class SimulateResults {

	private final Double winRate;
	private final String details;
	private final List<BettingStrategy> bsList;

	public SimulateResults(Double winRate, String details, List<BettingStrategy> bsList) {
		this.winRate = winRate;
		this.details = details;
		this.bsList = bsList;
	}

	public List<BettingStrategy> getBsList() {
		return bsList;
	}

	public Double getWinRate() {
		return this.winRate;
	}

	public String getDetails() {
		return this.details;
	}

}
