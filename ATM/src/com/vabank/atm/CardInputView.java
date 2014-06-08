package com.vabank.atm;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import org.json.simple.JSONObject;

@SuppressWarnings("serial")
public class CardInputView extends JPanel {

	/**
	 * Create the panel.
	 */
	
	public static JTextField cardNumberField;
	
	public CardInputView() {
		setLayout(null);
		
		cardNumberField = new JTextField();
		cardNumberField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		cardNumberField.setHorizontalAlignment(SwingConstants.CENTER);
		cardNumberField.setBounds(200, 253, 384, 42);
		add(cardNumberField);
		cardNumberField.setColumns(10);
		cardNumberField.setDocument(new JTextFieldLimit(16));
		
		JButton btnClean = new JButton("Clean");
		btnClean.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnClean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardNumberField.setText("");
				cardNumberField.requestFocus();
			}
		});
		btnClean.setBounds(584, 456, 200, 42);
		add(btnClean);
		
		JLabel lblWelcomeLabel = new JLabel("Please input your card number");
		lblWelcomeLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblWelcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeLabel.setBounds(0, 163, 784, 42);
		add(lblWelcomeLabel);
		
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*
				 * TODO:
				 * Check if there is such card number - proceed to pin
				 
				enterPin = new Pin();
				frame.setContentPane(enterPin);
				frame.invalidate();
				frame.repaint();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);*/
				
				
				//check if there's such a card
				JSONObject jsonObj = null;
				jsonObj = UrlConnector.getData("cardnum_existance.php?card_num=" + cardNumberField.getText() );

				Boolean cardnum_exists = (Boolean) jsonObj.get("cardnum_exists");
				//System.out.println(id);
				
				if (cardnum_exists.booleanValue() == true) {
					// if there is such a card
					JPanel contentPane = new PinInputView();
					ATMView.instance.setContentPane(contentPane);
					ATMView.instance.invalidate();
					ATMView.instance.repaint();
					ATMView.instance.setLocationRelativeTo(null);
					ATMView.instance.setVisible(true);	
				}
				else {
					//TODO: tell the user that he has intupped wrong card num
				}
				
			}
		});
		btnNext.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNext.setBounds(584, 509, 200, 42);
		add(btnNext);
		
		JLabel lblLogo = new JLabel("VA Bank");
		lblLogo.setForeground(new Color(0, 0, 255));
		lblLogo.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblLogo.setBounds(10, 11, 134, 32);
		add(lblLogo);
		
		final JLabel lblTime = new JLabel("");
		lblTime.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblTime.setBounds(640, 11, 134, 32);
		add(lblTime);
		
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
		
		cardNumberField.requestFocus();
		cardNumberField.grabFocus();
	}

}
