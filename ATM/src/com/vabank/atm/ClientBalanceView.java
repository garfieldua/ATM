package com.vabank.atm;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.Locale;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import org.json.simple.JSONObject;

@SuppressWarnings("serial")
public class ClientBalanceView extends JPanel {

	/**
	 * Create the panel.
	 */
	public ClientBalanceView() {
		setLayout(null);
		
		JLabel lblYourBalanceIs = new JLabel("Your balance is");
		lblYourBalanceIs.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourBalanceIs.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblYourBalanceIs.setBounds(0, 100, 784, 42);
		add(lblYourBalanceIs);
		
		//get money value from database
		JSONObject jsonObj = null;
		jsonObj = UrlConnector.getData("balance.php?card_num=" + CardInputView.cardNumberField.getText() );
		
		String strMoney = (String) jsonObj.get("balance");

		// adding commas for output
		Locale locale = new Locale("en", "US");
		int moneyAmount = Integer.parseInt(strMoney);
		String srt = NumberFormat.getInstance(locale).format(moneyAmount);
		
		JLabel lblMoneyAmount = new JLabel("");
		lblMoneyAmount.setText(srt + " UAH");
		lblMoneyAmount.setHorizontalAlignment(SwingConstants.CENTER);
		lblMoneyAmount.setFont(new Font("Tahoma", Font.PLAIN, 32));
		lblMoneyAmount.setBounds(0, 215, 784, 63);
		add(lblMoneyAmount);
		
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
		
		JButton btnCashWithdrawal = new JButton("Cash Withdrawal");
		btnCashWithdrawal.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel contentPane = new CashWithdrawalView();
				ATMView.instance.setContentPane(contentPane);
				ATMView.instance.invalidate();
				ATMView.instance.repaint();
				ATMView.instance.setLocationRelativeTo(ATMView.instance);
				ATMView.instance.setVisible(true);
			}
		});
		btnCashWithdrawal.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCashWithdrawal.setBounds(584, 509, 200, 42);
		add(btnCashWithdrawal);

		add(UITemplates.atmLogo);
		add(UITemplates.atmTime);
	}

}
