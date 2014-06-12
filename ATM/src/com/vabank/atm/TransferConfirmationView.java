package com.vabank.atm;

import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class TransferConfirmationView extends JPanel {

	/**
	 * Create the panel.
	 */
	public TransferConfirmationView() {
		setLayout(null);
		
		add(UITemplates.atmLogo);
		add(UITemplates.atmTime);
		
		JLabel lblConfirmTheCorrectess = new JLabel("Confirm the correctess of information");
		lblConfirmTheCorrectess.setHorizontalAlignment(SwingConstants.CENTER);
		lblConfirmTheCorrectess.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblConfirmTheCorrectess.setBounds(0, 100, 784, 42);
		add(lblConfirmTheCorrectess);
		
		JLabel lblReceiversCardNumber = new JLabel("Receiver's card number:");
		lblReceiversCardNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblReceiversCardNumber.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblReceiversCardNumber.setBounds(0, 188, 784, 42);
		add(lblReceiversCardNumber);
		
		JLabel lblToTrasferCard = new JLabel(TransferReceiverCardView.toTransferCard.getText());
		lblToTrasferCard.setHorizontalAlignment(SwingConstants.CENTER);
		lblToTrasferCard.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblToTrasferCard.setBounds(0, 228, 784, 42);
		add(lblToTrasferCard);
		
		JLabel lblTransferAmount = new JLabel("Transfer amount:");
		lblTransferAmount.setHorizontalAlignment(SwingConstants.CENTER);
		lblTransferAmount.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblTransferAmount.setBounds(0, 281, 784, 42);
		add(lblTransferAmount);
		
		JLabel lblToTransferAmount = new JLabel(TransferAmountView.toTransferAmount.getText());
		lblToTransferAmount.setHorizontalAlignment(SwingConstants.CENTER);
		lblToTransferAmount.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblToTransferAmount.setBounds(0, 323, 784, 42);
		add(lblToTransferAmount);
		
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
		
		JButton btnNext = new JButton("Next");
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//let's transfer out money!
				String samount = TransferAmountView.toTransferAmount.getText();
				Integer amount = Integer.parseInt(samount);
				
				UrlConnector.getData("transfer.php?from=" + CardInputView.cardNumberField.getText() + "&amount=" + amount + "&to=" + TransferReceiverCardView.toTransferCard.getText());
				
				JPanel contentPane = new OperationSuccessfulView();
				ATMView.instance.setContentPane(contentPane);
				ATMView.instance.invalidate();
				ATMView.instance.repaint();
				ATMView.instance.setLocationRelativeTo(ATMView.instance);
				ATMView.instance.setVisible(true);
			}
		});
		btnNext.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNext.setBounds(584, 509, 200, 42);
		add(btnNext);
	}

}
