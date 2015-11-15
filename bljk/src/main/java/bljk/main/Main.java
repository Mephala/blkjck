package bljk.main;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import bljk.data.AppData;
import bljk.view.InitFrame;
import bljk.view.OscarSimulateView;
import bljk.view.PlayView;
import bljk.view.SimulateDetailView;

public class Main {

	private static InitFrame initFrame;
	private static PlayView playFrame;
	private static SimulateDetailView simulateDetailFrame;
	private static OscarSimulateView oscarSimulateView;

	public static void main(String[] args) {

		try {
			runMain();
		} catch (Throwable t) {
			t.printStackTrace();
		}

	}

	private static void runMain() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

		while (true) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						initFrame = new InitFrame();
						centralizeJFrame(initFrame);
						initFrame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});

			while (!AppData.init.get())
				;
			initFrame.setVisible(false);
			initFrame.dispose();

			if (AppData.simulate.get()) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							simulateDetailFrame = new SimulateDetailView();
							centralizeJFrame(simulateDetailFrame);
							simulateDetailFrame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

				while (!AppData.simulateInit.get())
					;

				simulateDetailFrame.setVisible(false);
				simulateDetailFrame.dispose();

				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							oscarSimulateView = new OscarSimulateView();
							centralizeJFrame(oscarSimulateView);
							oscarSimulateView.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

				while (!AppData.simulateRes.get())
					;

				oscarSimulateView.setVisible(false);
				oscarSimulateView.dispose();

				AppData.simulateRes.set(false);
				AppData.init.set(false);
				AppData.simulateInit.set(false);

			} else {
				handleNonSimulate();
				break;
			}
		}

	}

	private static void handleNonSimulate() {
		final int winp = AppData.winp;
		Random r = new Random();
		for (int i = 0; i < AppData.rounds; i++) {
			int rint = r.nextInt(101) + 1;
			boolean win = rint <= winp;
			System.out.println(win);
		}

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					playFrame = new PlayView();
					centralizeJFrame(playFrame);
					playFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void centralizeJFrame(JFrame frame) {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation(dim.width / 2 - frame.getSize().width / 2, dim.height / 2 - frame.getSize().height / 2);
	}

}
