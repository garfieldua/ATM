package com.vabank.atm;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;

import org.json.simple.JSONObject;

@SuppressWarnings("serial")
public class ChangePinConfirmNewView extends JPanel {

	private JPasswordField passwordField;

	/**
	 * Create the panel.
	 */
	public ChangePinConfirmNewView() {
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
		
		JLabel lblConfirmYourNew = new JLabel("Confirm your new PIN number");
		lblConfirmYourNew.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfirmYourNew.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblConfirmYourNew.setBounds(0, 163, 784, 42);
		add(lblConfirmYourNew);
		
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
				//needs to check if new password is same as one inputed previously
				String snewpin = passwordField.getText();
				String sinputedpin = ChangePinInputNewView.passwordField.getText();
				
				if (snewpin.equals(sinputedpin)) {
					//ok, they are equal
					
					//send request to change pin
					//send request to update
					JSONObject jsonObj = null;
					jsonObj = UrlConnector.getData("pin_change.php?card_num=" + CardInputView.cardNumberField.getText() + "&new_pin=" + snewpin);
					
					//if everything's ok
					Boolean request_done = (Boolean) jsonObj.get("response");
					if (request_done.booleanValue() == true) {
						//show successful view
						JPanel contentPane = new OperationSuccessfulView();
						ATMView.instance.setContentPane(contentPane);
						ATMView.instance.invalidate();
						ATMView.instance.repaint();
						ATMView.instance.setLocationRelativeTo(ATMView.instance);
						ATMView.instance.setVisible(true);
					}
					else {
						//should be fine actually :)
						//might happen if some DB issues are found
					}
				}
				else {
					//not equal PINs!
					//give an error
					JOptionPane.showMessageDialog(ATMView.instance,
						    "PIN codes doesn't match",
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
