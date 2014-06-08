package com.vabank.atm;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class WithdrawalLimitView extends JPanel {
	private JTextField newLimitField;

	/**
	 * Create the panel.
	 */
	public WithdrawalLimitView() {
		setLayout(null);
		
		JLabel lblCurrentWithdrawalLimit = new JLabel("Current withdrawal limit is");
		lblCurrentWithdrawalLimit.setHorizontalAlignment(SwingConstants.CENTER);
		lblCurrentWithdrawalLimit.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblCurrentWithdrawalLimit.setBounds(0, 100, 784, 42);
		add(lblCurrentWithdrawalLimit);
		
		JLabel label = new JLabel("0 UAH");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 32));
		label.setBounds(0, 139, 784, 63);
		add(label);
		
		JLabel lblEnterNewWithdrawal = new JLabel("Enter new withdrawal limit");
		lblEnterNewWithdrawal.setHorizontalAlignment(SwingConstants.CENTER);
		lblEnterNewWithdrawal.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblEnterNewWithdrawal.setBounds(0, 210, 784, 42);
		add(lblEnterNewWithdrawal);
		
		newLimitField = new JTextField();
		newLimitField.setHorizontalAlignment(SwingConstants.CENTER);
		newLimitField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		newLimitField.setColumns(10);
		newLimitField.setBounds(200, 265, 384, 42);
		add(newLimitField);
		
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
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel contentPane = new OperationSuccessfulView();
				ATMView.instance.setContentPane(contentPane);
				ATMView.instance.invalidate();
				ATMView.instance.repaint();
				ATMView.instance.setLocationRelativeTo(ATMView.instance);
				ATMView.instance.setVisible(true);
			}
		});
		btnNext.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNext.setBounds(584, 456, 200, 42);
		add(btnNext);
		
		JButton btnClean = new JButton("Clean");
		btnClean.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				newLimitField.setText("");
				newLimitField.requestFocus();
			}
		});
		btnClean.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnClean.setBounds(584, 349, 200, 42);
		add(btnClean);
		
		add(UITemplates.atmLogo);
		add(UITemplates.atmTime);
	}
}
