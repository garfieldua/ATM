package com.vabank.automated;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

public class AutoMainView extends JFrame {

	private JPanel contentPane;
	private static JTextArea textArea = new JTextArea();

	public static void logWrite(String s) {
		textArea.append(s + "\n");

		Calendar cal = Calendar.getInstance();
		java.util.Date utilDate = cal.getTime();
		Date date_now = new Date(utilDate.getTime());

		SimpleDateFormat f2 = new SimpleDateFormat("yyyy-MM-dd");
		String st2 = f2.format(date_now);

		String path = "C:\\" + st2 + ".txt";
		File file = new File(path);
		// FileWriter second argument is for append if its true than FileWritter
		// will
		// write bytes at the end of File (append) rather than beginning of file
		try {
			FileWriter fileWriter = new FileWriter(file, true);

			// Use BufferedWriter instead of FileWriter for better performance
			BufferedWriter bufferFileWriter = new BufferedWriter(fileWriter);

			fileWriter.append(s + String.format("%n"));

			// Don't forget to close Streams or Reader to free FileDescriptor
			// associated with it
			bufferFileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AutoMainView frame = new AutoMainView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AutoMainView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		Automated.start();
		contentPane.setLayout(null);

		textArea.setEditable(false);
		textArea.setBounds(5, 5, 609, 406);
		contentPane.add(textArea);

		JScrollPane scroll = new JScrollPane(textArea);
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setBounds(5, 5, 609, 406);
		contentPane.add(scroll);

		logWrite("Successfully connected to DB!");
	}

}
