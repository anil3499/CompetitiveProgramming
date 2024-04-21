package org.ap.leetcode.Dec2023_1_to_70;
//https://leetcode.com/problems/rotate-image/
public class RotateImage { //RotateMatrix
    public void rotate(int[][] matrix) {
        //First transpose the matrix
        for(int i=0; i<matrix.length; i++){
            for(int j=i; j<matrix.length; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        //then reverse every row that will be our ans
        for(int k=0; k<matrix.length;k++){
            for(int i=0, j=matrix.length-1 ; i<j; i++,j--) {
                int temp = matrix[k][i];
                matrix[k][i] = matrix[k][j];
                matrix[k][j] = temp;
            }
        }
    }
}
