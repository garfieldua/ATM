package com.vabank.atm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ConTest {
	public static void main() throws IOException {
		URL obj = new URL("http://niddua.tk/index.php");
		HttpURLConnection con;
		con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine = in.readLine();

		//while ((inputLine = in.readLine()) != null)
		//System.out.println(inputLine);

		JSONParser parser = new JSONParser();

		Object obj = parser.parse(json);
		JSONObject jsonObj = (JSONObject) obj;
		System.out.println(jsonObj.get("paramsStr"));
		// some string

		JsonObject jo = jsonObj.get("paramsObj");
		System.out.println(jo.get("three"));
		
		
		in.close();
	}
}
