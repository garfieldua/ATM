package com.vabank.atm;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import org.json.simple.JSONObject;

@SuppressWarnings("serial")
public class ChangePinInputNewView extends JPanel {
	public static JPasswordField passwordField;
	
	/**
	 * Create the panel.
	 */
	public ChangePinInputNewView() {
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
		
		JLabel lblEnterYourNew = new JLabel("Enter your new PIN number");
		lblEnterYourNew.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterYourNew.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblEnterYourNew.setBounds(0, 163, 784, 42);
		add(lblEnterYourNew);
		
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
				
				String snewpin = passwordField.getText();
				
				try 
				{
					Integer newpin = Integer.parseInt(snewpin);
					//fine, we could convert it
					
					if (newpin.intValue() >= 0) {
						if (passwordField.getText().length() == 4) {
							JPanel contentPane = new ChangePinConfirmNewView();
							ATMView.instance.setContentPane(contentPane);
							ATMView.instance.invalidate();
							ATMView.instance.repaint();
							ATMView.instance.setLocationRelativeTo(ATMView.instance);
							ATMView.instance.setVisible(true);	
						}
						else {
							//length of PIN is not 4
						}
					}
					else {
						//bad number given (-100 for example)
					}
				}
				catch (java.lang.NumberFormatException e)
				{
					//not number given
					//tell the user
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
