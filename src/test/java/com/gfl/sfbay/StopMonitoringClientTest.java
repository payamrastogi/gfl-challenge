package com.gfl.sfbay;

import java.io.File;
import java.io.IOException;
import org.junit.Test;
import com.gfl.sbay.model.StopMonitoringResponse;
import com.gfl.sbay.model.StopMonitoringSearch;
import com.gfl.util.Config;

public class StopMonitoringClientTest
{
	@Test
	public void test()
	{
		File file = new File("src/main/resources/config.properties");
		if (!file.exists()) {
			System.out.println("config.properties not found @" + file.getAbsolutePath());
			System.exit(1);
		}
		Config config;
		try
		{
			config = new Config(file);
			StopMonitoringClient e = new StopMonitoringClient(config);
			StopMonitoringSearch search = e.createClient(config.getSfBayUrl());
			StopMonitoringResponse response = e.getResponse(search);
			System.out.println(response.serviceDelivery.producerRef+ " ");
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
	}
}
