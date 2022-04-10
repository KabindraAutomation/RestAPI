package com.qa.test;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.com.restapitest.restapi.TestBase;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.client.RestClient;
import com.qa.util.TestUtil;

public class GetApiTest extends TestBase {
	TestBase testBase;
	String serviceurl;
	String apiurl;
	String url;

	RestClient restclient;
	CloseableHttpResponse closeablehttpresponse;

	@BeforeMethod
	public void setUp() throws ClientProtocolException, IOException {
		testBase = new TestBase();
		serviceurl = prop.getProperty("URL");
		apiurl = prop.getProperty("serviceURL"); // https://reqres.in/api/users
		url = serviceurl + apiurl;

	}

	@Test(priority = 1)
	public void getAPITestwithoutHeader() throws ClientProtocolException, IOException {

		restclient = new RestClient();
		closeablehttpresponse = restclient.get(url);

		// a. Status Code: to geth status code
		int statusCdoe = closeablehttpresponse.getStatusLine().getStatusCode();
		System.out.println("Status Code displaying " + statusCdoe);

		Assert.assertEquals(statusCdoe, RESPONSE_STATUS_CODE_200, "Status Code is not 200");

		// b. To get the JSON String used==> EntityUtils class
		String responeString = EntityUtils.toString(closeablehttpresponse.getEntity(), "UTF-8");

		// Now we need to convert string into JSON for that we can use JSONObject class
		JSONObject responseJson = new JSONObject(responeString);
		System.out.println("Response JSON from API " + responseJson);

		// These are for single value assertion:
		// per_ page
		String perPageValue = TestUtil.getValueByJpath(responseJson, "/per_page");
		System.out.println("Value of per Page is -->" + perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 6);

		// total
		String totalValue = TestUtil.getValueByJpath(responseJson, "/total");
		System.out.println("Value of total is  -->" + totalValue);
		Assert.assertEquals(Integer.parseInt(totalValue), 12);

		// get the value from JSON Array:

		String lastname = TestUtil.getValueByJpath(responseJson, "/data[0]/last_name");
		
		 String id = TestUtil.getValueByJpath(responseJson,"/data[0]/id"); 
		 String firstname = TestUtil.getValueByJpath(responseJson,"/data[0]/first_name");
		  String avatar = TestUtil.getValueByJpath(responseJson,"/data[0]/avatar");
		  String email = TestUtil.getValueByJpath(responseJson,"/data[0]/email");
		 

		System.out.println(lastname);
		
		  System.out.println(id); System.out.println(firstname);
		  System.out.println(avatar); System.out.println(email);
		

		// C.All Header we are getting
		Header[] headerArray = closeablehttpresponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		for (Header header : headerArray) {
			allHeaders.put(header.getName(), header.getValue());

		}
		System.out.println("Header Array " + allHeaders);
	}
	@Test
	public void getAPITestwithHeader() throws ClientProtocolException, IOException {

		restclient = new RestClient();
		
		HashMap<String, String> headerMap = new HashMap<String, String> (); 
		headerMap.put("Content- Type",  "aaplication/json");
//		headerMap.put("username", "test");
//		headerMap.put("password", "test123");
		
		closeablehttpresponse = restclient.get(url, headerMap);

		// a. Status Code: to geth status code
		int statusCdoe = closeablehttpresponse.getStatusLine().getStatusCode();
		System.out.println("Status Code displaying " + statusCdoe);

		Assert.assertEquals(statusCdoe, RESPONSE_STATUS_CODE_200, "Status Code is not 200");

		// b. To get the JSON String used==> EntityUtils class
		String responeString = EntityUtils.toString(closeablehttpresponse.getEntity(), "UTF-8");

		// Now we need to convert string into JSON for that we can use JSONObject class
		JSONObject responseJson = new JSONObject(responeString);
		System.out.println("Response JSON from API " + responseJson);

		// These are for single value assertion:
		// per_ page
		String perPageValue = TestUtil.getValueByJpath(responseJson, "/per_page");
		System.out.println("Value of per Page is -->" + perPageValue);
		Assert.assertEquals(Integer.parseInt(perPageValue), 6);

		// total
		String totalValue = TestUtil.getValueByJpath(responseJson, "/total");
		System.out.println("Value of total is  -->" + totalValue);
		Assert.assertEquals(Integer.parseInt(totalValue), 12);

		// get the value from JSON Array:

		String lastname = TestUtil.getValueByJpath(responseJson, "/data[0]/last_name");
		
		 String id = TestUtil.getValueByJpath(responseJson,"/data[0]/id"); 
		 String firstname = TestUtil.getValueByJpath(responseJson,"/data[0]/first_name");
		  String avatar = TestUtil.getValueByJpath(responseJson,"/data[0]/avatar");
		  String email = TestUtil.getValueByJpath(responseJson,"/data[0]/email");
		 

		System.out.println(lastname);
		
		  System.out.println(id); System.out.println(firstname);
		  System.out.println(avatar); System.out.println(email);
		

		// C.All Header we are getting
		Header[] headerArray = closeablehttpresponse.getAllHeaders();
		HashMap<String, String> allHeaders = new HashMap<String, String>();
		for (Header header : headerArray) {
			allHeaders.put(header.getName(), header.getValue());

		}
		System.out.println("Header Array " + allHeaders);

	}
}
