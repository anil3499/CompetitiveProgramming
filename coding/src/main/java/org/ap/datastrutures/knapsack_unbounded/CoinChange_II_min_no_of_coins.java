package org.ap.datastrutures.knapsack_unbounded;

import static java.lang.Math.min;

//Get minimum nuber of coins required for the change
public class CoinChange_II_min_no_of_coins {
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
                    dp[i][j] = min( dp[i][j - coin[i - 1]] ,  dp[i - 1][j]);
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
                    dp[i][j] = 0;  //0 as False
                }
                if (j == 0) {
                    dp[i][j] = Integer.MAX_VALUE-1;  //1 as True
                }
            }
            //Special case for coin count problem
            i=1;
            for(int j =1; j<W+1; j++){
                if(j % coin[0] ==0)
                    dp[i][j] = i/coin[0];
                else
                    dp[i][j] = Integer.MAX_VALUE-1;
            }
            int result =  new CoinChange_II_min_no_of_coins().knapsack(coin,W,n);
            System.out.println(result);
        }
    }
}
