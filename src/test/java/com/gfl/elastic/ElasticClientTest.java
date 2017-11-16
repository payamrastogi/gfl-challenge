package com.gfl.elastic;

import java.io.File;
import java.io.IOException;
import org.junit.Test;
import com.gfl.elastic.model.ElasticSearch;
import com.gfl.elastic.model.ElasticSearchResponse;
import com.gfl.util.Config;

public class ElasticClientTest
{
	@Test
	public void test()
	{
		File file = new File("src/main/resources/config.properties");
		//File file = new File("src/main/resources/config.properties");
		if (!file.exists()) {
			System.out.println("config.properties not found @" + file.getAbsolutePath());
			System.exit(1);
		}
		Config config;
		try
		{
			config = new Config(file);
			ElasticClient e = new ElasticClient(config);
			ElasticSearch elasticSearch = e.createClient(config.getElasticSearchUrl());
			ElasticSearchResponse response = e.getResponse(elasticSearch, config.getElasticIndexName(), "stopCode:51130");
			System.out.println(response.hits.hits.get(0).source.agency);
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
	}
}
