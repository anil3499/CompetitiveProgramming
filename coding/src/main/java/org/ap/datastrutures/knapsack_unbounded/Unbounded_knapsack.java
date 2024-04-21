package org.ap.datastrutures.knapsack_unbounded;

import static java.lang.Math.max;

public class Unbounded_knapsack {
    //================================Recursive Approach================================================================================================
    //recursion
    //    Choice Diagram
//                      Wt[n-1]
//                  /               \
//            wt[n-1] <= W          wt[n-1] > W
//              /       \               |
//             Yes      No              No
//
//  Base Condition
//Think about valid minimal value of input

    public static int [][] dp ;
    public static void main(String[] args) {
        int [] wt = {1,2,3,4,5};
        int [] val = {1,4,5,7,8};
        int W = 100;
        int n = 5;
        dp = new int [n+1][W+1];

        //initialize with -1 for recursion
        for(int i =0; i< n+1; i++){
            for(int j =0; j<W+1; j++){
                dp[i][j] = -1;
            }
        }

        //for top down approach
        for (int i = 0; i<n+1; i++){
            for (int j = 0; j<n+1; j++){
                if (i ==0 || j==0){
                    dp[i][j] = 0;
                }
            }
        }

        new Unbounded_knapsack().knapsack_top_down(wt,val,W,n);

    }
    //================================Top Down Approach================================================================================================
    //Its better only in some cases we may get stack overflow error due to recursive function calls
    //Convert recursive to topdown approach

    //base condition will convert to initialization of top down
    public int knapsack_top_down1(int wt[], int val[], int W, int n) {
        if (wt[n - 1] <= W) {
            //only dp[i-1][j - wt[i - 1]] will change to dp[i][j - wt[i - 1]]
            //because even if its processed we need to add the same eleement again
            dp[n][W] = max(val[n - 1] + dp[n][W - wt[n - 1]], dp[n - 1][W]);
        } else {
            dp[n][W] = dp[n - 1][W];
        }
        return dp[n][W];
    }

    //Convert n and W to i and j and add loop
    public int knapsack_top_down(int wt[], int val[], int W, int n) {
        for (int i =1; i<n+1; i++) {
            for (int j=1; j<W+1; j++){
                if (wt[i - 1] <= j) {
                    //only dp[i-1][j - wt[i - 1]] will change to dp[i][j - wt[i - 1]]
                    //because even if its processed we need to add the same eleement again
                    dp[i][j] = max(val[i - 1] + dp[i][j - wt[i - 1]], dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        return dp[n][W];
    }
}
