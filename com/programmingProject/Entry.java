package com.programmingProject;

public class Entry {
	//who's your daddy
	private short number;
	private int value;
	
	public Entry(int numberOf, int valueOf) throws Exception{
		if(valueOf>255){
			throw(new Exception("your value too high"));
		}
		number=(short)numberOf;
		value=valueOf;
	}
	
	public int getNumber(){
		return number;
	}
	
	public int getValue(){
		return value;
	}
	
	public void setNumber(int newNumber){
		number=(short) newNumber;
	}
	
	public void setValue(int newValue){
		value=(short) newValue;
	}
	public String toString(){
		return "("+number+""+","+""+value+")";
	}
	
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

