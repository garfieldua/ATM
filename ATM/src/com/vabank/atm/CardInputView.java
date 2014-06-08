package com.vabank.atm;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

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

		JButton btnClean = UITemplates.createUIButton("Clean");
		btnClean.setBounds(584, 456, 200, 42);
		btnClean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardNumberField.setText("");
				cardNumberField.requestFocus();
			}
		});
		add(btnClean);
		
		JLabel lblWelcomeLabel = new JLabel("Please input your card number");
		lblWelcomeLabel.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblWelcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcomeLabel.setBounds(0, 163, 784, 42);
		add(lblWelcomeLabel);
		
		
		JButton btnNext = UITemplates.createUIButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
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
					ATMView.instance.setLocationRelativeTo(ATMView.instance);
					ATMView.instance.setVisible(true);	
				}
				else {
					//clearing incorrect fields
					cardNumberField.setText("");
					cardNumberField.requestFocus();
					
					//tell the user that he has inputed wrong card number
					JOptionPane.showMessageDialog(ATMView.instance,
						    "Card number is incorrect",
						    "Wrong card number",
						    JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnNext.setBounds(584, 509, 200, 42);
		add(btnNext);
		
		add(UITemplates.atmLogo);
		add(UITemplates.atmTime);
		
		cardNumberField.requestFocus();
	}

}
