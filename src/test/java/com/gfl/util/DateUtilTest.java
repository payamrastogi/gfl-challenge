package com.gfl.util;

import org.junit.Test;

public class DateUtilTest
{
	@Test
	public void test()
	{
		System.out.println(DateUtil.getDate("2017-11-16T15:14:00Z"));
		System.out.println(DateUtil.getTime("2017-11-16T15:14:00Z"));
		System.out.println(DateUtil.getTimeIn12HrFormat("2017-11-16T15:14:00Z"));
		System.out.println(DateUtil.getTimeIn12HrFormat("2017-11-16T12:14:00Z"));
		System.out.println(DateUtil.getTimeIn12HrFormat("2017-11-16T01:14:00Z"));
	}
}
