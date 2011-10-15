package com.programmingProject;

public class RLETest {
	public static void main(String[] args){
		try{
			int[] catcat={1,1,1,1,1,1,1,1,1,};
			RLESequence cat = new RLESequence(catcat);
			System.out.println(cat.internalToString());
		}
		catch(Exception e){
			System.out.print(e.getMessage());
		}
	}
}
