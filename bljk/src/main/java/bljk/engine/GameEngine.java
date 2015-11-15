package bljk.engine;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import bljk.data.AppData;
import bljk.strategy.BettingStrategy;

public class GameEngine {

	public static Random r = new Random();

	public static boolean playNewRound() {
		int rint = r.nextInt(101) + 1;
		boolean win = rint <= AppData.winp;
		return win;
	}

	public static boolean isNumeric(String year) {
		if (year == null)
			return false;
		boolean numeric = false;
		try {
			Long.parseLong(year);
			numeric = true;
		} catch (Exception e) {
			;
		}
		return numeric;
	}

	public static SimulateResults simulate(BettingStrategy bs, int simulateLimit) {
		StringBuilder sb = new StringBuilder();
		int gameWinCount = 0;
		List<BettingStrategy> bsList = new ArrayList<>();
		for (int i = 0; i < simulateLimit; i++) {
			bs = bs.reConstruct();
			bsList.add(bs);
			int money = AppData.totalMoney;
			boolean win = false;
			int bet = 0;
			boolean start = true;

			while (!bs.stopPlaying() && money > 0) {
				if (start) {
					bet = bs.bet();
					start = false;
				} else
					bet = bs.bet(win, bet);
				win = playNewRound();
				if (win)
					money += bet;
				else
					money -= bet;
			}
			if (bs.stopPlaying()) {
				sb.append(bs.getName() + " WINS. Money:" + money + ". Earned:" + (money - AppData.totalMoney) + ".   gameId:" + bs.getId() + String.format("%n"));
				bs.setEarned(money - AppData.totalMoney);
				gameWinCount++;
			} else {
				bs.setLost(AppData.totalMoney - money);
				sb.append(bs.getName() + " LOSES. Money:" + money + ". Lost:" + (AppData.totalMoney - money) + ".    gameId:" + bs.getId() + String.format("%n"));
			}
		}
		BigDecimal totalGameCount = new BigDecimal(simulateLimit);
		BigDecimal totalWinCount = new BigDecimal(gameWinCount);
		BigDecimal winRate = new BigDecimal("100").multiply(totalWinCount).divide(totalGameCount, 3, BigDecimal.ROUND_HALF_UP);
		Double winRateDouble = winRate.doubleValue();
		String detail = sb.toString();
		return new SimulateResults(winRateDouble, detail, bsList);
	}

}
