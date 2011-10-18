package com.exercise3;

import java.util.Date;
import java.util.GregorianCalendar;

public class CalendarComparer {
	/**
	 * This class does 1 thing and 1 thing only.
	 * It compares today's date to the day Justin Beiber
	 *  was born.  
	 */
	private static final int MILLIS_IN_SECOND = 1000;
	private static final int SECONDS_IN_MINUTE = 60;
	private static final int MINUTES_IN_HOUR = 60;
	private static final int HOURS_IN_DAY = 24;
	
	private GregorianCalendar bieber; 
	private GregorianCalendar today;

	/**
	 * Constructs the object with the date on which you constructed
	 * it. 
	 */
	public CalendarComparer(){
		bieber = new GregorianCalendar(1994, 2, 1);
		today = new GregorianCalendar();
	}
	/**
	 * compares today's date to his date
	 * @return the number of days between today and
	 * his birthday
	 */
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
	//flute
}
