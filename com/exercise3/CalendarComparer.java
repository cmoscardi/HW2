package com.exercise3;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class CalendarComparer {
	/**
	 * The epoch we are living in. I wish we could live in the epoch of beiber.
	 * It's too bad that we can't change the epoch, though conveniently enough 
	 * 1970 is 2 years before a leap year, just like 1994
	 */
	public static final int EPOCH=1970;
	private static final int DAYS_IN_YEAR=365;
	private static final int DAYS_TILL_BDAY=31+28;
	private GregorianCalendar bieber;
	private GregorianCalendar today;
	private static final int[] DAYS_PER_MONTH={31,28,31,30,31,30,31,31,30,31,30,31};
	
	public CalendarComparer(){
		bieber = new GregorianCalendar(1994, 2, 1);
		today = new GregorianCalendar();
	}
	
	public int compareValues(){
		Date biebDate = bieber.getTime();
		Date todayDate = today.getTime();
		long difference = todayDate.getTime() - biebDate.getTime();
		
		todayDate.setTime(difference);
		today.setTime(todayDate);
		int year=today.get(Calendar.YEAR);
		int daysCalc=yearsToDays(year);
		daysCalc+=monthsToDays(today);
		return daysCalc;
	}
	/**
	 * If this method is still being used 400 years from now...
	 * God help us all
	 * @param year 
	 * @return
	 */
	private int yearsToDays(int year){
		int yearDifference=year-EPOCH;
		//because the most recent year
		//has to be dealt with specially
		yearDifference--; 
		int daysVal=(yearDifference)*(DAYS_IN_YEAR);
		int leapYears=(yearDifference+2)/4;
		daysVal+=leapYears;
		return daysVal;
	}
	
	private int monthsToDays(GregorianCalendar today){
		int daysVal=365;
		int year=today.get(Calendar.YEAR);
		int month=today.get(Calendar.MONTH);
		int day=today.get(Calendar.DAY_OF_MONTH);
		
		
			if (year%4==0){
				daysVal++;
			}
			for(int i=0;i<month;i++){
				System.out.println(daysVal);
				System.out.println(i);
				daysVal+=DAYS_PER_MONTH[i];
			}
			daysVal+=day; //1 taken off because his berfday is march 1
		
		
		return daysVal;
			
	}
}
