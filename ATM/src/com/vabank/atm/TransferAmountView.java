package com.vabank.atm;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

import org.json.simple.JSONObject;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class TransferAmountView extends JPanel {
	public static JTextField toTransferAmount;

	/**
	 * Create the panel.
	 */
	public TransferAmountView() {
		setLayout(null);

		add(UITemplates.atmLogo);
		add(UITemplates.atmTime);
		
		JLabel lblEnterTheAmount = new JLabel("Enter the amount to transfer");
		lblEnterTheAmount.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterTheAmount.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblEnterTheAmount.setBounds(0, 163, 784, 42);
		add(lblEnterTheAmount);
		
		toTransferAmount = new JTextField();
		toTransferAmount.setHorizontalAlignment(SwingConstants.CENTER);
		toTransferAmount.setFont(new Font("Tahoma", Font.PLAIN, 24));
		toTransferAmount.setColumns(10);
		toTransferAmount.setBounds(200, 253, 384, 42);
		add(toTransferAmount);
		
		JButton button = new JButton("Clean");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				toTransferAmount.setText("");
				toTransferAmount.requestFocus();
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button.setBounds(584, 350, 200, 42);
		add(button);
		
		JButton button_1 = new JButton("Next");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String samount = toTransferAmount.getText();
				
				try 
				{
					Integer amount = Integer.parseInt(samount);
					
					if (amount > 0) {
						
							JSONObject jsonObj = null;
							jsonObj = UrlConnector.getData("balance.php?card_num=" + CardInputView.cardNumberField.getText() );
							String strMoney = (String) jsonObj.get("balance");
							int moneyAmount = Integer.parseInt(strMoney);
	
							if (moneyAmount >= amount) {
								CashWithdrawalView.toWithdrawAmount = amount;
								JPanel contentPane = new TransferConfirmationView();
								ATMView.instance.setContentPane(contentPane);
								ATMView.instance.invalidate();
								ATMView.instance.repaint();
								ATMView.instance.setLocationRelativeTo(ATMView.instance);
								ATMView.instance.setVisible(true);
							}
							else {
								toTransferAmount.setText("");
								toTransferAmount.requestFocus();
								
								JOptionPane.showMessageDialog(ATMView.instance,
									    "Not enough money on account",
									    "Error",
									    JOptionPane.ERROR_MESSAGE);
							}
						}
					
					else {
						//number given, but it is < 0
						toTransferAmount.setText("");
						toTransferAmount.requestFocus();
						
						JOptionPane.showMessageDialog(ATMView.instance,
							    "Amount must be positive",
							    "Error",
							    JOptionPane.ERROR_MESSAGE);
					}
				}
				catch (java.lang.NumberFormatException e)
				{
					//not number given
					toTransferAmount.setText("");
					toTransferAmount.requestFocus();
					
					JOptionPane.showMessageDialog(ATMView.instance,
						    "Number must be given",
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button_1.setBounds(584, 456, 200, 42);
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
	}

}
