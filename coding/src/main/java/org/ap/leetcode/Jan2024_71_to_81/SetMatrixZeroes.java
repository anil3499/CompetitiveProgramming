package org.ap.leetcode.Jan2024_71_to_81;

import java.util.HashSet;
import java.util.Set;

//https://leetcode.com/problems/set-matrix-zeroes/
public class SetMatrixZeroes {
    public void setZeroes(int[][] matrix) {
        Set<int[]> set = new HashSet<>();

        for(int i=0; i<matrix.length;i++) {
            for(int j=0; j<matrix[0].length;j++){
                if(matrix[i][j]==0){
                    set.add(new int[] {i,j});
                }
            }
        }
        for(int[] ij :set){
            int i= ij[0];
            int j = ij[1];
            for(int k=0; k<matrix[0].length; k++)
                matrix[i][k]=0;

            for(int k=0; k<matrix.length; k++)
                matrix[k][j]=0;

        }
    }
}
