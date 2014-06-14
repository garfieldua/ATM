package com.vabank.admin;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.toedter.calendar.JDateChooser;
import com.vabank.atm.ATMView;
import com.vabank.atm.JTextFieldLimit;
import com.vabank.atm.UITemplates;

import javax.swing.JButton;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.TreeSet;

@SuppressWarnings("serial")
public class LegalPersonView extends JPanel {

	// all legal persons
	private JTable table;
	// all natural persons
	private JTable table2;
	// selected natural persons
	private JTable table3;

	private TreeSet<Employee> workerIds = new TreeSet<Employee>();

	private JTextField boxFullName;
	private JTextField boxShortName;
	private JTextField boxIdentCode;
	private JTextField boxFunds;
	private JTextField boxCertNum;
	private JTextField boxRegAuth;
	private JTextField boxAddress;
	private JTextField boxSalary;

	/**
	 * Create the panel.
	 * 
	 * @throws SQLException
	 */
	public LegalPersonView() throws SQLException {
		setLayout(null);

		// MainView.instance.setSize(1300, 800);

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
		btnExit.setBounds(585, 523, 200, 42);
		add(btnExit);

		// model for current workers
		final ListTableModel[] model21 = { null };

		final JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(605, 306, 158, 20);
		add(dateChooser);

		dateChooser.setDateFormatString("yyyy-MM-dd");

		// Filling legal person table
		ResultSet resultSet = Database.getInstance().execute(
				"SELECT full_name as FullName" + ", ident_code AS IdentCode"
						+ ", funds AS Funds" + ", address AS Address"
						+ ", num_cert_of_reg AS CertificateNumber"
						+ " FROM legal_person" + " ORDER BY ident_code");

		ListTableModel model = ListTableModel
				.createModelFromResultSet(resultSet);
		table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane jp = new JScrollPane(table);
		jp.setBounds(10, 60, 775, 145);
		add(jp);

		// Filling all natural person table
		ResultSet resultSet2 = Database.getInstance().execute(
				"SELECT name AS Name" + ", card_number AS CardNumber"
						+ ", ident_code AS IdentCode" + " FROM natural_person"
						+ " ORDER BY card_number");

		ListTableModel model2 = ListTableModel
				.createModelFromResultSet(resultSet2);
		table2 = new JTable(model2);
		table2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane jp2 = new JScrollPane(table2);
		jp2.setBounds(10, 367, 346, 145);
		add(jp2);

		// Filling "selected" natural person table. By now in should be empty
		// though
		ResultSet resultSet3 = Database.getInstance().execute(
				"SELECT name AS Name" + ", card_number AS CardNumber"
						+ ", ident_code AS IdentCode" + " FROM natural_person"
						+ " WHERE ident_code <> ident_code"
						+ " ORDER BY card_number");

		ListTableModel model3 = ListTableModel
				.createModelFromResultSet(resultSet3);
		table3 = new JTable(model3);
		table3.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane jp3 = new JScrollPane(table3);
		jp3.setBounds(439, 367, 346, 145);
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
													+ ", address AS Address"
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
								String shortname = resultSet2.getString(1);
								boxShortName.setText(shortname);

								String fullname = resultSet2.getString(2);
								boxFullName.setText(fullname);

								String idcode = resultSet2.getString(3);
								boxIdentCode.setText(idcode);

								String funds = resultSet2.getString(4);
								boxFunds.setText(funds);

								String address = resultSet2.getString(5);
								boxAddress.setText(address);

								String num_cert_of_reg = resultSet2
										.getString(6);
								boxCertNum.setText(num_cert_of_reg);

								String reg_cert_authority = resultSet2
										.getString(7);
								boxRegAuth.setText(reg_cert_authority);

								Date date = resultSet2.getDate(8);
								dateChooser.setDate(date);

								// System.out.println(idcode);

								// now, filling table3 with natural persons that
								// are working with on-clicked legal person
								ResultSet resultSet3 = Database
										.getInstance()
										.execute(
												"SELECT name AS Name"
														+ ", natural_person.card_number AS CardNumber"
														+ ", natural_person.ident_code AS IdentCode"
														+ " FROM natural_person INNER JOIN employees ON natural_person.card_number = employees.card_number"
														+ " WHERE employees.ident_code = '"
														+ idcode + "'"
														+ " ORDER BY name");

								model21[0] = ListTableModel
										.createModelFromResultSet(resultSet3);
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
														+ ", salary AS Salary"
														+ " FROM natural_person INNER JOIN employees ON natural_person.card_number = employees.card_number"
														+ " WHERE employees.ident_code = '"
														+ idcode + "'"
														+ " ORDER BY name");
								workerIds.clear();

								while (resultSet4.next()) {
									Employee temp = new Employee(resultSet4
											.getInt(6), resultSet4.getString(2));
									workerIds.add(temp);
								}
								// System.out.println(workerIds);

							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}

						}
					}
				});

		// Processing clicks on workers
		table3.getSelectionModel().addListSelectionListener(
				new ListSelectionListener() {
					@Override
					public void valueChanged(ListSelectionEvent e) {
						if (table3.getSelectedRow() >= 0) {
							int selectedrow = table3.getSelectedRow();

							Iterator<Employee> it = workerIds.iterator();
							int i = 0;
							Employee current = null;
							while (it.hasNext() && i <= selectedrow) {
								current = it.next();
								i++;
							}

							// System.out.println(current.cardNumber);

							boxSalary.setText(Integer.toString(current.salary));
						}
					}

				});
		/*
		 * table.getSelectionModel().addListSelectionListener( new
		 * ListSelectionListener() {
		 * 
		 * @Override public void valueChanged(ListSelectionEvent e) { if
		 * (table.getSelectedRow() >= 0) { ResultSet resultSet2 = Database
		 * .getInstance() .execute( "SELECT name AS Name" + ", card_number" +
		 * ", phone_number" + ", ident_code" + ", balance" + ", pin" +
		 * ", address" + ", withdrawal_limit" + " FROM natural_person" +
		 * " ORDER BY name");
		 * 
		 * 
		 * for (int i = 0; i < table.getSelectedRow() + 1; i++) { try {
		 * resultSet2.next(); } catch (SQLException e1) { // TODO Auto-generated
		 * catch block e1.printStackTrace(); } }
		 * 
		 * //
		 * 
		 * try { String name = resultSet2.getString(1); boxName.setText(name);
		 * 
		 * String card_number = resultSet2.getString(2);
		 * boxCardNumber.setText(card_number);
		 * 
		 * String phone_number = resultSet2.getString(3);
		 * boxPhone.setText(phone_number);
		 * 
		 * String ident_code = resultSet2.getString(4);
		 * boxIdent.setText(ident_code);
		 * 
		 * String balance = resultSet2.getString(5);
		 * boxBalance.setText(balance);
		 * 
		 * String pin = resultSet2.getString(6); boxPin.setText(pin);
		 * 
		 * String address = resultSet2.getString(7);
		 * boxAddress.setText(address);
		 * 
		 * String withdrawal_limit = resultSet2.getString(8);
		 * boxWithdraw.setText(withdrawal_limit);
		 * 
		 * } catch (SQLException e1) { // TODO Auto-generated catch block
		 * e1.printStackTrace(); }
		 * 
		 * } } });
		 */
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int reply = JOptionPane.showConfirmDialog(MainView.instance,
						"Do you really want to delete selected legal person?",
						"Confirm your action", JOptionPane.YES_NO_OPTION);
				if (reply == JOptionPane.YES_OPTION) {
					if (table.getSelectedRow() >= 0) {
						ResultSet resultSet2 = Database
								.getInstance()
								.execute(
										"SELECT full_name as FullName"
												+ ", ident_code AS IdentCode"
												+ ", funds AS Funds"
												+ ", address AS Address"
												+ ", num_cert_of_reg AS CertificateNumber"
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
							String code = resultSet2.getString(2);

							// statemenet needed
							ResultSet resultSet3 = Database.getInstance()
									.execute(
											"DELETE" + " FROM legal_person"
													+ " WHERE ident_code = '"
													+ code + "'");

							ResultSet resultSet4 = Database
									.getInstance()
									.execute(
											"SELECT full_name as FullName"
													+ ", ident_code AS IdentCode"
													+ ", funds AS Funds"
													+ ", address AS Address"
													+ ", num_cert_of_reg AS CertificateNumber"
													+ " FROM legal_person"
													+ " ORDER BY ident_code");

							ListTableModel model2 = ListTableModel
									.createModelFromResultSet(resultSet4);
							table.setModel(model2);

						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						return;
					}
				}
			}
		});
		btnDelete.setBounds(696, 218, 89, 23);
		add(btnDelete);

		JButton btnClearFields = new JButton("Clear fields");
		btnClearFields.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boxFullName.setText("");
				boxShortName.setText("");
				boxIdentCode.setText("");
				boxFunds.setText("");
				boxCertNum.setText("");
				boxRegAuth.setText("");
				boxAddress.setText("");
				boxSalary.setText("");
			}
		});
		btnClearFields.setBounds(10, 216, 124, 23);
		add(btnClearFields);

		// Hiring natural persons
		JButton btnHire = new JButton("Hire");
		btnHire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (table2.getSelectedRow() >= 0) {
					ResultSet resultSet20 = Database.getInstance().execute(
							"SELECT name AS Name"
									+ ", card_number AS CardNumber"
									+ ", ident_code AS IdentCode"
									+ " FROM natural_person"
									+ " ORDER BY card_number");

					for (int i = 0; i < table2.getSelectedRow() + 1; i++) {
						try {
							resultSet20.next();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

					try {
						// System.out.println("===============");
						// System.out.println(resultSet20.getString(2));

						Employee temp = new Employee(Integer.parseInt(boxSalary
								.getText()), resultSet20.getString(2));
						workerIds.add(temp);

						// System.out.println("After adding:");
						// System.out.println(workerIds);
						// System.out.println("===============");

						ResultSet resultSet21 = Database.getInstance().execute(
								"SELECT name AS Name"
										+ ", card_number AS CardNumber"
										+ ", ident_code AS IdentCode"
										+ " FROM natural_person"
										+ " ORDER BY card_number");

						model21[0] = ListTableModel
								.createModelFromResultSet(resultSet21);

						ResultSet resultSet22 = Database.getInstance().execute(
								"SELECT name AS Name"
										+ ", card_number AS CardNumber"
										+ ", ident_code AS IdentCode"
										+ " FROM natural_person"
										+ " ORDER BY card_number");

						int curpos = 0;
						while (resultSet22.next()) {
							Employee temp2 = new Employee(0, resultSet22
									.getString(2));
							// System.out.println(temp2);
							if (!workerIds.contains(temp2)) {
								// System.out.println("here!");
								model21[0].removeRowRange(curpos, curpos);
								curpos--;
							}
							curpos++;
						}

						table3.setModel(model21[0]);

					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		});
		btnHire.setBounds(366, 452, 63, 23);
		add(btnHire);

		JButton btnFire = new JButton("Fire");
		btnFire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (table3.getSelectedRow() >= 0) {
					int selectedrow = table3.getSelectedRow();

					Iterator<Employee> it = workerIds.iterator();
					int i = 0;
					Employee current = null;
					while (it.hasNext() && i <= selectedrow) {
						current = it.next();
						i++;
					}

					workerIds.remove(current);

					try {
						ResultSet resultSet21 = Database.getInstance().execute(
								"SELECT name AS Name"
										+ ", card_number AS CardNumber"
										+ ", ident_code AS IdentCode"
										+ " FROM natural_person"
										+ " ORDER BY card_number");

						model21[0] = ListTableModel
								.createModelFromResultSet(resultSet21);

						ResultSet resultSet22 = Database.getInstance().execute(
								"SELECT name AS Name"
										+ ", card_number AS CardNumber"
										+ ", ident_code AS IdentCode"
										+ " FROM natural_person"
										+ " ORDER BY card_number");

						int curpos = 0;
						while (resultSet22.next()) {
							Employee temp = new Employee(0, resultSet22
									.getString(2));
							if (!workerIds.contains(temp)) {
								model21[0].removeRowRange(curpos, curpos);
								curpos--;
							}
							curpos++;
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					table3.setModel(model21[0]);
					// System.out.println("============================");
					// System.out.println(workerIds);
				}
			}
		});
		btnFire.setBounds(439, 523, 57, 23);
		add(btnFire);

		JLabel lblFullName = new JLabel("Full name");
		lblFullName.setBounds(10, 247, 124, 14);
		add(lblFullName);

		boxFullName = new JTextField();
		boxFullName.setBounds(10, 261, 158, 20);
		add(boxFullName);
		boxFullName.setColumns(10);

		JLabel lblShortName = new JLabel("Short name");
		lblShortName.setBounds(10, 292, 124, 14);
		add(lblShortName);

		boxShortName = new JTextField();
		boxShortName.setColumns(10);
		boxShortName.setBounds(10, 306, 158, 20);
		add(boxShortName);

		JLabel lblIdentCode = new JLabel("Ident code");
		lblIdentCode.setBounds(207, 247, 124, 14);
		add(lblIdentCode);

		boxIdentCode = new JTextField();
		boxIdentCode.setColumns(10);
		boxIdentCode.setBounds(207, 261, 158, 20);
		add(boxIdentCode);

		JLabel lblFunds = new JLabel("Funds");
		lblFunds.setBounds(207, 292, 124, 14);
		add(lblFunds);

		boxFunds = new JTextField();
		boxFunds.setColumns(10);
		boxFunds.setBounds(207, 306, 158, 20);
		add(boxFunds);

		JLabel lblCertificate = new JLabel("Certificate \u2116");
		lblCertificate.setBounds(404, 247, 124, 14);
		add(lblCertificate);

		boxCertNum = new JTextField();
		boxCertNum.setColumns(10);
		boxCertNum.setBounds(404, 261, 158, 20);
		add(boxCertNum);

		JLabel lblRegistrationAuthority = new JLabel("Registration authority");
		lblRegistrationAuthority.setBounds(404, 292, 158, 14);
		add(lblRegistrationAuthority);

		boxRegAuth = new JTextField();
		boxRegAuth.setColumns(10);
		boxRegAuth.setBounds(404, 306, 158, 20);
		add(boxRegAuth);

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(605, 247, 124, 14);
		add(lblAddress);

		boxAddress = new JTextField();
		boxAddress.setColumns(10);
		boxAddress.setBounds(605, 261, 158, 20);
		add(boxAddress);

		JLabel lblDateOfRegistration = new JLabel("Date of registration");
		lblDateOfRegistration.setBounds(605, 292, 124, 14);
		add(lblDateOfRegistration);

		JLabel lblSalary = new JLabel("Salary");
		lblSalary.setBounds(366, 397, 63, 14);
		add(lblSalary);

		boxSalary = new JTextField();
		boxSalary.setColumns(10);
		boxSalary.setBounds(366, 421, 63, 20);
		add(boxSalary);

		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnUpdate.setBounds(10, 523, 89, 23);
		add(btnUpdate);

		JButton btnCreate = new JButton("Create");
		btnCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				java.util.Date date = dateChooser.getDate();
				SimpleDateFormat f1 = new SimpleDateFormat("yyyy-MM-dd");
				String st = f1.format(date);
				
				ResultSet resultSet3 = Database
						.getInstance()
						.execute(
								"INSERT INTO legal_person (ident_code, full_name, short_name, funds, address, num_cert_of_reg, reg_cert_authority, date_cert)"
										+ " VALUES ('"
										+ boxIdentCode.getText()
										+ "', '"
										+ boxFullName.getText()
										+ "', '"
										+ boxShortName.getText()
										+ "', "
										+ boxFunds.getText()
										+ ", '"
										+ boxAddress.getText()
										+ "', '"
										+ boxCertNum.getText()
										+ "', '"
										+ boxRegAuth.getText()
										+ "', '"
										+ st + "')");

				
				Iterator<Employee> it = workerIds.iterator();
				int i = 0;
				Employee current = null;

				while (it.hasNext()) {
					current = it.next();
					i++;

					ResultSet resultSet2 = Database.getInstance().execute(
							"INSERT INTO employees (ident_code, card_number, salary)"
									+ " VALUES ('" + boxIdentCode.getText()
									+ "', '" + current.cardNumber + "', "
									+ current.salary + ")");
				}

				ResultSet resultSet4 = Database.getInstance().execute(
						"SELECT full_name as FullName" + ", ident_code AS IdentCode"
								+ ", funds AS Funds" + ", address AS Address"
								+ ", num_cert_of_reg AS CertificateNumber"
								+ " FROM legal_person" + " ORDER BY ident_code");

				ListTableModel model3;
				try {
					model3 = ListTableModel
							.createModelFromResultSet(resultSet4);

					table.setModel(model3);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnCreate.setBounds(109, 523, 89, 23);
		add(btnCreate);
	}
}
