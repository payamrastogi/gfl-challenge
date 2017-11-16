package com.gfl.sfbay;

import java.io.IOException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class StopMonitoringHttpClientTest
{
	@Test
	public void test()
	{
		HttpClient client = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet("https://api.511.org/transit/StopMonitoring?api_key=a305337b-9f85-4d25-97d4-836b57ff0f17&agency=AC&format=json&stopCode=50118");

        try {
            HttpResponse response = client.execute(request);
            HttpEntity entity = response.getEntity();

            // Read the contents of an entity and return it as a String.
            String content = EntityUtils.toString(entity);
            System.out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
