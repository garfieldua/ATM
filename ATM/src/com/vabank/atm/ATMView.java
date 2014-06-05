package com.vabank.atm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.Font;

import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Calendar;

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
		
		cardNumberField = new JTextField();
		cardNumberField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		cardNumberField.setHorizontalAlignment(SwingConstants.CENTER);
		cardNumberField.setBounds(200, 253, 384, 42);
		contentPane.add(cardNumberField);
		cardNumberField.setColumns(10);
		cardNumberField.setDocument(new JTextFieldLimit(16));
		
		JButton btnClean = new JButton("Clean");
		btnClean.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnClean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardNumberField.setText("");
			}
		});
		btnClean.setBounds(640, 456, 144, 42);
		contentPane.add(btnClean);
		
		JLabel lblWelcomeLabel = new JLabel("Please input your card number");
		lblWelcomeLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblWelcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeLabel.setBounds(0, 163, 784, 42);
		contentPane.add(lblWelcomeLabel);
		
		
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

		Timer t = new Timer(1000, updateClockAction);
		t.start();
	}
}
