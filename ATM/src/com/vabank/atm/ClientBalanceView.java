package com.vabank.atm;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class ClientBalanceView extends JPanel {

	/**
	 * Create the panel.
	 */
	public ClientBalanceView() {
		setLayout(null);

		JLabel lblLogo = new JLabel("VA Bank");
		lblLogo.setForeground(new Color(0, 0, 255));
		lblLogo.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblLogo.setBounds(10, 11, 134, 32);
		add(lblLogo);
		
		final JLabel lblTime = new JLabel("");
		lblTime.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblTime.setBounds(640, 11, 134, 32);
		add(lblTime);
		
		ActionListener updateClockAction = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String timeStamp = new SimpleDateFormat("HH:mm")
						.format(Calendar.getInstance().getTime());
				lblTime.setText(timeStamp);
			}
		};

		String timeStamp = new SimpleDateFormat("HH:mm")
				.format(Calendar.getInstance().getTime());
		lblTime.setText(timeStamp);
		
		JLabel lblYourBalanceIs = new JLabel("Your balance is");
		lblYourBalanceIs.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourBalanceIs.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblYourBalanceIs.setBounds(0, 100, 784, 42);
		add(lblYourBalanceIs);
		
		int moneyAmount = 10000;
		JLabel lblMoneyAmount = new JLabel("");
		lblMoneyAmount.setText(moneyAmount + " UAH");
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
				ATMView.instance.setLocationRelativeTo(null);
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
				ATMView.instance.setLocationRelativeTo(null);
				ATMView.instance.setVisible(true);
			}
		});
		btnCashWithdrawal.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCashWithdrawal.setBounds(584, 509, 200, 42);
		add(btnCashWithdrawal);

		Timer t = new Timer(1000, updateClockAction);
		t.start();
	}

}
