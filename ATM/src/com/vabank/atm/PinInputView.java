package com.vabank.atm;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import org.json.simple.JSONObject;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@SuppressWarnings("serial")
public class PinInputView extends JPanel {

	/**
	 * Create the panel.
	 */
	public PinInputView() {
		setLayout(null);
		
		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel contentPane = new CardInputView();
				ATMView.instance.setContentPane(contentPane);
				ATMView.instance.invalidate();
				ATMView.instance.repaint();
				ATMView.instance.setLocationRelativeTo(null);
				ATMView.instance.setVisible(true);
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button.setBounds(10, 509, 200, 42);
		add(button);

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
		
		JLabel lblEnterYourPin = new JLabel("Enter your PIN number");
		lblEnterYourPin.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterYourPin.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblEnterYourPin.setBounds(0, 163, 784, 42);
		add(lblEnterYourPin);
		
		final JTextField pinField;
		pinField = new JPasswordField();
		((JPasswordField) pinField).setEchoChar('*');
		pinField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		pinField.setHorizontalAlignment(SwingConstants.CENTER);
		pinField.setBounds(299, 253, 184, 42);
		add(pinField);
		pinField.setColumns(10);
		pinField.setDocument(new JTextFieldLimit(4));

		JButton button_1 = new JButton("Next");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//check if we can login with such card num and pin
				JSONObject jsonObj = null;
				jsonObj = UrlConnector.getData("cardnum_login.php?card_num=" + CardInputView.cardNumberField.getText() + "&pin=" + pinField.getText() );
				
				Boolean logged = (Boolean) jsonObj.get("cardnum_login");
				//System.out.println(id);
				
				if (logged.booleanValue() == true) {
					//ok, good login
					JPanel contentPane = new SelectTransactionView();
					ATMView.instance.setContentPane(contentPane);
					ATMView.instance.invalidate();
					ATMView.instance.repaint();
					ATMView.instance.setLocationRelativeTo(null);
					ATMView.instance.setVisible(true);
				}
				else {
					//TODO: incorrect pin!
				}
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button_1.setBounds(584, 509, 200, 42);
		add(button_1);
		
		Timer t = new Timer(1000, updateClockAction);
		t.start();
	}
}
