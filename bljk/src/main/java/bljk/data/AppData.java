package bljk.data;

import java.util.concurrent.atomic.AtomicBoolean;

public class AppData {

	public static int winp;
	public static int totalMoney;
	public static int rounds;
	public static AtomicBoolean init = new AtomicBoolean(false);
	public static AtomicBoolean simulate = new AtomicBoolean(false);
	public static int simulateCount;
	public static int x;
	public static AtomicBoolean simulateInit = new AtomicBoolean(false);
	public static AtomicBoolean simulateRes = new AtomicBoolean(false);

}
