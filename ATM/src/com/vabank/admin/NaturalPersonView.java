package com.vabank.admin;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.vabank.atm.ATMView;
import com.vabank.atm.ChangePinConfirmNewView;
import com.vabank.atm.JTextFieldLimit;
import com.vabank.atm.UITemplates;

import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

@SuppressWarnings("serial")
public class NaturalPersonView extends JPanel {

	private JTable table;
	private JTextField boxName;
	private JTextField boxPhone;
	private JTextField boxAddress;
	private JTextField boxIdent;
	private JTextField boxPin;
	private JTextField boxCardNumber;
	private JTextField boxBalance;
	private JTextField boxWithdraw;
	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	public NaturalPersonView() throws SQLException {
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
		btnExit.setBounds(584, 519, 200, 42);
		add(btnExit);
		
		ResultSet resultSet = Database.getInstance().execute(
				"SELECT name AS Name"
						+ ", card_number AS CardNumber"
						+ ", phone_number AS PhoneNumber"
						+ ", ident_code AS IdentCode"
						+ ", balance AS Balance"
						+ " FROM natural_person"
						+ " ORDER BY name");
				
		ListTableModel model = ListTableModel
				.createModelFromResultSet(resultSet);
		table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


		JScrollPane jp = new JScrollPane(table);
		jp.setBounds(10, 60, 774, 220);
		add(jp);

		
		//
		table.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent e) {
						if (table.getSelectedRow() >= 0) {
							ResultSet resultSet2 = Database
									.getInstance()
									.execute(
													"SELECT name AS Name"
													+ ", card_number"
													+ ", phone_number"
													+ ", ident_code"
													+ ", balance"
													+ ", pin"
													+ ", address"
													+ ", withdrawal_limit"
													+ " FROM natural_person"
													+ " ORDER BY name");
											

							for (int i = 0; i < table.getSelectedRow() + 1; i++) {
								try {
									resultSet2.next();
								} catch (SQLException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}

							//

							try {
								String name = resultSet2.getString(1);
								boxName.setText(name);
								
								String card_number = resultSet2.getString(2);
								boxCardNumber.setText(card_number);

								String phone_number = resultSet2.getString(3);
								boxPhone.setText(phone_number);
								
								String ident_code = resultSet2.getString(4);
								boxIdent.setText(ident_code);
								
								String balance = resultSet2.getString(5);
								boxBalance.setText(balance);
								
								String pin = resultSet2.getString(6);
								boxPin.setText(pin);
								
								String address = resultSet2.getString(7);
								boxAddress.setText(address);
								
								String withdrawal_limit = resultSet2.getString(8);
								boxWithdraw.setText(withdrawal_limit);
								
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						}
					}
				});

		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int reply = JOptionPane.showConfirmDialog(MainView.instance, "Do you really want to delete selected natural person?", "Confirm your action", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					if (table.getSelectedRow() >= 0) {
						ResultSet resultSet2 = Database.getInstance().execute(
								"SELECT name AS Name"
										+ ", card_number AS CardNumber"
										+ ", phone_number AS PhoneNumber"
										+ ", ident_code AS IdentCode"
										+ ", balance AS Balance"
										+ " FROM natural_person"
										+ " ORDER BY name");
	
						for (int i = 0; i < table.getSelectedRow() + 1; i++) {
							try {
								resultSet2.next();
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
	
						//
						try {
							String code = resultSet2.getString(2);
	
							// statemenet needed
	
	
							ResultSet resultSet3 = Database.getInstance().execute(
										"DELETE" + " FROM natural_person"
												+ " WHERE card_number = '"
												+ code + "'");
	
							ResultSet resultSet4 = Database
									.getInstance()
									.execute(
											"SELECT name AS Name"
													+ ", card_number AS CardNumber"
													+ ", phone_number AS PhoneNumber"
													+ ", ident_code AS IdentCode"
													+ ", balance AS Balance"
													+ " FROM natural_person"
													+ " ORDER BY name");
	
							ListTableModel model2 = ListTableModel
									.createModelFromResultSet(resultSet4);
							table.setModel(model2);
	
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					else {
						return;
					}
				}
			}
		});
		btnDelete.setBounds(695, 291, 89, 23);
		add(btnDelete);

		JButton btnNewButton = new JButton("Update");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String snewpin = boxPin.getText();
				
				try 
				{
					// TODO: PIN code shouldn't be an integer! What about PIN 0220? :)
					Integer newpin = Integer.parseInt(snewpin);
					//fine, we could convert it
					
					if (newpin.intValue() >= 0) {
						if (boxPin.getText().length() == 4) {
							//ok, fine
						}
						else {
							//length of PIN is not 4
							JOptionPane.showMessageDialog(ATMView.instance,
								    "PIN must have 4 digits",
								    "Error",
								    JOptionPane.ERROR_MESSAGE);
							
							return;
						}
					}
					else {
						//bad number given (-100 for example)
						JOptionPane.showMessageDialog(ATMView.instance,
							    "PIN code must consist of digits only",
							    "Error",
							    JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				catch (java.lang.NumberFormatException e1) {
					//not number given
					//tell the user
					JOptionPane.showMessageDialog(ATMView.instance,
						    "PIN code must consist of digits only",
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				/*
				ResultSet resultSet50 = Database.getInstance().execute(
						"SELECT * FROM natural_person WHERE card_number = '" + boxCardNumber.getText() + "'");
				
				try {
					if (resultSet50.next()) {
						JOptionPane.showMessageDialog(ATMView.instance,
							    "This card number already exists!",
							    "Error",
							    JOptionPane.ERROR_MESSAGE);
						return;
					}
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				*/
				
				if (table.getSelectedRow() >= 0) {
					ResultSet resultSet2 = Database.getInstance().execute(
							"SELECT name AS Name"
									+ ", card_number"
									+ ", phone_number"
									+ ", ident_code"
									+ ", balance"
									+ ", pin"
									+ ", address"
									+ ", withdrawal_limit"
									+ " FROM natural_person"
									+ " ORDER BY name");

					for (int i = 0; i < table.getSelectedRow() + 1; i++) {
						try {
							resultSet2.next();
						} catch (SQLException e11) {
							// TODO Auto-generated catch block
							e11.printStackTrace();
						}
					}

					//

					try {
						String ident_code = resultSet2.getString(2);

						@SuppressWarnings("unused")
						
						ResultSet resultSet4 = Database.getInstance().execute(
								"UPDATE natural_person" + " SET name = '" + boxName.getText() + "', card_number = '"
										+ boxCardNumber.getText() + "', phone_number = '"
										+ boxPhone.getText() + "', ident_code = '"
										+ boxIdent.getText() + "', balance = "
										+ boxBalance.getText() + ", pin = '"
										+ boxPin.getText() + "', address = '"
										+ boxAddress.getText() + "', withdrawal_limit = "
										+ boxWithdraw.getText() + " WHERE card_number = '" + ident_code + "'");

						ResultSet resultSet10 = Database
								.getInstance()
								.execute(
										"SELECT name AS Name"
												+ ", card_number AS CardNumber"
												+ ", phone_number AS PhoneNumber"
												+ ", ident_code AS IdentCode"
												+ ", balance AS Balance"
												+ " FROM natural_person"
												+ " ORDER BY name");

						try {
							ListTableModel model10 = ListTableModel
									.createModelFromResultSet(resultSet10);
							table.setModel(model10);
						} catch (SQLException ea) {
							// TODO Auto-generated catch block
							ea.printStackTrace();
						}

					} catch (SQLException e11) {
						// TODO Auto-generated catch block
						e11.printStackTrace();
					} 
				}
			}
		});
		btnNewButton.setBounds(109, 519, 89, 23);
		add(btnNewButton);

		JButton btnNewButton_1 = new JButton("Create");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String snewpin = boxPin.getText();
				
				try 
				{
					// TODO: PIN code shouldn't be an integer! What about PIN 0220? :)
					Integer newpin = Integer.parseInt(snewpin);
					//fine, we could convert it
					
					if (newpin.intValue() >= 0) {
						if (boxPin.getText().length() == 4) {
							//ok, fine
						}
						else {
							//length of PIN is not 4
							JOptionPane.showMessageDialog(ATMView.instance,
								    "PIN must have 4 digits",
								    "Error",
								    JOptionPane.ERROR_MESSAGE);
							
							return;
						}
					}
					else {
						//bad number given (-100 for example)
						JOptionPane.showMessageDialog(ATMView.instance,
							    "PIN code must consist of digits only",
							    "Error",
							    JOptionPane.ERROR_MESSAGE);
						return;
					}
				}
				catch (java.lang.NumberFormatException e) {
					//not number given
					//tell the user
					JOptionPane.showMessageDialog(ATMView.instance,
						    "PIN code must consist of digits only",
						    "Error",
						    JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				ResultSet resultSet50 = Database.getInstance().execute(
						"SELECT * FROM natural_person WHERE card_number = '" + boxCardNumber.getText() + "'");
				
				try {
					if (resultSet50.next()) {
						JOptionPane.showMessageDialog(ATMView.instance,
							    "This card number already exists!",
							    "Error",
							    JOptionPane.ERROR_MESSAGE);
						return;
					}
				
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
					try {
						//@SuppressWarnings("unused")
						
						ResultSet resultSet3 = Database.getInstance().execute(
								"INSERT INTO natural_person (name, card_number, phone_number, ident_code, balance, pin, address, withdrawal_limit)"
										+ " VALUES ('" + boxName.getText() + "', '"
										+ boxCardNumber.getText() + "', '"
										+ boxPhone.getText() + "', '"
										+ boxIdent.getText() + "', "
										+ boxBalance.getText() + ", '"
										+ boxPin.getText() + "', '"
										+ boxAddress.getText() + "', "
										+ boxWithdraw.getText() + ")");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				ResultSet resultSet5 = Database.getInstance().execute(
						"SELECT name AS Name"
								+ ", card_number AS CardNumber"
								+ ", phone_number AS PhoneNumber"
								+ ", ident_code AS IdentCode"
								+ ", balance AS Balance"
								+ " FROM natural_person"
								+ " ORDER BY name");

				try {
					ListTableModel model3 = ListTableModel
							.createModelFromResultSet(resultSet5);
					table.setModel(model3);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnNewButton_1.setBounds(10, 519, 89, 23);
		add(btnNewButton_1);

		boxName = new JTextField();
		boxName.setBounds(10, 343, 188, 20);
		add(boxName);
		boxName.setColumns(10);
		boxName.setDocument(new JTextFieldLimit(255));

		JLabel lblName = new JLabel("Name");
		lblName.setLabelFor(boxName);
		lblName.setBounds(10, 325, 89, 14);
		add(lblName);

		JLabel lblPhone = new JLabel("Phone number");
		lblPhone.setBounds(10, 374, 158, 14);
		add(lblPhone);

		boxPhone = new JTextField();
		boxPhone.setColumns(10);
		boxPhone.setBounds(10, 394, 188, 20);
		add(boxPhone);
		boxPhone.setDocument(new JTextFieldLimit(20));

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(10, 425, 89, 14);
		add(lblAddress);

		boxAddress = new JTextField();
		boxAddress.setColumns(10);
		boxAddress.setBounds(10, 443, 188, 20);
		add(boxAddress);
		boxAddress.setDocument(new JTextFieldLimit(255));

		boxIdent = new JTextField();
		boxIdent.setColumns(10);
		boxIdent.setBounds(10, 488, 188, 20);
		add(boxIdent);
		boxIdent.setDocument(new JTextFieldLimit(10));

		JButton btnClearInfo = new JButton("Clear fields");
		btnClearInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boxName.setText("");
				boxPhone.setText("");
				boxAddress.setText("");
				boxIdent.setText("");
				boxPin.setText("");
				boxWithdraw.setText("");
				boxBalance.setText("");
				boxCardNumber.setText("");
			}
		});
		btnClearInfo.setBounds(10, 291, 124, 23);
		add(btnClearInfo);

		JLabel lblCardNumber = new JLabel("Card number");
		lblCardNumber.setBounds(243, 325, 144, 14);
		add(lblCardNumber);

		JLabel lblPin = new JLabel("PIN");
		lblPin.setBounds(243, 374, 115, 14);
		add(lblPin);

		boxPin = new JTextField();
		boxPin.setColumns(10);
		boxPin.setBounds(243, 394, 188, 20);
		add(boxPin);
		boxPin.setDocument(new JTextFieldLimit(4));

		JLabel lblIdent = new JLabel("Ident code");
		lblIdent.setBounds(10, 470, 134, 14);
		add(lblIdent);
		
		JLabel lblStartBalance = new JLabel("Balance");
		lblStartBalance.setBounds(243, 425, 115, 14);
		add(lblStartBalance);
		
		boxCardNumber = new JTextField();
		boxCardNumber.setColumns(10);
		boxCardNumber.setBounds(243, 343, 188, 20);
		boxCardNumber.setDocument(new JTextFieldLimit(16));
		add(boxCardNumber);
		
		boxBalance = new JTextField();
		boxBalance.setColumns(10);
		boxBalance.setBounds(243, 443, 188, 20);
		boxBalance.setDocument(new JTextFieldLimit(255));
		add(boxBalance);
		
		JLabel lblWithdraw = new JLabel("Withdrawal limit");
		lblWithdraw.setBounds(243, 470, 115, 14);
		add(lblWithdraw);
		
		boxWithdraw = new JTextField();
		boxWithdraw.setColumns(10);
		boxWithdraw.setBounds(243, 488, 188, 20);
		boxWithdraw.setDocument(new JTextFieldLimit(255));
		add(boxWithdraw);
	}
}
