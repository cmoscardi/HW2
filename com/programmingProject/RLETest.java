package com.programmingProject;

public class RLETest {
	public static void main(String[] args){
		try{
			//Segment 1
		/**	 int[] catcat={2,3,2,3,3,2,3,2,2,2,3,3,2};
			RLESequence cat = new RLESequence(catcat);
			System.out.println(cat);
			System.out.println(cat.internalToString());
			cat.set(0,4);
			System.out.println(cat); 
			System.out.println(cat.internalToString());
			cat.set(12, 3);
			System.out.println(cat);
			System.out.println(cat.internalToString()); 
			int[] what = {-1};
			RLESequence bad = new RLESequence(what); **/
			
			//segment 2
			/**int[] ducat={0,1,1,2,3};
			int[] ducat2={0};
			RLESequence cat = new RLESequence(ducat);
			RLESequence cat2 = new RLESequence(ducat2);
			System.out.println(cat);
			cat.tailReplace(3, cat2);
			System.out.println(cat);
			System.out.println(cat.internalToString()); 
			int[] ducat3={0,0,1,1,1,1,2,4};
			RLESequence cat3=new RLESequence(ducat3);
			cat.tailReplace(1, cat3);
			System.out.println(cat);
			System.out.println(cat.internalToString());
			cat.tailReplace(1,cat3);
			System.out.println(cat);
			System.out.println(cat.internalToString());
			cat.tailReplace(2,cat3);
			System.out.println(cat);
			System.out.println(cat.internalToString());**/
			
			//segment 3
			/** int[] cat={9,8,9,9,9};
			RLESequence ducat = new RLESequence(cat);
			ducat.increment(10);
			System.out.println(ducat);
			ducat.increment(-100);**/
			
			
			//segment 4
			int[][] cat={ 	{0,0,1,0,0,1,0}, 
							{0,0,1,0,0,1,0},
							{0,0,0,1,0,12,0},
							{0,0,0,1,0,0,0},
							{0,1,1,1,1,1,0}
						};
			RLEImage cat2 = new RLEImage(cat);
			System.out.print(cat2); 
		} 
		catch(Exception e){
			System.out.print(e.getMessage());
		}
	}
}
