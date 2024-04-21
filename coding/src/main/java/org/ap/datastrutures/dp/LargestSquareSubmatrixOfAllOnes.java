package org.ap.datastrutures.dp;

import java.util.Scanner;

/*
LargestSquareSubmatrixOfAllOnes.JPG

diff from MaxAreaRectangleInBinaryMatrix is that
here row*col should be same
logic :
1. fill all zero as it is in dp array
2. fill last colmn and last row as it is
3. now start filling from down to up
4. cell[i][j] = 1 + min (cell[i+1][j] , cell[i][j+1], cell[i+1][j+1])
5. cell with max value will be the top left point of largest square submatrix

 */
public class LargestSquareSubmatrixOfAllOnes {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scn.nextInt();
        }
        int k = scn.nextInt();
        System.out.println(solution(arr, k));
    }

    public static int solution(int[] arr, int k) {
        int[] maxSum = new int[arr.length];
        // use kadane's
        maxSum[0] = arr[0];
        for(int i = 1 ; i < arr.length; i++) {
            maxSum[i] = Math.max(arr[i], maxSum[i - 1] + arr[i]);
        }

        int sum = 0 ;
        for(int i = 0 ; i < k; i++) {
            sum += arr[i];
        }

        int ans = sum;;
        for(int i = k ; i < arr.length; i++) {
            sum = sum + arr[i] - arr[i - k];
            ans = Math.max(ans, sum);
            ans = Math.max(ans, sum + maxSum[i - k]);
        }

        return ans;
    }
    //pepcoding
    public static int solutionUsingDP(int[][] arr, int k) {
       int [][]dp=new int[arr.length][arr[0].length];
       int ans=Integer.MIN_VALUE;
       for(int i=dp.length-1 ; i>=0 ;i--){
            for(int j = dp[0].length -1 ; j>=0 ; i--){
                //last cell
                if(i==dp.length-1 && j==dp[0].length-1){
                    dp[i][j] = arr[i][j];
                }else if(i==dp.length-1){ // last row
                    dp[i][j] = arr[i][j];
                }else if(j==dp[0].length-1){ //last column
                    dp[i][j] = arr[i][j];
                }else{ //rest of it
                        if(arr[i][j] == 0){
                            dp[i][j] = 0;
                        }else{
                            int min = Math.min(dp[i][j+1] ,dp[i+1][j]); // min b/w right and down
                            min = Math.min(min, dp[i-1][j-1]); // min b/w min and dailoganal
                            dp[i][j]=min+1;
                        }
                }
            }
       }
       return ans;
    }

}