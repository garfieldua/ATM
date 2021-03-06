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
import java.text.NumberFormat;
import java.util.Locale;

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
		
		//get withdrawal limit value from database
		JSONObject jsonObj = null;
		jsonObj = UrlConnector.getData("withdrawal_limit.php?card_num=" + CardInputView.cardNumberField.getText() );
		
		String strMoney = (String) jsonObj.get("withdrawal_limit");
		// adding commas for output
		Locale locale = new Locale("en", "US");
		int moneyAmount = Integer.parseInt(strMoney);
		String srt = NumberFormat.getInstance(locale).format(moneyAmount);
		
		JLabel label = new JLabel(srt + " UAH");
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
				
				String slimit = newLimitField.getText();
				
				try 
				{
					Integer limit = Integer.parseInt(slimit);
					
					if (limit >= 0)
					{
						//send request to update
						JSONObject jsonObj = null;
						jsonObj = UrlConnector.getData("withdrawal_limit_change.php?card_num=" + CardInputView.cardNumberField.getText() + "&limit=" + limit);
						
						Boolean request_done = (Boolean) jsonObj.get("response");
						//System.out.println(strMoney);
						if (request_done.booleanValue() == true) {
							JPanel contentPane = new OperationSuccessfulView();
							ATMView.instance.setContentPane(contentPane);
							ATMView.instance.invalidate();
							ATMView.instance.repaint();
							ATMView.instance.setLocationRelativeTo(ATMView.instance);
							ATMView.instance.setVisible(true);
						}
						else {
							//Some problem here, for example file doesn't exist, no need to give an error actually
						}
					}
					else
					{
						//number given, but it is < 0
						newLimitField.setText("");
						newLimitField.requestFocus();
						
						JOptionPane.showMessageDialog(ATMView.instance,
							    "Number must be positive",
							    "Error",
							    JOptionPane.ERROR_MESSAGE);
					}
				}
				catch (java.lang.NumberFormatException e)
				{
					//not number given
					newLimitField.setText("");
					newLimitField.requestFocus();
					
					JOptionPane.showMessageDialog(ATMView.instance,
						    "Number must be given",
						    "Error",
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
