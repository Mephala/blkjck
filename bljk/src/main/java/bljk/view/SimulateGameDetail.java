package bljk.view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import bljk.strategy.BettingStrategy;

public class SimulateGameDetail extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public SimulateGameDetail(BettingStrategy bs) {
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 968, 532);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 51, 918, 430);
		contentPane.add(scrollPane);

		JTextArea textArea = new JTextArea();
		textArea.setText(bs.getHistory());
		scrollPane.setViewportView(textArea);

		JLabel lblNewLabel = new JLabel("Game Win Rate:");
		lblNewLabel.setBounds(200, 12, 147, 15);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel(bs.calculateWinRate().toPlainString());
		lblNewLabel_1.setBounds(470, 12, 117, 15);
		contentPane.add(lblNewLabel_1);
	}

}
