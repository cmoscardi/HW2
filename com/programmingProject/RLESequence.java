package com.programmingProject;

import java.util.ArrayList;

public class RLESequence {
	private ArrayList<Entry> compressedSequence;
	
	public RLESequence(int lengthUncompressed){
		compressedSequence = new ArrayList<Entry>(lengthUncompressed);
	}

	
	public RLESequence(int[] inputUnCompressed)throws Exception{
		System.out.println("h");
		compressedSequence = new ArrayList<Entry>(inputUnCompressed.length);
		compress(inputUnCompressed);
		if (compressedSequence.size()==inputUnCompressed.length){
			throw new Exception("you haven't saved much space");
		}
	}
	
	public RLESequence(){
		compressedSequence = new ArrayList<Entry>(300);
	}
	
	private void compress(int[] uncompressedArray) throws Exception{
		int counter=0;
		int lastVal=uncompressedArray[0]; 
		for(int i=1;i<uncompressedArray.length;i++){
			System.out.println(i);
			if(lastVal==uncompressedArray[i]){
				counter++;
			}
			else{
				Entry newEntry = new Entry(counter,lastVal);
				System.out.println("??");
				counter++;
				compressedSequence.add(newEntry);
				counter=0;
			}
			if(counter!=0&&(i+1)==uncompressedArray.length){
				System.out.println("j");
				counter++;
				Entry newEntry=new Entry(counter,lastVal);
				compressedSequence.add(newEntry);
				System.out.println("here?");
			}
		}
		compressedSequence.trimToSize();
		System.out.println("k");
		System.out.println("h"+compressedSequence.size());
	}
	
	public String internalToString(){
		String returnVal="";
		returnVal+="(";
		for(int i=0;i<compressedSequence.size();i++){
			returnVal+=compressedSequence.get(i);
			returnVal+=",";
		}
		returnVal+=")";
		return returnVal;
	}
	
}
