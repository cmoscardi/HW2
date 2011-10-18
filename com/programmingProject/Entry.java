package com.programmingProject;
/**
 * This is an Entry for the RLESequence.
 * It's best used in conjunction with the RLESequence.
 * <br> <br>
 * It stores the number of elements in a row,
 * and the value of those elements. That's all.
 * @author Christian
 *
 */
public class Entry {
	//who's your daddy
	private int number;
	private int value;
	/**
	 * 
	 * @param numberOf the number of elemtns in a row
	 * @param valueOf the value of those elements
	 * @throws Exception if the value is out of range
	 */
	public Entry(int numberOf, int valueOf) throws Exception{
		if(valueOf>255||valueOf<0){
			throw(new Exception("your value too low/high"));
		}
		number=(short)numberOf;
		value=valueOf;
	}
	/**
	 * 
	 * @return the number of elements in a row
	 */
	public int getNumber(){
		return number;
	}
	/**
	 * 
	 * @return the value of the elements
	 */
	public int getValue(){
		return value;
	}
	/**
	 * sets how many in a row are in this
	 * Entry
	 * @param newNumber must be bigger than 0
	 */
	public void setNumber(int newNumber){
		number= newNumber;
	}
	/**
	 * sets the value of the Entry. It 
	 * sets the value of every Element, 
	 * so be careful
	 * @param newValue the new value you want.
	 * usual rules apply (>0 and <255).
	 */
	public void setValue(int newValue){
		value= newValue;
	}
	
	public String toString(){
		return "("+number+""+","+""+value+")";
	}
	/**
	 * 
	 * @param otherEntry the entry you want to compare
	 * @return true if they're equal in 
	 * number and value
	 */
	public boolean equals(Entry otherEntry){
		if(number==otherEntry.number){
			if(value==otherEntry.value){
				return true;
			}
			else return false;
		}
		else return false;
	}
}

