package com.exercise310;

/**
  	Stores a time of day. Not that you, the user,
	care, but it uses a really
 	poor internal representation
 * @author Christian
 *
 */
public class TimeOfDay {
	public static final String AM="AM";
	public static final String PM="PM";
	private static final int HOURS_TO_SECONDS=3600;
	private static final int MINUTES_TO_SECONDS=60;
	private int hoursVal;
	private int minutesVal;
	private int secondsVal;
	private String amPmVal;
	
	/**

	 * 
	 * @param hours between 1 and 12
	 * @param minutes between 0 and 59
	 * @param seconds between 0 and 59
	 * @param amPm "am" or "pm" case doesn't matter
	 * @throws IllegalArgumentException if something isn't a "natural 
	 * clock value" e.g. 1-12, 0-59, AM or PM
	 */
	public TimeOfDay(int hours, int minutes, int seconds, String amPm) throws IllegalArgumentException{
			hoursVal=hours;
			minutesVal=minutes;
			secondsVal=seconds;
			amPmVal=amPm.toUpperCase();
			if(!conditionCheck()){
				throw new IllegalArgumentException("One of your arguments is invalid. Check to see the possible argument values");
			}
			
		}
	/**
	 * 
	 * @param seconds the number of seconds you want to add.
	 * May not be less than 0. Don't try.
	 * @return a Time which has that many seconds added to it.
	 */
	public TimeOfDay addSeconds(int seconds){
		int newSeconds = seconds+secondsVal;
		if (newSeconds<60){
			return new TimeOfDay(hoursVal,minutesVal,newSeconds,amPmVal);
		}
		else return minutesChange(newSeconds);
				
		
		
	}
	/**
	 * residue classes mod 12 should always
	 * be written 0-11. that is all.
	 * @param newSeconds it's kind of a private matter.
	 * @return the Time with so many minutes and/or hours added to it
	 */
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
	/**
	 * the worst ever to write.
	 * There is no way to differentiate a number
	 * of things. WHY MUST WE USE THIS AWFUL
	 * INTERNAL REPRESENTATION
	 * @param newMinutes from minutesChange method
	 * @param newSeconds private, not telling. you thought
	 * i forgot. 
	 * @return the completely converted time of day
	 */
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
	
	
	/**
	 * Calculates how many seconds are between two times.
	 * It assumes they're in the same day.
	 * It gives a signed answer, so you can see which time is ahead 
	 * of which...
	 * @param other the other time you wish to compare
	 * @return the number of seconds, signed, between the two times
	 */
	public int secondsFrom(TimeOfDay other){
		int otherHours=other.getHour();
		int otherMinutes=other.getMinute();
		int otherSeconds=other.getSecond();
		String otherAmPm=other.getAmPm();
		int newSeconds=secondsVal;
		int newMinutes=minutesVal;
		int newHours=hoursVal;
		
		
		if(otherHours==12){
			otherHours-=12;
		}
		if(newHours==12){
			newHours-=12;
		}
		if(otherAmPm.equalsIgnoreCase(PM)){
			otherHours+=12;
		}
		if(amPmVal.equalsIgnoreCase(PM)){
			newHours+=12;
		}
		otherSeconds+=otherMinutes*MINUTES_TO_SECONDS;
		otherSeconds+=otherHours*HOURS_TO_SECONDS;
		
		newSeconds+=newMinutes*MINUTES_TO_SECONDS;
		newSeconds+=newHours*HOURS_TO_SECONDS;
		
		return newSeconds-otherSeconds;
	}
	/**
	 * 
	 * @return either AM or PM in string form
	 */
	public String getAmPm(){
		return amPmVal;
	}
	/**
	 * 
	 * @return a number between 1 and 12
	 */
	public int getHour(){
		return hoursVal;
	}
	/**
	 * 
	 * @return a number between 0 and 59
	 */
	public int getMinute(){
		return minutesVal;
	}
	/**
	 * 
	 * @return a number between 0 and 59
	 */
	public int getSecond(){
		return secondsVal;
	}
	/**
	 * why am i documenting these privates ;)
	 * @return true or false. (i know this is astounding to you. 
	 * try to remain calm.)
	 */
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
	/**
	 * 
	 * @param counter
	 * @return
	 */
	private int parseCounter(int counter){

		while(counter>24){
			counter-=24;
		}
		return counter;
	}
	/**
	 * words cannot expresss
	 * @param the pain
	 * @return i feel 
	 * when i'm with you
	 */
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
	
	/**
	 * hooray you can make the time into a string!
	 */
	public String toString(){
		return hoursVal+" "+minutesVal+" "+secondsVal+" "+amPmVal;
	}
}
