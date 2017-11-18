package com.gfl.service.util;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gfl.commons.exception.DateTimeFormatException;
public class DataUtilTest 
{
	private static Logger logger = LoggerFactory.getLogger(DataUtilTest.class);
	@Test
	public void Test() throws DateTimeFormatException
	{
		logger.debug(DateUtil.getTimeIn12HrFormat("2017-11-16T15:14:00Z"));
		logger.debug(DateUtil.getTimeIn12HrFormat("2017-11-16T15:14Z"));
	}
}
