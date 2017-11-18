package com.gfl.client.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtil
{
	private static Logger logger = LoggerFactory.getLogger(DateUtil.class);
	//2017-11-16T15:14:00Z
	public static String getDate(String timestamp)
	{
		if(timestamp.indexOf('T')<0)
			return null;
			
		String[] arr = timestamp.split("T");
		String date = arr[0];
		
		return date;
	}
	
	public static String getTime(String timestamp)
	{
		if(timestamp.indexOf('T')<0)
			return null;
			
		String[] arr = timestamp.split("T");
		String time = arr[1].split("Z")[0];
		
		return time;
	}
	
	public static String getTimeIn12HrFormat(String timestamp)
	{
		if(timestamp.indexOf('T')<0)
			return null;
			
		String[] arr = timestamp.split("T");
		String time = arr[1].split("Z")[0];
		if(time.indexOf(':')<0)
			return null;
		
		String[] timeArray = time.split(":");
		if(timeArray.length!=2 && timeArray.length!=3)
		{
			logger.debug(timeArray.length+"");
			return null;
		}
			
		for(String s : timeArray)
		{
			if(!isNumeric(s))
				return null;
		}
		int hr = Integer.parseInt(timeArray[0]);
		logger.debug(hr+"");
		String ampm = "AM";
		if(hr > 12)
		{
			
			hr -= 12;
			ampm = "PM";
		}
		else if(hr==12)
		{
			ampm = "PM";
		}
		else if(hr==0)
		{
			hr = 12;
		}
		StringBuilder sb = new StringBuilder()
				.append(hr)
				.append(":")
				.append(timeArray[1])
				.append(" ")
				.append(ampm);
		return sb.toString();
	}
	
	public static boolean isNumeric(String str)
	{
	    for (char c : str.toCharArray())
	    {
	        if (!Character.isDigit(c)) return false;
	    }
	    return true;
	}
	
}
