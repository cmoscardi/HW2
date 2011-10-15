package com.exercise310;

public class Test {

	/**
	 * @param args
	 */
	 public static void main(String[] args) {
		// TODO Auto-generated method stub
		TimeOfDay now=new TimeOfDay(10,30,27,"am");
		System.out.println(now);
		System.out.println("should be 10:30:27 am");
		now=now.addSeconds(60*29);
		System.out.println(now);
		System.out.println("should be 10:59:27 am");
		now=now.addSeconds(33);
		System.out.println(now);
		System.out.println("should be 11:00:00 am");
		now=now.addSeconds(60*60*12);
		System.out.println(now);
		System.out.println("should be 11PM");
		now=now.addSeconds(60*60*35);
		System.out.println(now);
	} 

}
