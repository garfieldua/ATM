package com.vabank.admin;

import javax.swing.JPanel;

import com.vabank.atm.UITemplates;

import javax.swing.JButton;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class LegalPersonView extends JPanel {

	/**
	 * Create the panel.
	 */
	public LegalPersonView() {
		setLayout(null);
		
		add(UITemplates.atmLogo);
		add(UITemplates.atmTime);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JPanel contentPane = new AdminMenuView();
				MainView.instance.setContentPane(contentPane);
				MainView.instance.invalidate();
				MainView.instance.repaint();
				MainView.instance.setLocationRelativeTo(MainView.instance);
				MainView.instance.setVisible(true);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnExit.setBounds(10, 519, 200, 42);
		add(btnExit);
	}

}
