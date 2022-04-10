package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class RestClient {

	// 1. GET Method without Header:
	public CloseableHttpResponse get(String url) throws ClientProtocolException, IOException {

		CloseableHttpClient httpClient = HttpClients.createDefault();// CreateDefault method will create one client
																		// connection
		HttpGet httpget = new HttpGet(url); // http get request
		CloseableHttpResponse closeablehttpresponse = httpClient.execute(httpget); // it will what is your request-->
																					// httpget is our request (hit the
																					// GET URL)

		return closeablehttpresponse;

	}

	// 2. GET Method with Header:
	public CloseableHttpResponse get(String url,HashMap<String , String> headerMap) throws ClientProtocolException, IOException {
		
		CloseableHttpClient httpClient = HttpClients.createDefault();// CreateDefault method will create one client connection 
		HttpGet httpget = new  HttpGet(url); //http get request
		
		for(Map.Entry<String, String> entry : headerMap.entrySet()){
			httpget.addHeader(entry.getKey(), entry.getValue()); 
			
		}
		
		CloseableHttpResponse closeablehttpresponse = httpClient.execute(httpget); // it will what is your request--> httpget is our request (hit the GET URL)
				
		return closeablehttpresponse;
	
	}
	
	//POST Method:
	public void post(String url, String entityString, HashMap<String, String> headerMap) {
		CloseableHttpClient httpPostClient = HttpClients.createDefault();
		HttpPost httpost = new HttpPost(url); //For Post Reuest
		
	}
	
}
