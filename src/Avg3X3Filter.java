//Steven Yu
//CS381 - Image Processing

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Avg3X3Filter {

	public static void main(String[] argv) throws IOException{
		
		int numRows = 0, numCols = 0, minVal = 0, maxVal = 0;
		
		Scanner inFile = new Scanner(new File(argv[0]));	//read in data
		PrintWriter histogram = new PrintWriter(new FileWriter(argv[2]));	//write to histogram.txt
		PrintWriter outFile = new PrintWriter(new FileWriter(argv[3]));		//write to Thresholded_image.txt

		//retrieve header information
		numRows = Integer.parseInt(inFile.next());
		numCols = Integer.parseInt(inFile.next());
		minVal = Integer.parseInt(inFile.next());
		maxVal = Integer.parseInt(inFile.next());
		
		imageProcessing processor = new imageProcessing(numRows,numCols,minVal,maxVal,inFile,outFile,histogram);
		int[][] imgInAry = new int[numRows][numCols], mirrorFramedAry = new int[numRows+2][numCols+2], tempAry = new int[numRows+2][numCols+2], imgOutAry = new int [numRows][numCols];
		int[] hist = new int[maxVal+1];
		int thr_value = Integer.parseInt(argv[1]);
		
		processor.loadImage(imgInAry,mirrorFramedAry);
		processor.ComputeHistogram(imgInAry, hist, maxVal);
		processor.mirrorFramed(numRows, numCols, mirrorFramedAry);
		processor.computeAVG3X3(mirrorFramedAry, tempAry);
		processor.computeThreshold(mirrorFramedAry, imgOutAry, thr_value);
		
		//print results
		processor.printHist(hist);
		processor.prettyPrint (imgOutAry);
	}
	
}
