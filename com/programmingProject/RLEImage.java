package com.programmingProject;

public class RLEImage {
	RLESequence[] compressedImage;
	public RLEImage(int[][] image) throws Exception{
		compress(image);
	}
	
	private int[][] decompress(){
		int[] line1=compressedImage[0].toArray();
		int[][] output=new int[compressedImage.length][line1.length];
		output[0]=line1;
		for(int i=1;i<compressedImage.length;i++){
			output[i]=compressedImage[i].toArray();
		}
		return output;
	}
	
	private void compress(int[][] image) throws Exception{
		compressedImage=new RLESequence[image.length];
		for(int i=0;i<image.length;i++){
			compressedImage[i]= new RLESequence(image[i]);
		}
	}
	
	public int[][] toArray(){
		int[][] outVal= decompress();
		return outVal;
	}
	public String toString(){
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
}
