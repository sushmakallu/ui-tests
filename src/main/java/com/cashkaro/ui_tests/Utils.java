package com.cashkaro.ui_tests;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Utils {

	public static JSONObject readContentFromJson(String fileName, String data) {
		JSONParser parser = new JSONParser();
		JSONObject dataObject = null;
		try {
			JSONObject obj = (JSONObject) parser
					.parse(new FileReader(fileName));
			dataObject = (JSONObject) obj.get(data);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dataObject;
	}
}
