package com.exercise3;

import java.util.Date;
import java.util.GregorianCalendar;

public class CalendarComparer {
	/**
	 * The epoch we are living in. I wish we could live in the epoch of beiber.
	 * It's too bad that we can't change the epoch, though conveniently enough 
	 * 1970 is 2 years before a leap year, just like 1994
	 */
	private static final int MILLIS_IN_SECOND = 1000;
	private static final int SECONDS_IN_MINUTE = 60;
	private static final int MINUTES_IN_HOUR = 60;
	private static final int HOURS_IN_DAY = 24;
	
	private GregorianCalendar bieber;
	private GregorianCalendar today;

	
	public CalendarComparer(){
		bieber = new GregorianCalendar(1994, 2, 1);
		today = new GregorianCalendar();
	}
	
	public int compareValues(){
		Date biebDate = bieber.getTime();
		Date todayDate = today.getTime();
		long difference = todayDate.getTime() - biebDate.getTime();
		
		todayDate.setTime(difference);
		System.out.println(todayDate.getTime());
		long daysCalc= todayDate.getTime()/MILLIS_IN_SECOND;
		daysCalc= daysCalc/SECONDS_IN_MINUTE;
		daysCalc=daysCalc/MINUTES_IN_HOUR;
		daysCalc=daysCalc/HOURS_IN_DAY;
		return (int) daysCalc; 
		
		}
	
}
