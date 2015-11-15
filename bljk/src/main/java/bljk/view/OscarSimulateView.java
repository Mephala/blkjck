package bljk.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import bljk.data.AppData;
import bljk.engine.GameEngine;
import bljk.engine.SimulateResults;
import bljk.strategy.BettingStrategy;
import bljk.strategy.OscarBettingStrategy;

public class OscarSimulateView extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private final List<BettingStrategy> memBsList;

	/**
	 * Create the frame.
	 */
	public OscarSimulateView() {
		setResizable(true);
		BettingStrategy bs = OscarBettingStrategy.construct(AppData.x);
		int simlimit = AppData.simulateCount;
		SimulateResults sr = GameEngine.simulate(bs, simlimit);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1070, 543);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("OSCAR RESULTS");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(169, 12, 268, 15);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Win Rate");
		lblNewLabel_1.setBounds(114, 39, 113, 15);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel(sr.getWinRate().toString());
		lblNewLabel_2.setBounds(361, 39, 135, 15);
		contentPane.add(lblNewLabel_2);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 185, 1038, 318);
		contentPane.add(scrollPane);

		Font font = new Font("SansSerif", Font.BOLD, 18);
		final JTextArea textArea = new JTextArea();
		textArea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				String selectedId = textArea.getSelectedText();
				showGameDetails(selectedId);
			}
		});

		textArea.setFont(font);
		textArea.setText(sr.getDetails());
		scrollPane.setViewportView(textArea);

		List<BettingStrategy> bsList = sr.getBsList();
		this.memBsList = bsList;
		int wins = 0;
		int losses = 0;
		int profit = 0;
		for (BettingStrategy bettingStrategy : bsList) {
			wins += bettingStrategy.getEarned();
			losses += bettingStrategy.getLost();
			profit += bettingStrategy.getEarned() - bettingStrategy.getLost();
		}

		JLabel lblNewLabel_3 = new JLabel("Earned:");
		lblNewLabel_3.setBounds(114, 73, 95, 15);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("Lost");
		lblNewLabel_4.setBounds(114, 118, 135, 15);
		contentPane.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel(Integer.valueOf(wins).toString());
		lblNewLabel_5.setBounds(361, 73, 113, 15);
		contentPane.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel(Integer.valueOf(losses).toString());
		lblNewLabel_6.setBounds(361, 118, 113, 15);
		contentPane.add(lblNewLabel_6);

		JLabel lblNewLabel_7 = new JLabel("Profit");
		lblNewLabel_7.setBounds(114, 159, 113, 15);
		contentPane.add(lblNewLabel_7);

		JLabel lblNewLabel_8 = new JLabel(Integer.valueOf(profit).toString());
		lblNewLabel_8.setBounds(361, 158, 113, 15);
		contentPane.add(lblNewLabel_8);

		JLabel lblNewLabel_9 = new JLabel("Oyun Detayi:");
		lblNewLabel_9.setBounds(534, 39, 113, 15);
		contentPane.add(lblNewLabel_9);

		textField = new JTextField();
		textField.setBounds(665, 37, 286, 19);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Detay Goster");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = textField.getText();
				showGameDetails(id);

			}
		});
		btnNewButton.setBounds(665, 83, 175, 25);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Yeniden Basla");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				AppData.simulateRes.set(true);
			}
		});
		btnNewButton_1.setBounds(665, 141, 175, 25);
		contentPane.add(btnNewButton_1);

		JLabel lblNewLabel_10 = new JLabel("Oyunun id'sini mouse ile secerek de gorebilirsiniz. ( Text Highlight )");
		lblNewLabel_10.setBounds(534, 118, 516, 15);
		contentPane.add(lblNewLabel_10);
	}

	private void showGameDetails(String id) {
		if (id == null || id.length() == 0)
			return;
		BettingStrategy foundOne = null;
		for (BettingStrategy bettingStrategy : memBsList) {
			if (bettingStrategy.getId().equals(id)) {
				foundOne = bettingStrategy;
			}
		}
		if (foundOne != null) {
			SimulateGameDetail sgd = new SimulateGameDetail(foundOne);
			sgd.setVisible(true);
		}
	}
}
