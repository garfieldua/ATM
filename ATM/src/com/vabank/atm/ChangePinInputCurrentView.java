package com.vabank.atm;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;

import org.json.simple.JSONObject;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ChangePinInputCurrentView extends JPanel {
	private JPasswordField passwordField;

	/**
	 * Create the panel.
	 */
	public ChangePinInputCurrentView() {
		setLayout(null);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
		
				JPanel contentPane = new AccountSettingsView();
				ATMView.instance.setContentPane(contentPane);
				ATMView.instance.invalidate();
				ATMView.instance.repaint();
				ATMView.instance.setLocationRelativeTo(ATMView.instance);
				ATMView.instance.setVisible(true);
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnBack.setBounds(10, 509, 200, 42);
		add(btnBack);
		
		JLabel label = new JLabel("Enter your PIN number");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 26));
		label.setBounds(0, 163, 784, 42);
		add(label);
		
		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		passwordField.setEchoChar('*');
		passwordField.setColumns(10);
		passwordField.setDocument(new JTextFieldLimit(4));
		passwordField.setBounds(299, 253, 184, 42);
		add(passwordField);
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//check if we can login with such card num and pin
				JSONObject jsonObj = null;
				jsonObj = UrlConnector.getData("cardnum_login.php?card_num=" + CardInputView.cardNumberField.getText() + "&pin=" + passwordField.getText() );
				
				Boolean logged = (Boolean) jsonObj.get("cardnum_login");
				//System.out.println(id);
				
				if (logged.booleanValue() == true) {
					//ok, good login
					JPanel contentPane = new ChangePinInputNewView();
					ATMView.instance.setContentPane(contentPane);
					ATMView.instance.invalidate();
					ATMView.instance.repaint();
					ATMView.instance.setLocationRelativeTo(ATMView.instance);
					ATMView.instance.setVisible(true);
				}
				else {
					passwordField.setText("");
					passwordField.requestFocus();
					
					//incorrect pin!
					JOptionPane.showMessageDialog(ATMView.instance,
						    "Wrong pin number",
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
		btnNext.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNext.setBounds(584, 509, 200, 42);
		add(btnNext);
		
		add(UITemplates.atmLogo);
		add(UITemplates.atmTime);
	}
}
