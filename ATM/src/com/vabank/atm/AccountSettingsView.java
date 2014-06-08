package com.vabank.atm;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class AccountSettingsView extends JPanel {

	/**
	 * Create the panel.
	 */
	public AccountSettingsView() {
		
		setLayout(null);
		
		JLabel lblAccountSettings = new JLabel("Account Settings");
		lblAccountSettings.setHorizontalAlignment(SwingConstants.CENTER);
		lblAccountSettings.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblAccountSettings.setBounds(0, 100, 784, 42);
		add(lblAccountSettings);
		
		JButton btnChangePin = new JButton("Change PIN");
		btnChangePin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnChangePin.setBounds(584, 456, 200, 42);
		add(btnChangePin);
		
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
		
		JButton btnWithdrawalLimit = new JButton("Withdrawal limit");
		btnWithdrawalLimit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel contentPane = new WithdrawalLimitView();
				ATMView.instance.setContentPane(contentPane);
				ATMView.instance.invalidate();
				ATMView.instance.repaint();
				ATMView.instance.setLocationRelativeTo(ATMView.instance);
				ATMView.instance.setVisible(true);
			}
		});
		btnWithdrawalLimit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnWithdrawalLimit.setBounds(584, 350, 200, 42);
		add(btnWithdrawalLimit);
		
		add(UITemplates.atmLogo);
		add(UITemplates.atmTime);
	}
}
