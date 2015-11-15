package bljk.view;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import bljk.data.AppData;
import bljk.engine.GameEngine;

public class PlayView extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private final JLabel remainingMoneyLabel;
	private final JTextArea textArea;
	private int round = AppData.rounds;
	private final JLabel roundLabel;

	/**
	 * Create the frame.
	 */
	public PlayView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 652, 496);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Kalan Para");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(119, 57, 113, 15);
		contentPane.add(lblNewLabel);

		remainingMoneyLabel = new JLabel(Integer.valueOf(AppData.totalMoney).toString());
		remainingMoneyLabel.setBounds(302, 57, 169, 15);
		contentPane.add(remainingMoneyLabel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(75, 103, 495, 217);
		contentPane.add(scrollPane);

		Font font = new Font("SansSerif", Font.BOLD, 18);
		textArea = new JTextArea();
		textArea.setFont(font);
		scrollPane.setViewportView(textArea);

		JLabel lblNewLabel_2 = new JLabel("Para yatir ( Sonra ENTER )");
		lblNewLabel_2.setBounds(75, 355, 209, 15);
		contentPane.add(lblNewLabel_2);

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10) {
					if (round == 0) {
						JOptionPane.showMessageDialog(null, "Oyun bitti.", "GAME OVER", JOptionPane.ERROR_MESSAGE);
						return;
					}
					String bet = textField.getText();
					if (bet == null || bet.length() == 0 || !GameEngine.isNumeric(bet)) {
						JOptionPane.showMessageDialog(null, "Bos ya da numerik olmayan bet", "Yanlis is tuttun", JOptionPane.ERROR_MESSAGE);
						return;
					}
					Integer betInt = Integer.parseInt(bet);
					Integer remainingMoney = Integer.parseInt(remainingMoneyLabel.getText());
					remainingMoney = remainingMoney - betInt;
					boolean playerWins = GameEngine.playNewRound();
					if (playerWins)
						remainingMoney += (2 * betInt);
					remainingMoneyLabel.setText(remainingMoney.toString());
					textField.setText("");
					String gameText = textArea.getText();
					String curText = "";
					if (playerWins)
						curText = "WIN  +++ " + betInt.toString() + String.format("%n");
					else
						curText = "LOSE ---" + betInt.toString() + String.format("%n");
					gameText += curText;
					textArea.setText(gameText);
					round--;
					roundLabel.setText(Integer.valueOf(round).toString());
				}
			}
		});
		textField.setBounds(337, 353, 169, 19);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Para Reset");
		btnNewButton.setBounds(12, 418, 117, 25);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Oyun Reset");
		btnNewButton_1.setBounds(258, 418, 117, 25);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Program Reset");
		btnNewButton_2.setBounds(467, 418, 165, 25);
		contentPane.add(btnNewButton_2);

		JLabel lblNewLabel_1 = new JLabel("Round");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(119, 30, 113, 15);
		contentPane.add(lblNewLabel_1);

		roundLabel = new JLabel(Integer.valueOf(AppData.rounds).toString());
		roundLabel.setBounds(302, 30, 70, 15);
		contentPane.add(roundLabel);
	}
}
