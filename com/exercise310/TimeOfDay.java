package com.exercise310;

public class TimeOfDay {
	public static final String AM="AM";
	public static final String PM="PM";
	private static final int HOURS_TO_SECONDS=3600;
	private static final int MINUTES_TO_SECONDS=60;
	private int hoursVal;
	private int minutesVal;
	private int secondsVal;
	private String amPmVal;
	public TimeOfDay(int hours, int minutes, int seconds, String amPm) throws IllegalArgumentException{
			hoursVal=hours;
			minutesVal=minutes;
			secondsVal=seconds;
			amPmVal=amPm.toUpperCase();
			if(!conditionCheck()){
				throw new IllegalArgumentException("One of your arguments is invalid. Check to see the possible argument values");
			}
			
		}
	
	public TimeOfDay addSeconds(int seconds){
		int newSeconds = seconds+secondsVal;
		if (newSeconds<60){
			return new TimeOfDay(hoursVal,minutesVal,newSeconds,amPmVal);
		}
		else return minutesChange(newSeconds);
				
		
		
	}

	private TimeOfDay minutesChange(int newSeconds){
		int counter=0;

		while(newSeconds>=60){
			newSeconds-=60;
			counter++;
		}
		
		int newMinutes=minutesVal+counter;
		if(newMinutes<60){
			return new TimeOfDay(hoursVal,newMinutes,newSeconds,amPmVal);
		}
		else {
			return hoursChange(newMinutes,newSeconds);
		}
		
	}
	
	private TimeOfDay hoursChange(int newMinutes, int newSeconds){
		int counter=0; 
		
		while(newMinutes>=60){
			newMinutes-=60;
			counter++; // counter was just reset, see above
		}
		
		int newHours=hoursVal;
		boolean switchTime=false;
		counter = parseCounter(counter); //cancelling out any 24 hour shifts
		while(counter!=0){
			newHours++;
			counter--;
			if(newHours==12){
				switchTime=(!switchTime);
			}
			if(newHours>12){
				newHours-=12;
			}
		}
		String newAmVal=switchCheck(switchTime);
		return new TimeOfDay(newHours,newMinutes,newSeconds,newAmVal);

	}
	
	
	
	public int secondsFrom(TimeOfDay other){
		int otherHours=other.getHour();
		int otherMinutes=other.getMinute();
		int otherSeconds=other.getSecond();
		String otherAmPm=other.getAmPm();
		if(!(otherAmPm.equalsIgnoreCase(amPmVal))){
			System.out.println("here");
			if(otherAmPm.equals("PM")){
				otherHours+=12;
			}
			if(amPmVal.equals("PM")){
				hoursVal+=12;
			}
		}
		
		otherHours-=hoursVal;
		otherMinutes-=minutesVal;
		otherSeconds-=secondsVal;

		otherSeconds+=otherHours*HOURS_TO_SECONDS;
		otherSeconds+=otherMinutes*MINUTES_TO_SECONDS;
		
		return otherSeconds;
	}

	public String getAmPm(){
		return amPmVal;
	}
	
	public int getHour(){
		return hoursVal;
	}
	
	public int getMinute(){
		return minutesVal;
	}
	
	public int getSecond(){
		return secondsVal;
	}
	
	private boolean conditionCheck(){
		if(amPmVal.equalsIgnoreCase(AM)|| amPmVal.equalsIgnoreCase(PM)){
			if(hoursVal>=1 && hoursVal<=12){
				if(minutesVal<=59 && minutesVal>=0){
					if(secondsVal<=59 && secondsVal>=0){
						return true;
					}
					else return false;
				}
				else return false;
			}
			else return false;
		}
		else return false;
	}
	
	private int parseCounter(int counter){

		while(counter>24){
			counter-=24;
		}
		return counter;
	}
	
	private String switchCheck(boolean switchTime){
		String newAmVal;
		if(!switchTime){
			newAmVal=amPmVal;
		}
		else if (amPmVal.equals(AM)){
			newAmVal=PM;
		}
		else{
			newAmVal=AM;
		}
		return newAmVal;
	}
	
	
	public String toString(){
		return hoursVal+" "+minutesVal+" "+secondsVal+" "+amPmVal;
	}
}
