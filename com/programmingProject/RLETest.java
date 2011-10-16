package com.programmingProject;

public class RLETest {
	public static void main(String[] args){
		try{
		/**	int[] catcat={2,3,2,3,3,2,3,2,2,2,3,3,2};
			RLESequence cat = new RLESequence(catcat);
			System.out.println(cat);
			cat.set(0,4);
			System.out.println(cat); **/
			
			int[] ducat={9,9,8,9};
			int[] ducat2={8,9,9,9};
			RLESequence cat = new RLESequence(ducat);
			RLESequence cat2 = new RLESequence(ducat2);
			System.out.println(cat);
			cat.tailReplace(3, cat2);
			System.out.println(cat);
			System.out.print(cat.internalToString());
		}
		catch(Exception e){
			System.out.print(e.getMessage());
		}
	}
}
