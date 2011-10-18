package com.programmingProject;

import java.util.ArrayList;

public class RLESequence {
	private ArrayList<Entry> compressedSequence;
	
	/**
	 * Makes an empty sequence for your compressing 
	 * pleasure
	 * @param lengthUncompressed self-explanatory
	 */
	public RLESequence(int lengthUncompressed){
		compressedSequence = new ArrayList<Entry>(lengthUncompressed);
	}
	/**
	 * Takes an uncompressed array and compresses it. This is the 
	 * constructor you want to use. 
	 * @param inputUnCompressed the uncompressed array
	 */
	public RLESequence(int[] inputUnCompressed) throws Exception{
		compressedSequence = new ArrayList<Entry>(inputUnCompressed.length);
		compress(inputUnCompressed);

	}
	
	/**
	 * constructs a sequence of default length. 300 is that length, 
	 * completely arbitrarily. if you, the user of this program, ever want
	 * that changed, send me an email or something
	 */
	public RLESequence(){
		compressedSequence = new ArrayList<Entry>(300);
	}
	
	/**
	 * private!
	 * @param uncompressedArray
	 * @throws Exception
	 */
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
	/**
	 * increments every value in the sequence by (what?)..
	 * <br> you can decrement if you want to. But values 
	 * below 0 will be REJECTED. As will values above 255
	 * @param byWhat this much!
	 * @throws if you give a bogus value
	 */
	public void increment(int byWhat) throws Exception{
		for (Entry e: compressedSequence){
			e.setValue(e.getValue()+byWhat);
			if(e.getValue()>255||e.getValue()<0){
				throw new Exception("too big value");
			}
		}
	}
	
	/**
	 * replaces all values after the provided start index
	 * @param startIndex you know that 
	 * 'provided start index' i was tlaking about??
	 * @param otherSequence the sequence you wish to append to the end
	 * 
	 */
	public void tailReplace (int startIndex, RLESequence otherSequence){
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

		
		for(int i=compressedSequence.size()-1;i>internalIndex;i--){
	
			compressedSequence.remove(i);
		}
		compressedSequence.get(internalIndex).setNumber(
				compressedSequence.get(internalIndex).getNumber()-indexFromEnd-1);
		if(compressedSequence.get(internalIndex).getNumber()==0){
			compressedSequence.remove(internalIndex);
			internalIndex--;
		}
	
		Entry compareEntries = compareTwo(otherSequence.compressedSequence.get(0),
							   compressedSequence.get(internalIndex));
		if(compareEntries!=null){ //i.e. they aren't unequal
			compressedSequence.set(internalIndex, compareEntries);
		}
		else{
		
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
	
 	/**
 	 * sets a given uncompressed index to a value you want
 	 * @param index the uncompressed index where that value is
 	 * @param val the value you wish to change to
 	 * @throws Exception if you put in a bad value or 
 	 * put in too high of an index
 	 */
	public void set(int index, int val) throws Exception{
		int[] indices = getInternalIndices(index);
		Entry indexedEntry=compressedSequence.get(indices[0]);
		
		//first, check this to escape unnecessary calculation
		//
		if(indexedEntry.getValue()==val){
			return;
		}
		
		
		//then
		
		setStepTwo(indices[0], indices[1], val);
		
		
		
		
	}
	
	/**
	 * priavate! Why is this method so long?
	 * Because it has to deal with a lot of checking and 
	 * recompression to make sure that everything is compressed
	 * correctly and in the right place.
	 * @param entryIndex see step 1
	 * @param indexWithinEntry see step1
	 * @param val the value, just like step 1 which you should have looked 
	 * at
	 * @throws Exception same as step 1
	 */
	private void setStepTwo(int entryIndex, int indexWithinEntry, int val)throws Exception{
		Entry previousEntry=null;
	
		Entry currentEntry = compressedSequence.get(entryIndex);
	
		if(entryIndex>0){
			previousEntry=compressedSequence.get(entryIndex-1);
	
		}
		Entry nextEntry=null;
		if(entryIndex<compressedSequence.size()-1){
			nextEntry=compressedSequence.get(entryIndex+1);
	
		}
		//case 1
		if (currentEntry.getNumber()==1){
			
			currentEntry.setValue(val);
			
			Entry firstCompare = compareTwo(previousEntry,currentEntry);
			Entry secondCompare = compareTwo(currentEntry,nextEntry);
	
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
				compressedSequence.set(entryIndex, firstCompare);
				compressedSequence.remove(entryIndex-1);
			}
		
		}
		//case 2
		else {
			//these are sub-entries
			
			Entry previousStuff=null;
			Entry nextStuff=null;
			Entry userInsertedEntry=null;
			for(int i=0;i<currentEntry.getNumber();i++){
				if(i==indexWithinEntry){
					if(i!=0){

						previousStuff=new Entry(i,currentEntry.getValue());
					}
					if(i<currentEntry.getNumber()-1){
	
						nextStuff=new Entry(currentEntry.getNumber()-i-1,currentEntry.getValue());
					}
					userInsertedEntry = new Entry(1, val);
				}

			}
			if(previousStuff!=null&&nextStuff!=null){
				compressedSequence.set(entryIndex,userInsertedEntry);
				compressedSequence.add(entryIndex+1,nextStuff);
				compressedSequence.add(entryIndex,previousStuff);
			}
			else if(previousStuff!=null){
				Entry one= compareTwo(nextEntry,userInsertedEntry);
				if(one!=null){
					compressedSequence.set(entryIndex+1, one);
					currentEntry.setNumber(currentEntry.getNumber()-1);
				}
				else {
					compressedSequence.set(entryIndex,userInsertedEntry);
					compressedSequence.add(entryIndex,previousStuff);
				}
			}
			else if(nextStuff!=null){
				Entry two= compareTwo(previousEntry,userInsertedEntry);
				if(two!=null){
					compressedSequence.set(entryIndex-1, two);
					currentEntry.setNumber(currentEntry.getNumber()-1);			
				}
				else{
					compressedSequence.set(entryIndex,userInsertedEntry);
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
	 * @param firstEntry the first entry you wish to compare
	 * @param secondEntry the <s>first</s> second (hooray for strike tags)
	 * @return null if they aren't equal
	 * 			<br> the new combined entry if they are equal
	 */
	private Entry compareTwo(Entry firstEntry, Entry secondEntry)  {
		Entry returnEntry=null;
		if(firstEntry==null||secondEntry==null){
			return returnEntry;
		}
		int finalValue=firstEntry.getNumber();
		if(firstEntry.getValue()==secondEntry.getValue()){
			finalValue+=secondEntry.getNumber();
			try{
			returnEntry = new Entry(finalValue,firstEntry.getValue());
			}
			catch (Exception e){
				return null;
			}
		}
		
		return returnEntry;
	}
	/**
	 * very self explanatory
	 * @param other the other RLESequence you're comparing
	 * @return true if they equal each other
	 */
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
	
	/**
	 * index 0 is the Entry <br>
	 * index 1 is the index within that Entry
	 * @return
	 */
	private int[] getInternalIndices(int externalIndex){
		int counter=0;
		int internalIndex=-2;
		int entryIndex = -2;
		
		for(int j=0;j<compressedSequence.size();j++){
			for(int k=0;k<compressedSequence.get(j).getNumber();k++){
				if(counter==externalIndex){
					entryIndex=j;
					internalIndex=k;
				
					int[] returnVal={entryIndex,internalIndex};
					return returnVal;
				}
				counter++;
			}
		}
		//this should not be reached, but if it is,
		//you'll get a -2
		int[] returnVal={entryIndex,internalIndex};
		return returnVal;
	}
/**
 * 
 * @return the uncompressed array
 */
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
	/**
	 * 
	 * @return the internal representation. in case you're interested
	 */
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
	
/**
 * 	
 */
public String toString(){
		String returnString="";
		returnString+="[";
		for(int i=0;i<compressedSequence.size();i++){
			for(int j=0;j<compressedSequence.get(i).getNumber();j++){
				//System.out.println(i);
				returnString+=compressedSequence.get(i).getValue();
				returnString+=" ";
			}
		}
		returnString=returnString.substring(0,returnString.length()-1);
		returnString+="]";
		return returnString;
	}
}
