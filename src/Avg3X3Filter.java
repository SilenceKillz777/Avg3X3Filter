import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Avg3X3Filter {

	public static void main(String[] argv) throws IOException{
		
		StringTokenizer str = null;
		int numRows = 0, numCols = 0, minVal = 0, maxVal = 0;
		
		Scanner inFile = new Scanner(new File(argv[0]));
		PrintWriter histogram = new PrintWriter(new FileWriter(argv[2]));
		PrintWriter outFile = new PrintWriter(new FileWriter(argv[3]));

		if(inFile.hasNextLine())
			str = new StringTokenizer(inFile.nextLine());
			
		numRows = Integer.parseInt(str.nextToken());
		numCols = Integer.parseInt(str.nextToken());
		minVal = Integer.parseInt(str.nextToken());
		maxVal = Integer.parseInt(str.nextToken());
		
		imageProcessing processor = new imageProcessing(numRows,numCols,minVal,maxVal,inFile,outFile,histogram);
		int[][] imgInAry = new int[numRows][numCols], mirrorFramedAry = new int[numRows+2][numCols+2], tempAry = new int[numRows+2][numCols+2], imgOutAry = new int [numRows][numCols];
		int[] hist = new int[maxVal+1];
		int thr_value = Integer.parseInt(argv[1]);
		
		processor.loadImage(imgInAry,mirrorFramedAry);
		processor.ComputeHistogram(imgInAry, hist, maxVal);
		processor.mirrorFramed(numRows, numCols, mirrorFramedAry);
		processor.computeAVG3X3(mirrorFramedAry, tempAry);
		processor.computeThreshold(mirrorFramedAry, imgOutAry, thr_value);
		
		processor.printHist(hist);
		processor.prettyPrint (imgOutAry);
	}
	
}
