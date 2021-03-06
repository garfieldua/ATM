package com.vabank.atm;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import org.json.simple.JSONObject;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class CashWithdrawalOtherAmountView extends JPanel {
	private JTextField amountField;

	/**
	 * Create the panel.
	 */
	public CashWithdrawalOtherAmountView() {
		setLayout(null);

		JLabel lblEnterTheAmount = new JLabel(
				"Enter the amount of 50 multiplie");
		lblEnterTheAmount.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterTheAmount.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblEnterTheAmount.setBounds(0, 163, 784, 42);
		add(lblEnterTheAmount);

		amountField = new JTextField();
		amountField.setHorizontalAlignment(SwingConstants.CENTER);
		amountField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		amountField.setColumns(10);
		amountField.setBounds(200, 253, 384, 42);
		add(amountField);

		add(UITemplates.atmLogo);
		add(UITemplates.atmTime);

		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String samount = amountField.getText();

				try {
					Integer amount = Integer.parseInt(samount);

					// getting withdrawal limit
					JSONObject jsonObj = null;
					jsonObj = UrlConnector
							.getData("withdrawal_limit.php?card_num="
									+ CardInputView.cardNumberField.getText());

					String strMoney = (String) jsonObj.get("withdrawal_limit");
					int withdrawal_limit = Integer.parseInt(strMoney);

					if (amount > 0) {
						if (amount <= withdrawal_limit) {
							if (amount % 50 == 0) {
								JSONObject jsonObj2 = null;
								jsonObj2 = UrlConnector
										.getData("balance.php?card_num="
												+ CardInputView.cardNumberField
														.getText());
								String strMoney2 = (String) jsonObj2
										.get("balance");
								//int moneyAmount = Integer.parseInt(strMoney2);
								double moneyAmount = Double.parseDouble(strMoney2);
								
								if (moneyAmount >= amount) {
									CashWithdrawalView.toWithdrawAmount = amount;
									CashWithdrawalView.withdraw();

									JPanel contentPane = new CashWithdrawalSuccessView();
									ATMView.instance
											.setContentPane(contentPane);
									ATMView.instance.invalidate();
									ATMView.instance.repaint();
									ATMView.instance
											.setLocationRelativeTo(ATMView.instance);
									ATMView.instance.setVisible(true);
								} else {
									amountField.setText("");
									amountField.requestFocus();

									JOptionPane.showMessageDialog(
											ATMView.instance,
											"Not enough money on account",
											"Error", JOptionPane.ERROR_MESSAGE);
								}
							} else {
								amountField.setText("");
								amountField.requestFocus();

								JOptionPane.showMessageDialog(ATMView.instance,
										"Amount is not a multiplie of 50",
										"Error", JOptionPane.ERROR_MESSAGE);
							}
						} else {
							amountField.setText("");
							amountField.requestFocus();

							JOptionPane
									.showMessageDialog(
											ATMView.instance,
											"Withdrawal limit is exceeded on this card",
											"Error", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						// number given, but it is < 0
						amountField.setText("");
						amountField.requestFocus();

						JOptionPane.showMessageDialog(ATMView.instance,
								"Amount must be positive", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				} catch (java.lang.NumberFormatException e) {
					// not number given
					amountField.setText("");
					amountField.requestFocus();

					JOptionPane.showMessageDialog(ATMView.instance,
							"Number must be given", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnNext.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNext.setBounds(584, 456, 200, 42);
		add(btnNext);

		JButton btnClean = new JButton("Clean");
		btnClean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				amountField.setText("");
				amountField.requestFocus();
			}
		});
		btnClean.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnClean.setBounds(584, 350, 200, 42);
		add(btnClean);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel contentPane = new SelectTransactionView();
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
	}
}
