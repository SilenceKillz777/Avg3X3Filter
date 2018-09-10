import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Avg3X3Filter {

	public static void main(String[] argv) throws IOException{
		
		//String line;
		StringTokenizer str = null;
		int numRows = 0, numCols = 0, minVal = 0, maxVal = 0;
		//here
		Scanner inFile = new Scanner(new File("Avg3X3Filter_Data1.txt"));
		if(inFile.hasNextLine())
			str = new StringTokenizer(inFile.nextLine());
			
		numRows = Integer.parseInt(str.nextToken());
		numCols = Integer.parseInt(str.nextToken());
		minVal = Integer.parseInt(str.nextToken());
		maxVal = Integer.parseInt(str.nextToken());
		imageProcessing processor = new imageProcessing(numRows,numCols,minVal,maxVal,inFile);
		int[][] imgInAry = new int[numRows][numCols], mirrorFramedAry = new int[numRows+2][numCols+2];
		
		processor.printHeader();
		processor.loadImage(imgInAry,mirrorFramedAry);
		processor.mirrorFramed(numRows, numCols, mirrorFramedAry);
		//Test 11:33
	}
	
}
	
class imageProcessing {
	int numRows, numCols, minVal, maxVal, newMin, newMax;
	int[][] imgInAry, imgOutAry, mirrorFramedAry, tempAry;
	int[] hist, neighborAry;
	Scanner inFile;
	
	imageProcessing(int numRows,int numCols,int minVal,int maxVal, Scanner inFile){
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
		this.inFile = inFile;
	}
	
	void loadImage(int[][] imgInAry,int[][] mirrorFramedAry) {
		
        for(int i=0;i<numRows;i++) {
            for(int j=0;j<numCols;j++) {
            	imgInAry[i][j] = Integer.parseInt(inFile.next());
            	mirrorFramedAry[i+1][j+1] = imgInAry[i][j];
            }       
        }
        
    }
	
	void mirrorFramed(int numRows, int numCols,int[][] mirrorFramedAry){
		for(int i=0;i<numRows;i++) {
			for(int j=0;j<numCols;j++) {
				mirrorFramedAry[0][j] = mirrorFramedAry[1][j];
				mirrorFramedAry[numRows+1][j] = mirrorFramedAry[numRows][j];
				mirrorFramedAry[i][0] = mirrorFramedAry[i][1];
				mirrorFramedAry[i][numCols+1] = mirrorFramedAry[i][numCols];
			}
		}
		/*
		for(int i=0;i<numRows+2;i++) {
			for(int j=0;j<numCols+2;j++) {
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

