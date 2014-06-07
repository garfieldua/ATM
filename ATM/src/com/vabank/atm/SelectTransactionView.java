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
public class SelectTransactionView extends JPanel {

	/**
	 * Create the panel.
	 */
	public SelectTransactionView() {
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
		
		JLabel lblSelectTransaction = new JLabel("Select Transaction");
		lblSelectTransaction.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectTransaction.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblSelectTransaction.setBounds(0, 100, 784, 42);
		add(lblSelectTransaction);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel contentPane = new CardInputView();
				ATMView.instance.setContentPane(contentPane);
				ATMView.instance.invalidate();
				ATMView.instance.repaint();
				ATMView.instance.setLocationRelativeTo(null);
				ATMView.instance.setVisible(true);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnExit.setBounds(10, 509, 200, 42);
		add(btnExit);
		
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
		btnCashWithdrawal.setBounds(10, 456, 200, 42);
		add(btnCashWithdrawal);
		
		JButton btnBalanceInquiry = new JButton("Balance Inquiry");
		btnBalanceInquiry.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel contentPane = new ClientBalanceView();
				ATMView.instance.setContentPane(contentPane);
				ATMView.instance.invalidate();
				ATMView.instance.repaint();
				ATMView.instance.setLocationRelativeTo(null);
				ATMView.instance.setVisible(true);
			}
		});
		btnBalanceInquiry.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnBalanceInquiry.setBounds(10, 350, 200, 42);
		add(btnBalanceInquiry);
		
		JButton btnAccountSettings = new JButton("Account Settings");
		btnAccountSettings.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAccountSettings.setBounds(584, 456, 200, 42);
		add(btnAccountSettings);
		
		JButton btnTransfer = new JButton("Transfer");
		btnTransfer.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnTransfer.setBounds(584, 350, 200, 42);
		add(btnTransfer);

		Timer t = new Timer(1000, updateClockAction);
		t.start();
	}

}
