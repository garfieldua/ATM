package com.vabank.atm;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

//Main window
@SuppressWarnings("serial")
public class ATMView extends JFrame {

	private JPanel contentPane;
	private JTextField cardNumberField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ATMView frame = new ATMView();
					frame.setTitle("ATM");
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ATMView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnClean = new JButton("Clean");
		btnClean.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnClean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnClean.setBounds(640, 456, 144, 42);
		contentPane.add(btnClean);
		
		JLabel lblWelcomeLabel = new JLabel("Please input your card number");
		lblWelcomeLabel.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblWelcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeLabel.setBounds(0, 200, 784, 42);
		contentPane.add(lblWelcomeLabel);
		
		cardNumberField = new JTextField();
		cardNumberField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		cardNumberField.setHorizontalAlignment(SwingConstants.CENTER);
		cardNumberField.setBounds(86, 253, 613, 42);
		contentPane.add(cardNumberField);
		cardNumberField.setColumns(10);
		
		JButton btnNext = new JButton("Next");
		btnNext.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNext.setBounds(640, 509, 144, 42);
		contentPane.add(btnNext);
	}
}
