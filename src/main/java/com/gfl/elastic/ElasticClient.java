package com.gfl.elastic;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ElasticClient
{
	public static void main(String args[])
	{
		ElasticClient e = new ElasticClient();
		e.createClient();
	}
	
	public void createClient()
	{
		HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet("https://elasticsearch-rest.greenfieldlabs.io/transit-stops/_search?q=stopCode:51130");

        try {
            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();

            // Read the contents of an entity and return it as a String.
            String content = EntityUtils.toString(entity);
            JsonObject obj = new Gson().fromJson(content, JsonObject.class);
            JsonObject hits = (JsonObject)obj.get("hits");
            JsonArray hitsArray = (JsonArray)hits.get("hits");
            JsonObject o = (JsonObject)hitsArray.get(0);
            JsonObject src = (JsonObject) o.get("_source");
            System.out.println(src.get("agency").getAsString() + " " + src.get("id") + " " + src.get("stopCode"));
            
            System.out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
