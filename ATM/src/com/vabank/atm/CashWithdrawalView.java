package com.vabank.atm;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import org.json.simple.JSONObject;

@SuppressWarnings("serial")
public class CashWithdrawalView extends JPanel {
	public static int toWithdrawAmount;
	/**
	 * Create the panel.
	 */
	public CashWithdrawalView() {
		setLayout(null);
		
		JLabel lblWhichAmountDo = new JLabel("Which amount do you want to withdraw?");
		lblWhichAmountDo.setHorizontalAlignment(SwingConstants.CENTER);
		lblWhichAmountDo.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblWhichAmountDo.setBounds(0, 100, 784, 42);
		add(lblWhichAmountDo);
		
		JButton button = new JButton("Back");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel contentPane = new SelectTransactionView();
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
		
		JButton btnUah_2 = new JButton("200 UAH");
		btnUah_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JSONObject jsonObj = null;
				jsonObj = UrlConnector.getData("balance.php?card_num=" + CardInputView.cardNumberField.getText() );
				String strMoney = (String) jsonObj.get("balance");
				int moneyAmount = Integer.parseInt(strMoney);
				
				if (moneyAmount >= 200){
					toWithdrawAmount = 200;
					JPanel contentPane = new CashWithdrawalSuccessView();
					ATMView.instance.setContentPane(contentPane);
					ATMView.instance.invalidate();
					ATMView.instance.repaint();
					ATMView.instance.setLocationRelativeTo(ATMView.instance);
					ATMView.instance.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(ATMView.instance,
						    "Not enough money on account",
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
				}
					
			}
		});
		btnUah_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnUah_2.setBounds(10, 456, 200, 42);
		add(btnUah_2);
		
		JButton btnUah_1 = new JButton("100 UAH");
		btnUah_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JSONObject jsonObj = null;
				jsonObj = UrlConnector.getData("balance.php?card_num=" + CardInputView.cardNumberField.getText() );
				String strMoney = (String) jsonObj.get("balance");
				int moneyAmount = Integer.parseInt(strMoney);
				
				if (moneyAmount >= 100){
					toWithdrawAmount = 100;
					JPanel contentPane = new CashWithdrawalSuccessView();
					ATMView.instance.setContentPane(contentPane);
					ATMView.instance.invalidate();
					ATMView.instance.repaint();
					ATMView.instance.setLocationRelativeTo(ATMView.instance);
					ATMView.instance.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(ATMView.instance,
						    "Not enough money on account",
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnUah_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnUah_1.setBounds(10, 403, 200, 42);
		add(btnUah_1);
		
		JButton btnUah = new JButton("50 UAH");
		btnUah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JSONObject jsonObj = null;
				jsonObj = UrlConnector.getData("balance.php?card_num=" + CardInputView.cardNumberField.getText() );
				String strMoney = (String) jsonObj.get("balance");
				int moneyAmount = Integer.parseInt(strMoney);
				
				if (moneyAmount >= 50){
					toWithdrawAmount = 50;
					JPanel contentPane = new CashWithdrawalSuccessView();
					ATMView.instance.setContentPane(contentPane);
					ATMView.instance.invalidate();
					ATMView.instance.repaint();
					ATMView.instance.setLocationRelativeTo(ATMView.instance);
					ATMView.instance.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(ATMView.instance,
						    "Not enough money on account",
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnUah.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnUah.setBounds(10, 350, 200, 42);
		add(btnUah);
		
		JButton button_4 = new JButton("Other amount");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel contentPane = new CashWithdrawalOtherAmountView();
				ATMView.instance.setContentPane(contentPane);
				ATMView.instance.invalidate();
				ATMView.instance.repaint();
				ATMView.instance.setLocationRelativeTo(ATMView.instance);
				ATMView.instance.setVisible(true);
			}
		});
		button_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button_4.setBounds(584, 509, 200, 42);
		add(button_4);
		
		JButton btnUah_5 = new JButton("2000 UAH");
		btnUah_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JSONObject jsonObj = null;
				jsonObj = UrlConnector.getData("balance.php?card_num=" + CardInputView.cardNumberField.getText() );
				String strMoney = (String) jsonObj.get("balance");
				int moneyAmount = Integer.parseInt(strMoney);
				
				if (moneyAmount >= 2000){
					toWithdrawAmount = 2000;
					JPanel contentPane = new CashWithdrawalSuccessView();
					ATMView.instance.setContentPane(contentPane);
					ATMView.instance.invalidate();
					ATMView.instance.repaint();
					ATMView.instance.setLocationRelativeTo(ATMView.instance);
					ATMView.instance.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(ATMView.instance,
						    "Not enough money on account",
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnUah_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnUah_5.setBounds(584, 456, 200, 42);
		add(btnUah_5);
		
		JButton btnUah_4 = new JButton("1000 UAH");
		btnUah_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JSONObject jsonObj = null;
				jsonObj = UrlConnector.getData("balance.php?card_num=" + CardInputView.cardNumberField.getText() );
				String strMoney = (String) jsonObj.get("balance");
				int moneyAmount = Integer.parseInt(strMoney);
				
				if (moneyAmount >= 1000){
					toWithdrawAmount = 1000;
					JPanel contentPane = new CashWithdrawalSuccessView();
					ATMView.instance.setContentPane(contentPane);
					ATMView.instance.invalidate();
					ATMView.instance.repaint();
					ATMView.instance.setLocationRelativeTo(ATMView.instance);
					ATMView.instance.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(ATMView.instance,
						    "Not enough money on account",
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnUah_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnUah_4.setBounds(584, 403, 200, 42);
		add(btnUah_4);
		
		JButton btnUah_3 = new JButton("500 UAH");
		btnUah_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JSONObject jsonObj = null;
				jsonObj = UrlConnector.getData("balance.php?card_num=" + CardInputView.cardNumberField.getText() );
				String strMoney = (String) jsonObj.get("balance");
				int moneyAmount = Integer.parseInt(strMoney);
				
				if (moneyAmount >= 500){
					toWithdrawAmount = 500;
					JPanel contentPane = new CashWithdrawalSuccessView();
					ATMView.instance.setContentPane(contentPane);
					ATMView.instance.invalidate();
					ATMView.instance.repaint();
					ATMView.instance.setLocationRelativeTo(ATMView.instance);
					ATMView.instance.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(ATMView.instance,
						    "Not enough money on account",
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnUah_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnUah_3.setBounds(584, 350, 200, 42);
		add(btnUah_3);

		add(UITemplates.atmLogo);
		add(UITemplates.atmTime);
	}

}
