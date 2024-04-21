package org.ap.datastrutures.metrix.matrix;
/*
Input:
1 2 3
4 5 6
7 8 9
Output:
7 4 1
8 5 2
9 6 3

logic :
1 transpose the matrix (make all row as column)
common mistake : when we transpose matrix and run on whole matrix then it will reverse
matrix two times so make sure to run reverse on half of the matrix only either upper tringle
or lower tringal

2. reserve each row individually
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class RotateMatrixBy90Degree {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(br.readLine());
            }
        }

        rotateby90(arr);
        display(arr);
    }

    public static void rotateby90(int[][] arr){
        // transpose
        for(int i = 0; i < arr.length; i++){
            for(int j = i; j < arr[0].length; j++){
                int temp = arr[i][j];
                arr[i][j]= arr[j][i];
                arr[j][i] = temp;
            }
        }
        // reverse columns, row by row
        for(int i = 0; i < arr.length; i++){
            int li = 0;
            int ri = arr[0].length - 1;
            while(li <= ri){
                int temp = arr[i][li];
                arr[i][li] = arr[i][ri];
                arr[i][ri] = temp;
                li++;
                ri--;
            }
        }
    }
    public static void display(int[][] arr){
        for(int i = 0; i < arr.length; i++){
            for(int j = 0; j < arr[0].length; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }
}
