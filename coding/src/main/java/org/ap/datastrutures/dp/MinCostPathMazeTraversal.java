package org.ap.datastrutures.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
MinCostPathMazeTraversal.JPG
https://www.geeksforgeeks.org/min-cost-path-dp-6/
logic :
divide matrix in 4 parts
1. last cell - keep value as it is
2. last row -> copy value in each cell from next column
3. last column -> copy value in each cell from next row
4. remaining cells -> min of (next row cell , next column cell)

 */
public class MinCostPathMazeTraversal {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][m];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(str.split(" ")[j]);
            }
        }

        int[][] dp = new int[arr.length][arr[0].length];
        for (int i = n-1; i >= 0; i--) {
            for (int j = m-1; j >= 0; j--) {
                if(i==n-1 && j==m-1){
                    dp[i][j]=arr[i][j];
                }else if(i==n-1){
                    dp[i][j]=arr[i][j]+dp[i][j+1];
                }else if(j==m-1){
                    dp[i][j]=arr[i][j]+dp[i+1][j];
                }else{
                    dp[i][j]=arr[i][j] + Math.min(dp[i+1][j],dp[i][j+1]);
                }
            }
        }
        System.out.println(dp[0][0]);


    }
}
