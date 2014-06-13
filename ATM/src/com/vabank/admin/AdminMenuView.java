package com.vabank.admin;

import javax.swing.JPanel;

import com.vabank.atm.ATMView;
import com.vabank.atm.AccountSettingsView;
import com.vabank.atm.UITemplates;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

@SuppressWarnings("serial")
public class AdminMenuView extends JPanel {

	/**
	 * Create the panel.
	 */
	public AdminMenuView() {
		setLayout(null);
		
		add(UITemplates.atmLogo);
		add(UITemplates.atmTime);
		
		JLabel lblPleaseSelectAction = new JLabel("Please, select action");
		lblPleaseSelectAction.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseSelectAction.setFont(new Font("Tahoma", Font.PLAIN, 26));
		lblPleaseSelectAction.setBounds(0, 135, 784, 42);
		add(lblPleaseSelectAction);
		
		JButton btnLegalPerson = new JButton("Legal person");
		btnLegalPerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel contentPane = new LegalPersonView();
				MainView.instance.setContentPane(contentPane);
				MainView.instance.invalidate();
				MainView.instance.repaint();
				MainView.instance.setLocationRelativeTo(MainView.instance);
				MainView.instance.setVisible(true);
			}
		});
		btnLegalPerson.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnLegalPerson.setBounds(76, 365, 200, 42);
		add(btnLegalPerson);
		
		JButton btnNaturalPerson = new JButton("Natural person");
		btnNaturalPerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel contentPane = null;
				try {
					contentPane = new NaturalPersonView();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				MainView.instance.setContentPane(contentPane);
				MainView.instance.invalidate();
				MainView.instance.repaint();
				MainView.instance.setLocationRelativeTo(MainView.instance);
				MainView.instance.setVisible(true);
			}
		});
		btnNaturalPerson.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNaturalPerson.setBounds(519, 365, 200, 42);
		add(btnNaturalPerson);
	}
}
