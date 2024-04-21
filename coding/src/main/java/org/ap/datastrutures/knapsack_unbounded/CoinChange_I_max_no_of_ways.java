package org.ap.datastrutures.knapsack_unbounded;

import static java.lang.Math.max;

public class CoinChange_I_max_no_of_ways {
//================================Recursive Approach================================================================================================
// recursion\
//  Choice Diagram
//                      Wt[n-1]
//                  /               \
//            wt[n-1] <= W          wt[n-1] > W
//              /       \               |
//             Yes      No              No
//
//  Base Condition
//Think about valid minimal value of input

    public static int [][] dp ;
    public int knapsack(int coin[], int W, int n) {
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < W + 1; j++) {
                if (coin[i - 1] <= j) {
                    //dp[i][j] = max(dp[i - 1][j - arr[i - 1]], dp[i - 1][j]);
                    //change in unbounded is only dp[i] instead of dp[i-1
                    // instead of max we used + beacuase we have been asked for count of number of ways
                    dp[i][j] = dp[i][j - coin[i - 1]] +  dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n][W];
    }

    public static void main(String[] args) {
        int coin[] = {2, 3, 7, 8, 10};
        int W = 11;
        int n = 5;  //len of arr
        dp = new int[n + 1][W + 1];
        //initalize
        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < W + 1; j++) {
                if (i == 0) {
                    dp[i][j] = 1;  //0 as False
                }
                if (j == 0) {
                    dp[i][j] = 0;  //1 as True
                }
            }
           int result =  new CoinChange_I_max_no_of_ways().knapsack(coin,W,n);
            System.out.println(result);
        }
    }
}
