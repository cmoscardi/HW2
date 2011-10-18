package com.exercise310;

public class Test {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TimeOfDay now=new TimeOfDay(12,30,27,"pm");
		TimeOfDay later=new TimeOfDay(1,30,27,"am");
		System.out.println(later);
		System.out.println(now.secondsFrom(later));
		System.out.println(later.secondsFrom(now));
	} 

}
