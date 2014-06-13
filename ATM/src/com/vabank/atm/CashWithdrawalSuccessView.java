package com.vabank.atm;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.text.NumberFormat;
import java.util.Locale;

@SuppressWarnings("serial")
public class CashWithdrawalSuccessView extends JPanel {

	/**
	 * Create the panel.
	 */
	public CashWithdrawalSuccessView() {
		setLayout(null);
		
		JLabel lblTakeYourMoney = new JLabel("Take your money");
		lblTakeYourMoney.setHorizontalAlignment(SwingConstants.CENTER);
		lblTakeYourMoney.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblTakeYourMoney.setBounds(0, 100, 784, 42);
		add(lblTakeYourMoney);
		
		add(UITemplates.atmLogo);
		add(UITemplates.atmTime);
		
		JButton btnFinish = new JButton("Finish");
		btnFinish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel contentPane = new CardInputView();
				ATMView.instance.setContentPane(contentPane);
				ATMView.instance.invalidate();
				ATMView.instance.repaint();
				ATMView.instance.setLocationRelativeTo(ATMView.instance);
				ATMView.instance.setVisible(true);
			}
		});
		btnFinish.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnFinish.setBounds(10, 509, 200, 42);
		add(btnFinish);
		
		JButton btnMainMenu = new JButton("Main menu");
		btnMainMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel contentPane = new SelectTransactionView();
				ATMView.instance.setContentPane(contentPane);
				ATMView.instance.invalidate();
				ATMView.instance.repaint();
				ATMView.instance.setLocationRelativeTo(ATMView.instance);
				ATMView.instance.setVisible(true);
			}
		});
		btnMainMenu.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnMainMenu.setBounds(584, 509, 200, 42);
		add(btnMainMenu);
		
		// adding commas for output
		Locale locale = new Locale("en", "US");
		String srt = NumberFormat.getInstance(locale).format(CashWithdrawalView.toWithdrawAmount);
		
		JLabel label = new JLabel(srt + " UAH");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 32));
		label.setBounds(0, 215, 784, 63);
		add(label);
	}

}
