package org.ap.leetcode.Jan2024_71_to_81;
//https://leetcode.com/problems/search-a-2d-matrix/description/
public class SearchA2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        int i=0, j=matrix[0].length-1;
        while(i>=0 && i<matrix.length && j >=0 && j<matrix[0].length){
            if(matrix[i][j] == target)
                return true;
            else if(matrix[i][j]> target){
                j--;
            }else {
                i++;
            }
        }
        return false;
    }
}
