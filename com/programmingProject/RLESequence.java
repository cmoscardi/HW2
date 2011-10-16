package com.programmingProject;

import java.util.ArrayList;

public class RLESequence {
	private ArrayList<Entry> compressedSequence;
	
	public RLESequence(int lengthUncompressed){
		compressedSequence = new ArrayList<Entry>(lengthUncompressed);
	}

	public RLESequence(int[] inputUnCompressed)throws Exception{
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
			counter++;
			if(lastVal!=uncompressedArray[i]){
				//System.out.println(counter);
				Entry newEntry = new Entry(counter,lastVal);
				compressedSequence.add(newEntry);
				counter=0;
			}

			lastVal=uncompressedArray[i];
		}
		counter++;
		compressedSequence.add(new Entry(counter,lastVal));
		compressedSequence.trimToSize();
	}
	
	public void tailReplace (int startIndex, RLESequence otherSequence)throws Exception{
		int counter=0;
		int internalIndex=0;
		int indexFromEnd=0;
		for(int j=0;j<compressedSequence.size();j++){
			for(int k=0;k<compressedSequence.get(j).getNumber();k++){
				if(counter==startIndex){
					internalIndex=j;
					indexFromEnd=compressedSequence.get(j).getNumber()- 1 - k;
				}
				counter++;
			}
		}
		System.out.println("internalIndex " + internalIndex);
		System.out.println("indexFromEnd" + indexFromEnd);
		System.out.println(this);
		System.out.println(compressedSequence.size());
		System.out.println(compressedSequence.get(2));
		
		for(int i=compressedSequence.size()-1;i>internalIndex;i--){
			System.out.println("wtf");
			System.out.println(compressedSequence.get(i));
			compressedSequence.remove(i);
		}
		compressedSequence.get(internalIndex).setNumber(
				compressedSequence.get(internalIndex).getNumber()-indexFromEnd-1);
		if(compressedSequence.get(internalIndex).getNumber()==0){
			compressedSequence.remove(internalIndex);
			internalIndex--;
		}
		System.out.println("hey "+this);
		
		
		Entry cat = compareTwo(otherSequence.compressedSequence.get(0),
							   compressedSequence.get(internalIndex));
		System.out.println("catVal=" + cat);
		if(cat!=null){
			System.out.println(indexFromEnd);
			compressedSequence.set(internalIndex, cat);
		}
		else{
			compressedSequence.get(internalIndex).setNumber(
					compressedSequence.get(internalIndex).getNumber()-1);
			if(compressedSequence.get(internalIndex).getNumber()==0){
				compressedSequence.set(internalIndex, otherSequence.compressedSequence.get(0));
			}
			else{
				compressedSequence.add(otherSequence.compressedSequence.get(0));
			}
		}
		for(int i=1;i<otherSequence.compressedSequence.size();i++){
			compressedSequence.add(otherSequence.compressedSequence.get(i));
		}
		
	}
	
	
	/**
 * Retreives the value at index i of the uncompressed string <br>
	 * Indices start at 0, because we're computer scientists!
	 * @param i the uncompressed index
	 * @return the value at the index. if 300, the value you
	 * @throws the value you input isn't in there
	 */
 	public int get(int index)throws Exception{
		int counter=0;
		for (int j=0;j<compressedSequence.size();j++){
			for(int k=0;k<compressedSequence.get(j).getNumber();k++){
				if(counter==index){
					return compressedSequence.get(j).getValue();
				}
				counter++;
			}
		}
		throw new Exception("THAT INDEX JUST ISN'T HERE");
		
	}
	
	public void set(int index, int val) throws Exception{
		int newIndex=index;
		int counter=0;
		Entry indexedEntry=null;
		int entryIndex = -2;
		for(int j=0;j<compressedSequence.size();j++){
			for(int k=0;k<compressedSequence.get(j).getNumber();k++){
				if(counter==index){
					indexedEntry=compressedSequence.get(j);
					entryIndex=j;
					newIndex=k;
					System.out.println("newIndex="+newIndex);
				}
				counter++;
			}
		}
		
		//first, check this to escape unnecessary calculation
		//
		if(indexedEntry.getValue()==val){
			return;
		}
		
		
		//then
		
		setStepTwo(entryIndex, newIndex, val);
		
		
		
		
	}
	
	private void setStepTwo(int entryIndex, int newIndex, int val)throws Exception{
		Entry indexMinusOne=null;
		System.out.println("entryIndex=" + entryIndex);
		Entry containingIndex = compressedSequence.get(entryIndex);
		System.out.println("containingIndex="+containingIndex);
		if(entryIndex>0){
			indexMinusOne=compressedSequence.get(entryIndex-1);
			System.out.println("indexMinusOne="+indexMinusOne);
		}
		Entry indexPlusOne=null;
		if(entryIndex<compressedSequence.size()-1){
			indexPlusOne=compressedSequence.get(entryIndex+1);
			System.out.println("indexPlusOne="+indexPlusOne);
		}
		//case 1
		if (containingIndex.getNumber()==1){
			containingIndex.setValue(val);
			Entry firstCompare = compareTwo(indexMinusOne,containingIndex);
			Entry secondCompare = compareTwo(containingIndex,indexPlusOne);
	
			if (firstCompare!=null&&secondCompare!=null){
				Entry thirdCompare = compareTwo(firstCompare,secondCompare);
				compressedSequence.set(entryIndex,thirdCompare);
				compressedSequence.remove(entryIndex-1);
				//since we've shifted one to the left
				compressedSequence.remove(entryIndex);
			}
			else if(secondCompare!=null){
				compressedSequence.set(entryIndex, secondCompare);
				compressedSequence.remove(entryIndex+1);
			}
			else if(firstCompare!=null){
				compressedSequence.set(entryIndex, secondCompare);
				compressedSequence.remove(entryIndex-1);
			}
		
		}
		//case 2
		else {
			//these are sub-entries
			
			Entry previousStuff=null;
			Entry nextStuff=null;
			Entry currentEntry=null;
			for(int i=0;i<containingIndex.getNumber();i++){
				if(i==newIndex){
					if(i!=0){
						System.out.println("previousStuff is good");
						System.out.println(i);
						previousStuff=new Entry(i,containingIndex.getValue());
					}
					if(i<containingIndex.getNumber()-1){
						System.out.println("nextStuff is good");
						nextStuff=new Entry(containingIndex.getNumber()-i-1,containingIndex.getValue());
					}
					currentEntry = new Entry(1, val);
				}

			}
			if(previousStuff!=null&&nextStuff!=null){
				System.out.println("previousStuff=" + previousStuff);
				System.out.println("nextStuff=" + nextStuff);
				System.out.println("here");
				compressedSequence.set(entryIndex,currentEntry);
				compressedSequence.add(entryIndex+1,nextStuff);
				compressedSequence.add(entryIndex,previousStuff);
			}
			else if(previousStuff!=null){
				Entry one= compareTwo(indexPlusOne,currentEntry);
				if(one!=null){
					compressedSequence.set(entryIndex+1, one);
					containingIndex.setNumber(containingIndex.getNumber()-1);
				}
				else {
					compressedSequence.set(entryIndex,currentEntry);
					compressedSequence.add(entryIndex,previousStuff);
				}
			}
			else if(nextStuff!=null){
				Entry two= compareTwo(indexMinusOne,currentEntry);
				if(two!=null){
					System.out.println("heree");
					System.out.println(two);
					compressedSequence.set(entryIndex-1, two);
					containingIndex.setNumber(containingIndex.getNumber()-1);			
				}
				else{
					compressedSequence.set(entryIndex,currentEntry);
					compressedSequence.add(entryIndex+1,nextStuff);
				}
			}
			else{
				//this is mathematically impossible because
				//it is handled above, in case 1
			}
			
		}

		
	}
	/**
	 * 
	 * @param firstEntry
	 * @param secondEntry
	 * @return null if they aren't equal
	 */
	private Entry compareTwo(Entry firstEntry, Entry secondEntry) throws Exception{
		Entry returnEntry=null;
		if(firstEntry==null||secondEntry==null){
			return returnEntry;
		}
		int finalValue=firstEntry.getNumber();
		if(firstEntry.getValue()==secondEntry.getValue()){
			finalValue+=secondEntry.getNumber();
			returnEntry = new Entry(finalValue,firstEntry.getValue());
		}
		
		return returnEntry;
	}

	public boolean equals(RLESequence other){
		
		if(other.compressedSequence.size()!=compressedSequence.size()){
			return false;
		}
		
		for(int i=0;i<compressedSequence.size();i++){
			if(!(other.compressedSequence.get(i).equals(compressedSequence.get(i)))){
				return false;
			}
		}
		return true;
	}
	
	public int[] toArray(){
		int[] returnArray;
		int counter=0;
		//determine size of array
		for (int i=0;i<compressedSequence.size();i++){
			counter+=compressedSequence.get(i).getNumber();
		}
		returnArray= new int[counter];
		//adding in each value 
		//counter is reused, because i like that word
		int innerCounter=0;
		int outerCounter=0;
		for(int i=0;i<compressedSequence.size();i++){
			outerCounter+=innerCounter;
			innerCounter=0;
			for(int j=0;j<compressedSequence.get(i).getNumber();j++){
			//	System.out.println(i+" "+j+" "+outerCounter);
				returnArray[i+j+outerCounter]=
					compressedSequence.get(i).getValue();
				if(j>0){
					innerCounter++;
				}
			}
		}
		return returnArray;
	}
	
	public String internalToString(){
		String returnString="";
		returnString+="(";
		for(int i=0;i<compressedSequence.size();i++){
			returnString+=compressedSequence.get(i);
			returnString+=",";
		}
		returnString=returnString.substring(0,returnString.length()-1);
		returnString+=")";
		return returnString;
	}
	
	public String toString(){
		String returnString="";
		returnString+="[";
		for(int i=0;i<compressedSequence.size();i++){
			for(int j=0;j<compressedSequence.get(i).getNumber();j++){
				//System.out.println(i);
				returnString+=compressedSequence.get(i).getValue();
				returnString+=",";
			}
		}
		returnString=returnString.substring(0,returnString.length()-1);
		returnString+="]";
		return returnString;
	}
}
