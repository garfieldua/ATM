package com.vabank.atm;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class UITemplates {
	
	public static JLabel atmLogo;
	public static JLabel atmTime;
	
	static ActionListener updateClockAction = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String timeStamp = new SimpleDateFormat("HH:mm")
					.format(Calendar.getInstance().getTime());
			atmTime.setText(timeStamp);
		}
	};
	
	static {
		// setting up logo
		String atmName = "VA Bank";
		atmLogo = new JLabel(atmName);
		atmLogo.setForeground(new Color(0, 0, 255));
		atmLogo.setFont(new Font("Tahoma", Font.PLAIN, 26));
		atmLogo.setBounds(10, 11, 134, 32);
		
		// setting up timer
		String timeStamp = new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime());
		atmTime = new JLabel(timeStamp);
		atmTime.setHorizontalAlignment(SwingConstants.RIGHT);
		atmTime.setFont(new Font("Tahoma", Font.PLAIN, 26));
		atmTime.setBounds(640, 11, 134, 32);
		Timer t = new Timer(1000, updateClockAction);
		t.start();
	}
	
	// factory for all user interface buttons
	public static JButton createUIButton(String str) {
		JButton toreturn = new JButton(str);
		toreturn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		return toreturn;
	}
	
	/*
	public static JLabel createTextMenu(String str){
		JLabel toreturn = new JLabel(str);
		return toreturn;
	}
	*/
}
