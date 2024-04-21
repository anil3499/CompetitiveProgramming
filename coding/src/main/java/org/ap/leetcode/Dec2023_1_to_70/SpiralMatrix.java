package org.ap.leetcode.Dec2023_1_to_70;

import java.util.ArrayList;
import java.util.List;
//https://leetcode.com/problems/spiral-matrix/description/
public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int m=matrix[0].length;//row
        int n=matrix.length;//col
        int direction=0, i,j,top=0,bottom=n-1, left=0,  right=m-1;
        while(top<=bottom && left<=right){
            //topleft and bottomright for columns
            //bottom left and top right for row

            //move left to right
            if(direction==0){
                i=top;////fix row
                for(j=left; j<=right;j++ )
                    result.add(matrix[i][j]);
                top++;
            }

            //move top to bottom
            if(direction==1){
                j= right; //fix columns
                for(i= top; i<=bottom;i++)
                    result.add(matrix[i][j]);
                right--;
            }

            //left to right
            if(direction==2){
                i=bottom; //fix row
                for(j=right; j>=left; j--)
                    result.add(matrix[i][j]);
                bottom--;
            }

            //move bottom to top
            if(direction==3){
                j = left; ////fix columns
                for(i=bottom; i>=top;i--)
                    result.add(matrix[i][j]);
                left++;
            }
            direction = (direction+1)%4;
        }
        return result;
    }
}
