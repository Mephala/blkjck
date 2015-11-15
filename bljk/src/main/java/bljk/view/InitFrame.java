package bljk.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import bljk.data.AppData;
import bljk.engine.GameEngine;

public class InitFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblNewLabel_2;
	private JTextField textField_2;
	private JButton btnNewButton_1;

	/**
	 * Create the frame.
	 */
	public InitFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Kurallar");
		setResizable(false);
		setBounds(100, 100, 450, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Oyuncu Kazanma Yuzdesi ( Küsüratsız )");
		lblNewLabel.setBounds(12, 82, 302, 15);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(316, 80, 114, 19);
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10)
					tryInit();
			}
		});
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Para");
		lblNewLabel_1.setBounds(12, 133, 302, 15);
		contentPane.add(lblNewLabel_1);

		textField_1 = new JTextField();
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10)
					tryInit();
			}
		});
		textField_1.setBounds(316, 131, 114, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JButton btnNewButton = new JButton("Ben Oynuycam");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tryInit();
			}
		});
		btnNewButton.setBounds(12, 185, 153, 25);
		contentPane.add(btnNewButton);

		lblNewLabel_2 = new JLabel("Oyun Limiti");
		lblNewLabel_2.setBounds(12, 38, 302, 15);
		contentPane.add(lblNewLabel_2);

		textField_2 = new JTextField();
		textField_2.setBounds(316, 36, 114, 19);
		textField_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == 10)
					tryInit();
			}
		});
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		btnNewButton_1 = new JButton("Simule Et");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String winp = textField.getText();
				String moneyLimit = textField_1.getText();
				if (winp == null || winp.length() == 0 || !GameEngine.isNumeric(winp)) {
					JOptionPane.showMessageDialog(null, "Kazanma yuzdesi bos ya da numerik degil", "Olmadi panpa", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (moneyLimit == null || moneyLimit.length() == 0 || !GameEngine.isNumeric(moneyLimit)) {
					JOptionPane.showMessageDialog(null, "Para bos ya da numerik degil", "Olmadi panpa", JOptionPane.ERROR_MESSAGE);
					return;
				}
				AppData.winp = Integer.parseInt(winp);
				AppData.totalMoney = Integer.parseInt(moneyLimit);
				AppData.simulate.set(true);
				AppData.init.set(true);
			}
		});
		btnNewButton_1.setBounds(237, 185, 193, 25);
		contentPane.add(btnNewButton_1);
	}

	private void tryInit() {
		String winp = textField.getText();
		String moneyLimit = textField_1.getText();
		String rounds = textField_2.getText();
		if (winp == null || winp.length() == 0 || !GameEngine.isNumeric(winp)) {
			JOptionPane.showMessageDialog(null, "Kazanma yuzdesi bos ya da numerik degil", "Olmadi panpa", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (moneyLimit == null || moneyLimit.length() == 0 || !GameEngine.isNumeric(moneyLimit)) {
			JOptionPane.showMessageDialog(null, "Para bos ya da numerik degil", "Olmadi panpa", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (rounds == null || rounds.length() == 0 || !GameEngine.isNumeric(rounds)) {
			JOptionPane.showMessageDialog(null, "Oyun limiti bos ya da numerik degil", "Olmadi panpa", JOptionPane.ERROR_MESSAGE);
			return;
		}
		AppData.winp = Integer.parseInt(winp);
		AppData.rounds = Integer.parseInt(rounds);
		AppData.totalMoney = Integer.parseInt(moneyLimit);
		AppData.init.set(true);
	}
}
