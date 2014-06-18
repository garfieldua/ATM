package com.vabank.atm;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import org.json.simple.JSONObject;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

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
				ATMView.instance.setLocationRelativeTo(ATMView.instance);
				ATMView.instance.setVisible(true);
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button.setBounds(10, 509, 200, 42);
		add(button);
		
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
					ATMView.instance.setLocationRelativeTo(ATMView.instance);
					ATMView.instance.setVisible(true);
				}
				else {
					pinField.setText("");
					pinField.requestFocus();
					
					String sError = "Wrong pin number";
					//check if card is frozen now
					Boolean frozen = (Boolean) jsonObj.get("cardnum_frozen");
					if (frozen) {
						sError = "You've inputted wrong PIN too many times. Your card is now frozen";
					}
					//some comment for push to github
					
					//incorrect pin!
					JOptionPane.showMessageDialog(ATMView.instance,
							sError,
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button_1.setBounds(584, 509, 200, 42);
		add(button_1);
		
		add(UITemplates.atmLogo);
		add(UITemplates.atmTime);
	}
}
