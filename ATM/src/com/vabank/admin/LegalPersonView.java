package com.vabank.admin;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.vabank.atm.ATMView;
import com.vabank.atm.JTextFieldLimit;
import com.vabank.atm.UITemplates;

import javax.swing.JButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.TreeSet;

@SuppressWarnings("serial")
public class LegalPersonView extends JPanel {

	// all legal persons
	private JTable table;
	// all natural persons
	private JTable table2;
	// selected natural persons
	private JTable table3;
	
	private JTextField boxName;
	private JTextField boxPhone;
	private JTextField boxAddress;
	private JTextField boxIdent;
	private JTextField boxPin;
	private JTextField boxCardNumber;
	private JTextField boxBalance;
	private JTextField boxWithdraw;
	
	private TreeSet<String> workerIds = new TreeSet<String>();
	/**
	 * Create the panel.
	 * @throws SQLException 
	 */
	public LegalPersonView() throws SQLException {
		setLayout(null);
		
		MainView.instance.setSize(1300, 800);
		
		add(UITemplates.atmLogo);
		add(UITemplates.atmTime);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainView.instance.setSize(800, 600);
				JPanel contentPane = new AdminMenuView();
				MainView.instance.setContentPane(contentPane);
				MainView.instance.invalidate();
				MainView.instance.repaint();
				MainView.instance.setLocationRelativeTo(MainView.instance);
				MainView.instance.setVisible(true);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnExit.setBounds(594, 718, 200, 42);
		add(btnExit);
		
		// model for current workers
		final ListTableModel[] model21 = { null };
		
		// Filling legal person table
		ResultSet resultSet = Database.getInstance().execute(
				"SELECT short_name AS ShortName"
						+ ", ident_code AS IdentCode"
						+ ", funds AS Funds"
						+ ", country AS Country"
						+ ", num_cert_of_reg AS CertificateNumber"
						+ " FROM legal_person"
						+ " ORDER BY ident_code");
				
		ListTableModel model = ListTableModel
				.createModelFromResultSet(resultSet);
		table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


		JScrollPane jp = new JScrollPane(table);
		jp.setBounds(10, 60, 1280, 160);
		add(jp);

		// Filling all natural person table
		ResultSet resultSet2 = Database.getInstance().execute(
				"SELECT name AS Name"
						+ ", card_number AS CardNumber"
						+ ", phone_number AS PhoneNumber"
						+ ", ident_code AS IdentCode"
						+ " FROM natural_person"
						+ " ORDER BY name");
				
		ListTableModel model2 = ListTableModel
				.createModelFromResultSet(resultSet2);
		table2 = new JTable(model2);
		table2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane jp2 = new JScrollPane(table2);
		jp2.setBounds(10, 466, 597, 160);
		add(jp2);

		// Filling "selected" natural person table. By now in should be empty though
		ResultSet resultSet3 = Database.getInstance().execute(
				"SELECT name AS Name"
						+ ", card_number AS CardNumber"
						+ ", phone_number AS PhoneNumber"
						+ ", ident_code AS IdentCode"
						+ " FROM natural_person"
						+ " WHERE ident_code <> ident_code"
						+ " ORDER BY name");
				
		ListTableModel model3 = ListTableModel
				.createModelFromResultSet(resultSet3);
		table3 = new JTable(model3);
		table3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane jp3 = new JScrollPane(table3);
		jp3.setBounds(693, 466, 597, 160);
		add(jp3);
		
		// Processing clicking on a legal person
		table.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent e) {
						if (table.getSelectedRow() >= 0) {
							ResultSet resultSet2 = Database
									.getInstance()
									.execute(
											"SELECT short_name AS ShortName"
													+ ", full_name AS FullName"
													+ ", ident_code AS IdentCode"
													+ ", funds AS Funds"
													+ ", country AS Country"
													+ ", num_cert_of_reg AS CertificateNumber"
													+ ", reg_cert_authority AS RegAuth"
													+ ", date_cert AS DateReg"
													+ " FROM legal_person"
													+ " ORDER BY ident_code");
											

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
								String idcode = resultSet2.getString(3);
								
								//System.out.println(idcode);
								
								// now, filling table3 with natural persons that are working with on-clicked legal person
								ResultSet resultSet3 = Database
										.getInstance()
										.execute(
												"SELECT name AS Name"
														+ ", natural_person.card_number AS CardNumber"
														+ ", phone_number AS PhoneNumber"
														+ ", natural_person.ident_code AS IdentCode"
														+ ", balance AS Balance"
														+ " FROM natural_person INNER JOIN employees ON natural_person.card_number = employees.card_number"
														+ " WHERE employees.ident_code = '" + idcode + "'"
														+ " ORDER BY name");
								
								model21[0] = ListTableModel.createModelFromResultSet(resultSet3);
								table3.setModel(model21[0]);
								
								// filling
								ResultSet resultSet4 = Database
										.getInstance()
										.execute(
												"SELECT name AS Name"
														+ ", natural_person.card_number AS CardNumber"
														+ ", phone_number AS PhoneNumber"
														+ ", natural_person.ident_code AS IdentCode"
														+ ", balance AS Balance"
														+ " FROM natural_person INNER JOIN employees ON natural_person.card_number = employees.card_number"
														+ " WHERE employees.ident_code = '" + idcode + "'"
														+ " ORDER BY name");
								workerIds.clear();

								while (resultSet4.next()) {
									workerIds.add(resultSet4.getString(2));
								}
								
								System.out.println(workerIds);
								/*
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
								*/
								
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						}
					}
				});
		
		/*
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
*/
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
		btnDelete.setBounds(655, 771, 89, 23);
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
		btnNewButton.setBounds(109, 984, 89, 23);
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
		btnNewButton_1.setBounds(10, 984, 89, 23);
		add(btnNewButton_1);

		boxName = new JTextField();
		boxName.setBounds(10, 808, 188, 20);
		add(boxName);
		boxName.setColumns(10);
		boxName.setDocument(new JTextFieldLimit(255));

		JLabel lblName = new JLabel("Name");
		lblName.setLabelFor(boxName);
		lblName.setBounds(10, 790, 89, 14);
		add(lblName);

		JLabel lblPhone = new JLabel("Phone number");
		lblPhone.setBounds(10, 839, 158, 14);
		add(lblPhone);

		boxPhone = new JTextField();
		boxPhone.setColumns(10);
		boxPhone.setBounds(10, 859, 188, 20);
		add(boxPhone);
		boxPhone.setDocument(new JTextFieldLimit(20));

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(10, 890, 89, 14);
		add(lblAddress);

		boxAddress = new JTextField();
		boxAddress.setColumns(10);
		boxAddress.setBounds(10, 908, 188, 20);
		add(boxAddress);
		boxAddress.setDocument(new JTextFieldLimit(255));

		boxIdent = new JTextField();
		boxIdent.setColumns(10);
		boxIdent.setBounds(10, 953, 188, 20);
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
		btnClearInfo.setBounds(10, 756, 124, 23);
		add(btnClearInfo);

		JLabel lblCardNumber = new JLabel("Card number");
		lblCardNumber.setBounds(243, 790, 144, 14);
		add(lblCardNumber);

		JLabel lblPin = new JLabel("PIN");
		lblPin.setBounds(243, 839, 115, 14);
		add(lblPin);

		boxPin = new JTextField();
		boxPin.setColumns(10);
		boxPin.setBounds(243, 859, 188, 20);
		add(boxPin);
		boxPin.setDocument(new JTextFieldLimit(4));

		JLabel lblIdent = new JLabel("Ident code");
		lblIdent.setBounds(10, 935, 134, 14);
		add(lblIdent);
		
		JLabel lblStartBalance = new JLabel("Balance");
		lblStartBalance.setBounds(243, 890, 115, 14);
		add(lblStartBalance);
		
		boxCardNumber = new JTextField();
		boxCardNumber.setColumns(10);
		boxCardNumber.setBounds(243, 808, 188, 20);
		boxCardNumber.setDocument(new JTextFieldLimit(16));
		add(boxCardNumber);
		
		boxBalance = new JTextField();
		boxBalance.setColumns(10);
		boxBalance.setBounds(243, 908, 188, 20);
		boxBalance.setDocument(new JTextFieldLimit(255));
		add(boxBalance);
		
		JLabel lblWithdraw = new JLabel("Withdrawal limit");
		lblWithdraw.setBounds(243, 935, 115, 14);
		add(lblWithdraw);
		
		boxWithdraw = new JTextField();
		boxWithdraw.setColumns(10);
		boxWithdraw.setBounds(243, 953, 188, 20);
		boxWithdraw.setDocument(new JTextFieldLimit(255));
		add(boxWithdraw);
		
		JButton button = new JButton("Clear fields");
		button.setBounds(10, 402, 124, 23);
		add(button);
		
		JButton button_1 = new JButton("Clear fields");
		button_1.setBounds(144, 402, 124, 23);
		add(button_1);
		
		JButton button_2 = new JButton("->");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button_2.setBounds(617, 508, 57, 23);
		add(button_2);
		
		JButton button_3 = new JButton("<-");
		button_3.setBounds(617, 577, 57, 23);
		add(button_3);
	}
}
