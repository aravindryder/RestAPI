package com.RestAPI;

import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Data_Vaild {
	
	@Test
	public void DataVaild() {
		
		RestAssured.baseURI = "https://reqres.in/";
		RequestSpecification req = RestAssured.given();
		Response res = req.get("api/users/2");
		String sour = res.asString();
		System.out.println(sour);
		int code = res.statusCode();
		System.out.println("Status Code: " + code);
	}
}