package com.vabank.atm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class InputPinView extends JFrame {

	// put back to private!
	public static JPanel contentPane;
	private JTextField pinField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InputPinView frame = new InputPinView();
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
	public InputPinView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		pinField = new JPasswordField();
		((JPasswordField) pinField).setEchoChar('*');
		pinField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		pinField.setHorizontalAlignment(SwingConstants.CENTER);
		pinField.setBounds(299, 253, 184, 42);
		contentPane.add(pinField);
		pinField.setColumns(10);
		pinField.setDocument(new JTextFieldLimit(4));
		
		JLabel lblWelcomePin = new JLabel("Enter your PIN number");
		lblWelcomePin.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblWelcomePin.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomePin.setBounds(0, 163, 784, 42);
		contentPane.add(lblWelcomePin);
		
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*
				 * TODO:
				 * Check if there is such card number - proceed to pin
				 */
			}
		});
		btnNext.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNext.setBounds(640, 509, 144, 42);
		contentPane.add(btnNext);
		
		JLabel lblLogo = new JLabel("VA Bank");
		lblLogo.setForeground(new Color(0, 0, 255));
		lblLogo.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblLogo.setBounds(10, 11, 134, 32);
		contentPane.add(lblLogo);
		
		final JLabel lblTime = new JLabel("");
		lblTime.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblTime.setBounds(640, 11, 134, 32);
		contentPane.add(lblTime);
		
		ActionListener updateClockAction = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String timeStamp = new SimpleDateFormat("HH:mm")
						.format(Calendar.getInstance().getTime());
				lblTime.setText(timeStamp);
			}
		};

		String timeStamp = new SimpleDateFormat("HH:mm")
				.format(Calendar.getInstance().getTime());
		lblTime.setText(timeStamp);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnBack.setBounds(0, 509, 144, 42);
		contentPane.add(btnBack);

		Timer t = new Timer(1000, updateClockAction);
		t.start();
	}
}
