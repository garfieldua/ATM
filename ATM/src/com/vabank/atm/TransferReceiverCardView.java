package com.vabank.atm;

import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JLabel;

import org.json.simple.JSONObject;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class TransferReceiverCardView extends JPanel {
	public static JTextField toTransferCard;

	/**
	 * Create the panel.
	 */
	public TransferReceiverCardView() {
		setLayout(null);
		
		add(UITemplates.atmLogo);
		add(UITemplates.atmTime);
		
		toTransferCard = new JTextField();
		toTransferCard.setHorizontalAlignment(SwingConstants.CENTER);
		toTransferCard.setFont(new Font("Tahoma", Font.PLAIN, 24));
		toTransferCard.setColumns(10);
		toTransferCard.setDocument(new JTextFieldLimit(16));
		toTransferCard.setBounds(200, 253, 384, 42);
		add(toTransferCard);
		
		JButton button = new JButton("Next");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JSONObject jsonObj = null;
				jsonObj = UrlConnector.getData("cardnum_existance.php?card_num=" + toTransferCard.getText() );

				Boolean cardnum_exists = (Boolean) jsonObj.get("cardnum_exists");
				
				//System.out.println(CardInputView.cardNumberField);
				//System.out.println(textField.getText());
				if (cardnum_exists.booleanValue() == true && !(toTransferCard.getText().equals(CardInputView.cardNumberField.getText()))) {
					// if there is such a card
					JPanel contentPane = new TransferAmountView();
					ATMView.instance.setContentPane(contentPane);
					ATMView.instance.invalidate();
					ATMView.instance.repaint();
					ATMView.instance.setLocationRelativeTo(ATMView.instance);
					ATMView.instance.setVisible(true);	
				}
				else {
					//clearing incorrect fields
					toTransferCard.setText("");
					toTransferCard.requestFocus();
					
					//tell the user that he has inputed wrong card number
					JOptionPane.showMessageDialog(ATMView.instance,
						    "Card number is incorrect",
						    "Wrong card number",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button.setBounds(584, 456, 200, 42);
		add(button);
		
		JButton button_1 = new JButton("Clean");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				toTransferCard.setText("");
				toTransferCard.requestFocus();
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button_1.setBounds(584, 350, 200, 42);
		add(button_1);
		
		JButton button_2 = new JButton("Back");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel contentPane = new SelectTransactionView();
				ATMView.instance.setContentPane(contentPane);
				ATMView.instance.invalidate();
				ATMView.instance.repaint();
				ATMView.instance.setLocationRelativeTo(ATMView.instance);
				ATMView.instance.setVisible(true);
			}
		});
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button_2.setBounds(10, 509, 200, 42);
		add(button_2);
		
		JLabel lblEnterReceiversCard = new JLabel("Enter receiver's card number");
		lblEnterReceiversCard.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterReceiversCard.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblEnterReceiversCard.setBounds(0, 163, 784, 42);
		add(lblEnterReceiversCard);
	}
}
