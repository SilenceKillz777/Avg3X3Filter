//Steven Yu
//CS381 - Image Processing

import java.io.PrintWriter;
import java.util.Scanner;

public class imageProcessing {
	int numRows, numCols, minVal, maxVal, newMin, newMax;
	int[][] imgInAry, imgOutAry, mirrorFramedAry, tempAry;
	int[] hist, neighborAry;
	Scanner inFile;
	PrintWriter outFile, histogram;
	
	//Constructor
	imageProcessing(int numRows,int numCols,int minVal,int maxVal, Scanner inFile, PrintWriter outFile, PrintWriter histogram){
		this.numRows = numRows;
		this.numCols = numCols;
		this.minVal = minVal;
		this.maxVal = maxVal;
		newMin = maxVal;
		newMax = minVal;
		imgInAry= new int [numRows][numCols];
		imgOutAry = new int [numRows][numCols];
		mirrorFramedAry = new int [numRows+2][numCols+2];
		tempAry = new int [numRows+2][numCols+2];
		hist = new int[maxVal+1];
		neighborAry = new int[9];
		this.inFile = inFile;
		this.outFile = outFile;
		this.histogram = histogram;
	}
	
	//Methods
	void loadImage(int[][] imgInAry,int[][] mirrorFramedAry) {
        for(int i=0;i<numRows;i++) {
            for(int j=0;j<numCols;j++) {
            	imgInAry[i][j] = Integer.parseInt(inFile.next());
            	mirrorFramedAry[i+1][j+1] = imgInAry[i][j];
            }       
        }
    }
	
	void ComputeHistogram(int[][] imgInAry, int[] hist,int maxVal) {
		for(int i=0;i<numRows;i++){
			for(int j=0;j<numCols;j++) {
				hist[imgInAry[i][j]]++;
			}
		}
	}
	
	void printHist(int[] hist) {
		histogram.println(numRows + " " + numCols + " " + newMin + " " + newMax);
		for(int i=0;i<maxVal+1;i++)
			histogram.println(i + " " + hist[i]);
		histogram.close();
	}
	
	void mirrorFramed(int numRows, int numCols,int[][] mirrorFramedAry){
		for(int i=0;i<numRows;i++) {
			for(int j=0;j<numCols;j++) {
				mirrorFramedAry[0][j] = mirrorFramedAry[1][j];					//mirror top
				mirrorFramedAry[numRows+1][j] = mirrorFramedAry[numRows][j];	//mirror bottom
				mirrorFramedAry[i][0] = mirrorFramedAry[i][1];					//mirror left
				mirrorFramedAry[i][numCols+1] = mirrorFramedAry[i][numCols];	//mirror right
			}
		}
	}
	
	void computeAVG3X3(int[][] mirrorFramedAry, int[][]tempAry) {
		for(int i=1;i<numRows+1;i++) {
			for(int j=1;j<numCols+1;j++) {
				int sum = 0;
				neighborAry[0] = mirrorFramedAry[i][j];			//center
				neighborAry[1] = mirrorFramedAry[i-1][j-1];		//top-left
				neighborAry[2] = mirrorFramedAry[i][j-1];		//middle-left
				neighborAry[3] = mirrorFramedAry[i+1][j-1];		//bottom-left
				neighborAry[4] = mirrorFramedAry[i-1][j];		//top-middle
				neighborAry[5] = mirrorFramedAry[i+1][j];		//bottom-middle
				neighborAry[6] = mirrorFramedAry[i-1][j+1];		//top-right
				neighborAry[7] = mirrorFramedAry[i][j+1];		//middle-right
				neighborAry[8] = mirrorFramedAry[i+1][j+1];		//bottom-right
				for(int k=0;k<9;k++) {
					sum += neighborAry[k];
				}
				tempAry[i][j] = sum/9;
				if (tempAry[i][j]<newMin)
					newMin = tempAry[i][j];
				if (tempAry[i][j]>newMax)
					newMax = tempAry[i][j];
			}
		}
	}
	
	void computeThreshold(int[][] mirrorFramedAry, int[][] imgOutAry, int thr_value) {
		for(int i=1;i<numRows+1;i++) {
			for(int j=1;j<numCols+1;j++) {
				if(mirrorFramedAry[i][j]>=thr_value)
					imgOutAry[i-1][j-1]=1;
				else imgOutAry[i-1][j-1] = 0;
			}
		}
	}
	
	void prettyPrint(int[][] imgOutAry) {
		outFile.println(numRows + " " + numCols + " " + newMin + " " + newMax);
		for(int i=0;i<numRows;i++) {
			for(int j=0;j<numCols;j++) {
				if(imgOutAry[i][j] > 0)outFile.print(imgOutAry[i][j]);
				else outFile.print("  ");
			}
			outFile.println();			
		}
		outFile.close();
	}
}
