package bljk.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;

import bljk.strategy.BettingStrategy;
import bljk.strategy.OscarBettingStrategy;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
public class TestOscarSimulateStrategy {

	@Test
	public void test1() {
		BettingStrategy bs = OscarBettingStrategy.construct(50);
		bs.bet(false, 50);
		bs.bet(false, 50);
		bs.bet(false, 50);
		bs.bet(false, 50);
		bs.bet(false, 50);
		assertTrue(50 == bs.bet(false, 50));

	}

	@Test
	public void test2() {
		BettingStrategy bs = OscarBettingStrategy.construct(50);
		bs.bet(false, 50);
		bs.bet(false, 50);
		bs.bet(false, 50);
		bs.bet(false, 50);
		bs.bet(false, 50);
		assertTrue(100 == bs.bet(true, 50));
	}

	/**
	 * - 100 - 100 - 100 - 100 - 100 - 100 - 100 - 100 + 100 + 200 - 300 - 300
	 * -300 - 300 - 300 + 300 + 400 + 500 + 600 + 300 + 100
	 * 
	 */
	@Test
	public void test3() {
		BettingStrategy bs = OscarBettingStrategy.construct(100);
		bs.bet(false, 100);
		bs.bet(false, 100);
		bs.bet(false, 100);
		bs.bet(false, 100);
		bs.bet(false, 100);
		bs.bet(false, 100);
		bs.bet(false, 100);
		bs.bet(false, 100);
		assertTrue(200 == bs.bet(true, 100));
		assertTrue(300 == bs.bet(true, 200));
		assertTrue(300 == bs.bet(false, 300));
		assertTrue(300 == bs.bet(false, 300));
		assertTrue(300 == bs.bet(false, 300));
		assertTrue(300 == bs.bet(false, 300));
		assertTrue(300 == bs.bet(false, 300));
		assertTrue(400 == bs.bet(true, 300));
		assertTrue(500 == bs.bet(true, 400));
		assertTrue(600 == bs.bet(true, 500));
		assertTrue(300 == bs.bet(true, 600));
		assertTrue(100 == bs.bet(true, 300));
		bs.bet(true, 100);
		bs.bet(true, 100);
		bs.bet(true, 100);
		bs.bet(true, 100);
		bs.bet(true, 100);
		bs.bet(true, 100);
		bs.bet(true, 100);
		bs.bet(true, 100);
		bs.bet(true, 100);
		bs.bet(true, 100);
		assertTrue(bs.stopPlaying());
	}

}
