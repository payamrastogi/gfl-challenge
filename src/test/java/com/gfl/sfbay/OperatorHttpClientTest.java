package com.gfl.sfbay;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class OperatorHttpClientTest
{
	@Test
	public void test()
	{
		HttpClient client = HttpClientBuilder.create().build();
	    HttpGet request = new HttpGet("https://api.511.org/transit/operators?api_key=a305337b-9f85-4d25-97d4-836b57ff0f17&format=json");
	
	    try {
	        HttpResponse response = client.execute(request);
	        HttpEntity entity = response.getEntity();
	
	        // Read the contents of an entity and return it as a String.
	        String content = EntityUtils.toString(entity);
	        JsonArray arr = new Gson().fromJson(content, JsonArray.class);
	        JsonObject obj = (JsonObject) arr.get(0);
	        System.out.println(obj.get("Id") + " " + obj.get("Name") + " " + obj.get("ContactTelephoneNumber"));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}
