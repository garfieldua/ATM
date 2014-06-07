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
public class CashWithdrawalView extends JPanel {

	/**
	 * Create the panel.
	 */
	public CashWithdrawalView() {
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
				ATMView.instance.setLocationRelativeTo(null);
				ATMView.instance.setVisible(true);
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button.setBounds(10, 509, 200, 42);
		add(button);
		
		JButton btnUah_2 = new JButton("200 UAH");
		btnUah_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnUah_2.setBounds(10, 456, 200, 42);
		add(btnUah_2);
		
		JButton btnUah_1 = new JButton("100 UAH");
		btnUah_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnUah_1.setBounds(10, 403, 200, 42);
		add(btnUah_1);
		
		JButton btnUah = new JButton("50 UAH");
		btnUah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnUah.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnUah.setBounds(10, 350, 200, 42);
		add(btnUah);
		
		JButton button_4 = new JButton("Other amount");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button_4.setBounds(584, 509, 200, 42);
		add(button_4);
		
		JButton btnUah_5 = new JButton("2000 UAH");
		btnUah_5.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnUah_5.setBounds(584, 456, 200, 42);
		add(btnUah_5);
		
		JButton btnUah_4 = new JButton("1000 UAH");
		btnUah_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnUah_4.setBounds(584, 403, 200, 42);
		add(btnUah_4);
		
		JButton btnUah_3 = new JButton("500 UAH");
		btnUah_3.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnUah_3.setBounds(584, 350, 200, 42);
		add(btnUah_3);

		Timer t = new Timer(1000, updateClockAction);
		t.start();
	}

}