package org.ap.datastrutures.longest_common_subsequence;

import static java.lang.Math.max;

public class LongestCommonSubstrings {
    //Top down approach
    //we dont need condition which we added in dp
    // just add for loop and convert m to i and n to j
    public int LCS_top_down_approach(String x, String y, int m, int n){
        if (m==0 || n==0){
            return 0;
        }
        for (int i= 1; i<m+1; i++){
            for( int j=1; j<n+1; j++){
                if (x.toCharArray()[i - 1] == y.toCharArray()[j - 1]) {
                    return dp[i][j] = 1 + LCS_top_down_approach(x, y, i-1, j-1);
                } else {
                    return 0;
                    // we have to retrun 0 if substring breaks
                    //  dp[i][j] = max(LCS_top_down_approach(x, y, i-1, j), LCS_top_down_approach(x, y, i, j-1));
                }
            }
        }

        return dp[m][n];
    }
    static int[][] dp;
    public static void main(String[] args) {
        String x="abc";
        String y = "abe";
        int m = x.length();
        int n = y.length();
        dp = new int[m+1][n+1];

        //initilize dp metrix
        for (int i =0; i< m+1; i++){
            for (int j =0; j< n+1; j++) {
                if (i==0 || j==0)
                    dp[m][n] = 0 ;
            }
        }
        new LongestCommonSubstrings().LCS_top_down_approach(x,y,m,n);
    }
}

