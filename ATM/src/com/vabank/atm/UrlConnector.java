package com.vabank.atm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class UrlConnector {
	
	//configuration
	private static String url = "http://niddua.tk/";
	
	private UrlConnector() {
	}
	
	public static JSONObject getData(String link)  {
		String urlstr = url + link;
		URL obj;
		try {
			obj = new URL(urlstr);
			HttpURLConnection con;
			con = (HttpURLConnection) obj.openConnection();
			con.setRequestMethod("GET");
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine = in.readLine();

			//while ((inputLine = in.readLine()) != null)
			//System.out.println(inputLine);

			JSONParser parser = new JSONParser();

			Object obj2 = parser.parse(inputLine);
			JSONObject jsonObj = (JSONObject) obj2;
			//System.out.println(jsonObj.get("paramsStr"));
			// some string
			
			in.close();
			
			
			return jsonObj;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	private static JSONParser parser = new JSONParser();
}