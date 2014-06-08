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

public class ConTest {
	public static void main() throws Exception {
		
		JSONObject jsonObj = UrlConnector.getData("cardnum_existance.php?card_num=1111222233334444");
		Boolean id = (Boolean) jsonObj.get("cardnum_exists");
		
		
		System.out.println(id);
	}
}
