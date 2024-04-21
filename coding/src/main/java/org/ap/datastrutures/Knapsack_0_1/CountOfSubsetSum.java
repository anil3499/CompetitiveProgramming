package org.ap.datastrutures.Knapsack_0_1;

import static java.lang.Math.max;

// Initialization
//
//recursion
//    Choice Diagram
//                       Wt[n-1]   n-1 because arr size is n
//                  /               \
//            wt[n-1] <= W          wt[n-1] > W
//              /       \               |
//             Yes      No              No
//
// if sum of a arr is odd then we cannot create 2 equal partitions, its always correct
public class CountOfSubsetSum {
    public static int dp[][];

    public int knapsack(int arr[], int W, int n) {
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < W + 1; j++) {
                if (arr[i - 1] <= j) {
                    //dp[i][j] = max(dp[i - 1][j - arr[n - 1]], dp[i - 1][j]);
                    dp[i][j] = dp[i - 1][j - arr[n - 1]] +  dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][W];
    }

    public static void main(String[] args) {
        int arr[] = {2, 3, 7, 8, 10};
        int W = 11;
        int n = 5;  //len of arr
        dp = new int[n + 1][W + 1];
        //initalize
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < W + 1; j++) {
                if (i == 0) {
                    dp[i][j] = 0;  //0 as False
                }
                if (j == 0) {
                    dp[i][j] = 1;  //1 as True
                }
            }
        }
    }
}