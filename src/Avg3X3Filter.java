import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Avg3X3Filter {

	public static void main(String[] argv) throws IOException{
		
		//String line;
		StringTokenizer str = null;
		int numRows = 0;
		int numCols = 0;
		int minVal = 0;
		int maxVal = 0;
		//here
		Scanner inFile = new Scanner(new File("Avg3X3Filter_Data1.txt"));
		if(inFile.hasNextLine())
			str = new StringTokenizer(inFile.nextLine());
			
		numRows = Integer.parseInt(str.nextToken());
		numCols = Integer.parseInt(str.nextToken());
		minVal = Integer.parseInt(str.nextToken());
		maxVal = Integer.parseInt(str.nextToken());
		imageProcessing processor = new imageProcessing(numRows,numCols,minVal,maxVal);
		processor.print();
		
		//Test 11:33
	}
	
}
	
class imageProcessing {
	int numRows, numCols, minVal, maxVal, newMin, newMax;
	int[][] imgInAry, imgOutAry, mirrorFramedAry, tempAry;
	int[] hist, neighborAry;
	
	imageProcessing(int numRows,int numCols,int minVal,int maxVal){
		this.numRows = numRows;
		this.numCols = numCols;
		this.minVal = minVal;
		this.maxVal = maxVal;
		int imgInAry[][] = new int [numRows][numCols];
		int imgOutAry[][] = new int [numRows][numCols];
		int mirrorFramedAry[][] = new int [numRows+2][numCols+2];
		int tempAry[][] = new int [numRows+2][numCols+2];
		int hist[] = new int[maxVal+1];
		int neighborAry[] = new int[9];
	}
	
	void loadImage(int[][] imgInAry, int[][] mirrorFramedAry) {
        for(int i=0;i<numRows;i++) {
            for(int j=0;j<numCols;j++) {

            }
        }
    }
	
	void mirrorFramed(int numRows, int numCols){
		for(int numRow=0;numRow<numRows;numRow++) {
			for(int numCol=0;numCol<numCols;numCol++ ) {
				// need more work here
			}
		}
	}
		
	void print(){
		System.out.println(numRows + " " + numCols + " " + minVal + " " + maxVal);
	}
}

