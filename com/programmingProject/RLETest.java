package com.programmingProject;

public class RLETest {
	public static void main(String[] args){
		try{
			int[] catcat={2,3,2,3,3,2,3,2,2,2,3,3,2};
			RLESequence cat = new RLESequence(catcat);
			System.out.println(cat);
			cat.set(6,4);
			System.out.println(cat);
		}
		catch(Exception e){
			System.out.print(e.getMessage());
		}
	}
}
