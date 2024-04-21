package org.ap.leetcode.Dec2023_1_to_70;
//https://leetcode.com/problems/spiral-matrix-ii/description/
public class SpiralMatrix_II {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];

        int top=0, bottom=n-1, left =0, right =n-1;
        int direction=0,counter=1;
        while(top <= bottom && left <= right){
            //System.out.println(" direction "+direction +" top "+top + " bottom "+ bottom +" left "+left +" right "+right +" counter "+counter);
            if(direction==0){
                for(int i=top; i<=right; i++)
                    matrix[top][i] = counter++;
                top++;
            }
            if(direction==1){
                for(int i=top;i<=bottom; i++)
                    matrix[i][right] = counter++;
                right--;
            }
            if(direction==2){
                for(int i=right; i>=left; i--)
                    matrix[bottom][i] = counter++;
                bottom--;
            }
            if(direction==3){
                for(int i=bottom;i>=top;i--)
                    matrix[i][left]=counter++;
                left++;
            }
            direction = (direction+1)%4;
        }
        return matrix;
    }
}
