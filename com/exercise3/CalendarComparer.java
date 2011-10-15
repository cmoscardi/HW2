package com.exercise3;

import java.util.Date;
import java.util.GregorianCalendar;

public class CalendarComparer {
	private GregorianCalendar Bieber;
	private GregorianCalendar Today;
	
	public CalendarComparer(){
		Bieber = new GregorianCalendar(1994, 3, 1);
		Today = new GregorianCalendar();
	}
	
	public int compareValues(){
		Date biebDate = Bieber.getTime();
		Date todayDate = Today.getTime();
		long difference = todayDate.getTime() - biebDate.getTime();
		todayDate.setTime(difference);
		
	}
}
