package bljk.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import bljk.data.AppData;
import bljk.engine.GameEngine;

public class SimulateDetailView extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the frame.
	 */
	public SimulateDetailView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Simulasyon Detayi ( Sadece Oscar icin Simdilik )");
		setResizable(false);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Kac Defa Simule Edeyim");
		lblNewLabel.setBounds(30, 77, 212, 15);
		contentPane.add(lblNewLabel);

		textField = new JTextField();
		textField.setBounds(274, 75, 114, 19);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("X = ?");
		lblNewLabel_1.setBounds(30, 142, 212, 15);
		contentPane.add(lblNewLabel_1);

		textField_1 = new JTextField();
		textField_1.setBounds(274, 140, 114, 19);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		JButton btnNewButton = new JButton("GO!");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String simulateCount = textField.getText();
				String x = textField_1.getText();

				if (simulateCount == null || simulateCount.length() == 0 || !GameEngine.isNumeric(simulateCount)) {
					JOptionPane.showMessageDialog(null, "Simulasyon Detayi bos ya da numerik degil", "Olmadi panpa", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (x == null || x.length() == 0 || !GameEngine.isNumeric(x)) {
					JOptionPane.showMessageDialog(null, "X bos ya da numerik degil", "Olmadi panpa", JOptionPane.ERROR_MESSAGE);
					return;
				}
				AppData.x = Integer.parseInt(x);
				AppData.simulateCount = Integer.parseInt(simulateCount);
				AppData.simulateInit.set(true);
			}
		});
		btnNewButton.setBounds(159, 235, 117, 25);
		contentPane.add(btnNewButton);
	}
}
