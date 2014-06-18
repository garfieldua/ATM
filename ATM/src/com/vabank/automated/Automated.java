package com.vabank.automated;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.Timer;

import com.vabank.admin.Database;

public class Automated {

	
	static ActionListener updateClockAction = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			System.out.println("tick..");
			
			ResultSet resultSet = Database.getInstance().execute(
					"SELECT ident_code, card_number, salary, last_paid FROM `employees`");
			
			try {
				while (resultSet.next()) {
					//System.out.println(resultSet.getInt(3));
					int told_salary = resultSet.getInt(3);
					String ident_code = resultSet.getString(1);
					String card_number = resultSet.getString(2);
					Date last_paid = resultSet.getDate(4);
					
					
					Calendar cal = Calendar.getInstance();
					
					int day = cal.get(Calendar.DAY_OF_MONTH);
					if (day == 18) {
						//let's pay, if not yet paid
						java.util.Date utilDate = cal.getTime();
						Date date_now = new Date(utilDate.getTime());
						
						if (! (date_now.getDay() == last_paid.getDay() && date_now.getMonth() == last_paid.getMonth() && date_now.getYear() == last_paid.getYear() )) {
							//fine, we can pay
							
							ResultSet resultSet2 = Database.getInstance().execute(
									"SELECT payment_fee, funds,taxes FROM `legal_person` WHERE ident_code = '" + ident_code + "'");
							
							ResultSet resultSet3 = Database.getInstance().execute(
									"SELECT balance FROM `natural_person` WHERE card_number = '" + card_number + "'");
							
							
							if (resultSet2.next() && resultSet3.next()) {
								double payment_fee = resultSet2.getDouble(1);
								//int funds = resultSet2.getInt(2);
								//int curtaxes = resultSet2.getInt(3);
								
								//int balance = resultSet3.getInt(1);
								double funds = resultSet2.getDouble(2);
								double curtaxes = resultSet2.getDouble(3);
								
								double balance = resultSet3.getDouble(1);

								//total update database
								
								//natural person
								//int newbalance = (int) (balance + told_salary * (1 - payment_fee));
								double newbalance = balance + told_salary * (1 - payment_fee);
								ResultSet resultSet4 = Database.getInstance().execute(
										"UPDATE natural_person" + " SET balance = " + newbalance +
												" WHERE card_number = '" + card_number + "'");
								
								//employee
								SimpleDateFormat f1 = new SimpleDateFormat("yyyy-MM-dd");
								String st = f1.format(date_now);
								
								ResultSet resultSet5 = Database.getInstance().execute(
										"UPDATE employees" + " SET last_paid = '" + st +
												"' WHERE card_number = '" + card_number + "' AND ident_code = '" + ident_code + "'");
								
								// legal person
								//int newfunds = funds - told_salary;
								//int newtaxes = (int) (curtaxes + told_salary * payment_fee);
								double newfunds = funds - told_salary;
								double newtaxes = curtaxes + told_salary * payment_fee;
								
								ResultSet resultSet6 = Database.getInstance().execute(
										"UPDATE legal_person" + " SET funds = " + newfunds + ", taxes = " + newtaxes +
												" WHERE ident_code = '" + ident_code + "'");
								
								//gj
								
								SimpleDateFormat f2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
								String st2 = f2.format(date_now);
								
								AutoMainView.logWrite(st2 + " - Payment from " + ident_code + " to " + card_number + ", amount: " + told_salary );
								System.out.println("paid!");
								
							}
							
						}
					}
					
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	};
	
	public static void start() {
		Database.getInstance();
		
		//start the timer
		Timer t = new Timer(1000, updateClockAction);
		t.start();	
	}
	

}
