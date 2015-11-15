package bljk.strategy;

import java.math.BigDecimal;

public interface BettingStrategy {

	public BettingStrategy reConstruct();

	public int bet(boolean prevRound, int amount);

	public int bet();

	public String getName();

	public boolean stopPlaying();

	public int getTotalLoss();

	public int getTotalWin();

	public String getHistory();

	public String getId();

	public void setProfit(int profit);

	public int getProfit();

	public void setEarned(int earned);

	public int getEarned();

	public void setLost(int lost);

	public int getLost();

	public BigDecimal calculateWinRate();

}
