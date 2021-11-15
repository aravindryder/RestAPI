package com.RestAPI;

import java.io.File;
import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class PutAPI {

	public static void main(String[] args) throws Throwable {

		File f = new File(
				"C:\\Users\\Flynn Ryder\\eclipse-workspace\\RestAPI\\src\\test\\java\\JsonDetails\\Details.Json");

		FileReader fr = new FileReader(f);

		JSONParser jp = new JSONParser();

		Object parse = jp.parse(fr);

		JSONObject job = (JSONObject) parse; // narrow casting

		Object object = job.get("name");

		String string = object.toString();

		System.out.println("Name: " + string);

		Object object1 = job.get("name");

		System.out.println("\nJob: " + object1.toString());

		Object object2 = job.get("email");

		System.out.println("\nID: " + object2.toString());

		Object object3 = job.get("address");

		System.out.println("\nLocation: " + object3.toString());
	}
}