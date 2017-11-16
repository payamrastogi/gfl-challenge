package com.gfl.sfbay;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.junit.Test;
import com.gfl.sbay.model.OperatorResponse;
import com.gfl.sbay.model.OperatorSearch;
import com.gfl.util.Config;

public class OperatorClientTest
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
			OperatorsClient e = new OperatorsClient(config);
			OperatorSearch operatorSearch = e.createClient(config.getSfBayUrl());
			List<OperatorResponse> response = e.getResponse(operatorSearch);
			System.out.println(response.get(0).name+ " " + response.get(0).shortName);
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
	}
}
