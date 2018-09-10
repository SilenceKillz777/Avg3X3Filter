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
		
		processor.printHeader();
		processor.loadImage(inFile);
		processor.mirrorFramed(numRows, numCols);
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
		imgInAry= new int [numRows][numCols];
		imgOutAry = new int [numRows][numCols];
		mirrorFramedAry = new int [numRows+2][numCols+2];
		tempAry = new int [numRows+2][numCols+2];
		hist = new int[maxVal+1];
		neighborAry = new int[9];
	}
	
	void loadImage(Scanner infile) {
		
        for(int i=0;i<numRows;i++) {
            for(int j=0;j<numCols;j++) {
            	//System.out.print(infile.next()+" ");
            	imgInAry[i][j] = Integer.parseInt(infile.next());
            //	System.out.print(imgInAry.length);
            }
           // System.out.println();
        }
        /*
        for(int row = 0;row<numRows;row++){
        	for(int col = 0;col<numCols;col++){
        		System.out.print(imgInAry[row][col]+ " ");
        	}
        	System.out.println();
        }
        */
    }
	
	void mirrorFramed(int numRows, int numCols){
		for(int numRow=0;numRow<numRows;numRow++) {
			for(int numCol=0;numCol<numCols;numCol++ ) {
				mirrorFramedAry[numRow+1][numCol+1] = imgInAry[numRow][numCol];
			}
		}
		
		/*
		for(int i=0;i<numRows+2;i++){
			for(int j=0;j<numCols+2;j++){
				System.out.print(mirrorFramedAry[i][j]+" ");
			}
			System.out.println();
		}
		*/
	}
		
	void printHeader(){
		System.out.println(numRows + " " + numCols + " " + minVal + " " + maxVal);
	}
}

