
package com.programmingProject;
public class RLEEntry {

		//who's your daddy
		private short number;
		private RLESequence value;
		
		public RLEEntry(int numberOf, RLESequence valueOf) {
			number=(short)numberOf;
			value=valueOf;
		}
		
		public int getNumber(){
			return number;
		}
		
		public RLESequence getValue(){
			return value;
		}
		
		
		public String toString(){
			return "("+number+""+","+""+value+")";
		}
		
	
	}



