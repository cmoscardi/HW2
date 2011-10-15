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
		System.out.println("cool");
	}
	
	public String toString(){
		return "("+number+")"+","+"("+value+")";
	}
}
