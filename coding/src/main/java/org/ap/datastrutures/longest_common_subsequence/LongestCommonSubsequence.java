package org.ap.datastrutures.longest_common_subsequence;

//Insubsequence we can skip worrds but in substring it should be connected
//
//smlaller input, n-1 pe call krege
//
//Base condition - Think of smallest valid input
//input is string x and y
// size can be empty for x and y
//
//if len(x)==0 || len(y)==0  ===> return lcs = 0
//
//
//Choice diagram
//                          x[m-1] == y[n-1]
//                       /                      \
//                  Yes                          No THEN WE HAVE  CHOICES
//          |                                          /                              \
//x.substring(0,n-1) ,y.substring(0,n-1)     x.substring(0,n) ,y.substring(0,n-1)        x.substring(0,n-1) ,y.substring(0,n)

import static java.lang.Math.max;

public class LongestCommonSubsequence {
    //recursive version
    public int LCS(String x, String y, int m, int n){
        if (m==0 || n==0){
            return 0;
        }
        if(x.toCharArray()[m-1] ==y.toCharArray()[n-1]){
             return 1 + LCS(x,y,m-1,n-1);
        } else {
            return max(LCS(x,y,m-1,n) , LCS(x,y,m,n-1));
        }
    }
    //Memoize version, converting to DP
    //Add condition if element already exist in DP then no need to evaluate
    public int LCS_memoize(String x, String y, int m, int n){
        if (m==0 || n==0){
            return 0;
        }
        if (dp[m][n] == -1) {
            if (x.toCharArray()[m - 1] == y.toCharArray()[n - 1]) {
                return dp[m][n] = 1 + LCS_memoize(x, y, m - 1, n - 1);
            } else {
                return dp[m][n] = max(LCS_memoize(x, y, m - 1, n), LCS_memoize(x, y, m, n - 1));
            }
        }
        return dp[m][n];
    }
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
                    return dp[i][j] = max(LCS_top_down_approach(x, y, i-1, j), LCS_top_down_approach(x, y, i, j-1));
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
                //FOR MEMOIZE APPROACH
                //dp[m][n] = -1;
                //fOR TOP DOWN APPROACH
                if (i==0 || j==0)
                    dp[m][n] = 0 ;
            }
        }
        new LongestCommonSubsequence().LCS_top_down_approach(x,y,m,n);

    }
}
