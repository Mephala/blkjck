package bljk.strategy;

import java.math.BigDecimal;
import java.util.UUID;

public class OscarBettingStrategy implements BettingStrategy {

	private final int x;
	private int status = 0;
	private int totalEarn = 0;
	private int totalLoss = 0;
	private StringBuilder hb = new StringBuilder();
	private String id = UUID.randomUUID().toString();
	private int profit = 0;
	private int earned = 0;
	private int lost = 0;

	private OscarBettingStrategy(int x) {
		this.x = x;
	}

	@Override
	public int bet(boolean prevRound, int amount) {
		if (prevRound) {
			int tmpEarn = totalEarn + amount;
			String xStatus = Integer.valueOf((tmpEarn - totalLoss) / x).toString();
			totalEarn += amount;
			hb.append("+" + amount + "\t ( " + xStatus + "x ) " + String.format("%n"));
		} else {
			int tmpLoss = totalLoss + amount;
			String xStatus = Integer.valueOf((totalEarn - tmpLoss) / x).toString();
			totalLoss += amount;
			hb.append("-" + amount + "\t ( " + xStatus + "x ) " + String.format("%n"));
		}

		if (!prevRound) {
			status -= amount;
			return amount;
		} else {
			status += amount;
			if (status < 0) {
				int betAmount = Math.abs(amount) + x;
				if ((status + betAmount) > 0)
					return Math.abs(status) + x;
				else
					return betAmount;
			} else {
				status = 0;
				return x;
			}
		}

	}

	public static BettingStrategy construct(int x) {
		return new OscarBettingStrategy(x);
	}

	@Override
	public int bet() {
		return x;
	}

	@Override
	public String getName() {
		return "OSCAR";
	}

	@Override
	public BettingStrategy reConstruct() {
		return new OscarBettingStrategy(x);
	}

	@Override
	public boolean stopPlaying() {
		return ((totalEarn - totalLoss) > (10 * x));
	}

	@Override
	public int getTotalLoss() {
		return totalLoss;
	}

	@Override
	public int getTotalWin() {
		return totalEarn;
	}

	@Override
	public String getHistory() {
		return hb.toString();
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public void setProfit(int profit) {
		this.profit = profit;
	}

	@Override
	public int getProfit() {
		return this.profit;
	}

	@Override
	public void setEarned(int earned) {
		this.earned = earned;

	}

	@Override
	public int getEarned() {
		return this.earned;
	}

	@Override
	public void setLost(int lost) {
		this.lost = lost;

	}

	@Override
	public int getLost() {
		return this.lost;
	}

	@Override
	public BigDecimal calculateWinRate() {
		String history = getHistory();
		int i = history.indexOf("+");
		int plusCount = 0;
		while (i != -1) {
			plusCount++;
			history = history.substring(i + 1);
			i = history.indexOf("+");
		}
		history = getHistory();
		i = history.indexOf("-");
		int minusCount = 0;
		while (i != -1) {
			minusCount++;
			history = history.substring(i + 1);
			i = history.indexOf("-");
		}
		int total = minusCount + plusCount;
		return new BigDecimal("100").multiply(new BigDecimal(plusCount)).divide(new BigDecimal(total), 4, BigDecimal.ROUND_HALF_UP);
	}

}
