package com.exercise3;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Test21 {
	public static void main(String[] args){
		CalendarComparer cat= new CalendarComparer();
		System.out.println((new GregorianCalendar().get(Calendar.MONTH)));
		System.out.println(cat.compareValues());
		//System.out.println(cat.compareValues());
		
	}
}
