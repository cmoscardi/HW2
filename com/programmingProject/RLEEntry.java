
package com.programmingProject;
/**
 * This is best used in conjunction
 * with the RLEImage class. It is to compress
 * the rows of the RLEImage matrix of pixels
 */
public class RLEEntry {

		private int number;
		private RLESequence value;
		
		/**
		 * 
		 * @param numberOf the number of rows
		 * @param valueOf
		 */
		public RLEEntry(int numberOf, RLESequence valueOf) {
			number=numberOf;
			value=valueOf;
		}
		/**
		 * 
		 * @return how many rows it stores
		 */
		public int getNumber(){
			return number;
		}
		/**
		 * 
		 * @return the sequence which is compressed 
		 * in these rows
		 */
		public RLESequence getValue(){
			return value;
		}
		
		
		public String toString(){
			return "("+number+""+","+""+value+")";
		}
		
	
	}



