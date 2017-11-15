package com.gfl.controller;
import static spark.Spark.*;
import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
public class Slack511Controller
{
	public static void main(String args[])
	{
		get("/slack511", (req, res) -> {
			//System.out.println("==================================================================");
			//System.out.println(req.attribute("response_url").toString());
			//System.out.println("==================================================================");
			//return createClient();
			return createClient(req.raw().getParameter("response_url"), req.raw().getParameter("text"));			
		});
	}
	
	public static String createClient(String url, String text)
	{
		HttpClient client = HttpClientBuilder.create().build();
        HttpPost request = new HttpPost(url);
        
        try {
        		JsonObject j = new JsonObject();
        		j.addProperty("response_type", "in_channel");
        		j.addProperty("text", "Hello Meqo"+ text);
        		
        		StringEntity params =new StringEntity(j.toString());
        		request.addHeader("content-type", "application/json");
        		request.setEntity(params);
            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();

            // Read the contents of an entity and return it as a String.
            String content = EntityUtils.toString(entity);
            System.out.println("=============================================================");
            System.out.println(content);
            System.out.println("=============================================================");
            //JsonArray arr = new Gson().fromJson(content, JsonArray.class);
           //JsonObject obj = (JsonObject) arr.get(0);
            //System.out.println(obj.get("Id") + " " + obj.get("Name") + " " + obj.get("ContactTelephoneNumber"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "Hello World";
	}
}
