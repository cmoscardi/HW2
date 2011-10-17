package com.programmingProject;

import java.util.ArrayList;

public class RLEImage {
	private RLESequence[] compressedImage;
	private ArrayList<RLEEntry> compressedSequence;
	public RLEImage(int[][] image) throws Exception{
		compressedSequence=new ArrayList<RLEEntry>();
		compress(image);
	}
	
	private int[][] decompress()throws Exception{
		int[][] returnVal;
		int counter = 0;
		for(int i=0;i<compressedSequence.size();i++){
			counter+=compressedSequence.get(i).getNumber();
		}
		
		//woah nelly! unfortunately it's pretty much necessary
		int rowNumber = 
			compressedSequence.get(0).getValue().toArray().length;
		
		returnVal = new int[counter][rowNumber];
		int outerCounter=0;
		int innerCounter=0;
		for(int i=0;i<compressedSequence.size();i++){
			outerCounter+=innerCounter;
			innerCounter=0;
			for(int j=0;j<compressedSequence.get(i).getNumber();j++){
				for(int k=0;k<rowNumber;k++){
					
					returnVal[i+j+outerCounter][k]=compressedSequence.get(i).getValue().get(k);
				}
				if (j>0){
					innerCounter++;
				}
			}
		}
		return returnVal;
		
	}
	
	private void compress(int[][] image) throws Exception{
		compressedImage=new RLESequence[image.length];
		int counter=0;
		RLESequence lastVal=new RLESequence(image[0]);
		for(int i=1;i<image.length;i++){
			counter++;
			RLESequence nextLine = new RLESequence(image[i]);
			if(!(lastVal.equals(nextLine))){
				RLEEntry next = new RLEEntry(counter, lastVal);
				compressedSequence.add(next);
				counter=0;
			}
			lastVal = nextLine;
		}
		counter++;
		compressedSequence.add(new RLEEntry(counter,lastVal));
		compressedSequence.trimToSize();
	}
	
	public int[][] toArray(){
		
		try{
			int[][] outVal = decompress();
			return outVal;
		}
		catch (Exception e){
			int[][] outVal={{-1},{-1}};
			return outVal;
			//honestly if this happens, cry.
		}
	}
	public String toString() {
		try{
			String returnString="";
			int[][] out = decompress();
			for(int i=0;i<out.length;i++){
				returnString+="[";
			for (int j=0;j<out[i].length;j++){
				returnString+=out[i][j]+" ";	
			}
			returnString=returnString.substring(0,returnString.length()-1);
			returnString+="]";
			returnString+="\n";
		}
		return returnString;
		}
		catch(Exception e){
		return e.toString();
		}
	}
}
