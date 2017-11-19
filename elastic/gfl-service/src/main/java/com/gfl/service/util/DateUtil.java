package com.gfl.service.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.gfl.commons.exception.DateTimeFormatException;

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
	
	/**
	 * @param timestamp Timestamp to be formatted in 12 hours format
	 * @return String representing time in 12 hour format eg. 6:15 AM
	 * @throws DateTimeFormatException
	 */
	public static String getTimeIn12HrFormat(String timestamp) throws DateTimeFormatException
	{
		Pattern pattern = Pattern.compile("T(\\d{2}):(\\d{2}):?.*");
		Matcher matcher = pattern.matcher(timestamp);
		String hr = null;
		String min = null;
		if(matcher.find())
		{
			logger.debug(matcher.group());
			hr  = matcher.group(1);
			min = matcher.group(2);
			logger.debug(hr + " : "+min);
		}
		else
		{
			throw new DateTimeFormatException("Unsupported DateTime format");
		}
		
		int hour = Integer.parseInt(hr);
		int minutes = Integer.parseInt(min);
		logger.debug(hour+":"+minutes);
		String ampm = "AM";
		if(hour > 12)
		{
			hour -= 12;
			ampm = "PM";
		}
		else if(hour==12)
		{
			ampm = "PM";
		}
		else if(hour==0)
		{
			hour = 12;
		}
		StringBuilder sb = new StringBuilder()
				.append(hour)
				.append(":")
				.append(minutes)
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
