package com.vabank.atm;

import org.json.simple.JSONObject;

public class ConTest {
	public static void main() throws Exception {
		
		JSONObject jsonObj = UrlConnector.getData("cardnum_existance.php?card_num=1111222233334444");
		Boolean id = (Boolean) jsonObj.get("cardnum_exists");
		
		
		System.out.println(id);
	}
}
