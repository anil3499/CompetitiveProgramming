package org.ap.datastrutures.longest_common_subsequence;

import static java.lang.Math.max;

public class LongestPalindromeSubsequence {
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
        String x="agbcba";
        //longest palindromic subsequence
        //input is not matchg since only one string
        String y = new StringBuilder(x).reverse().toString(); //REVERSERe_of_A
        int m = x.length();
        int n = y.length();
        dp = new int[m+1][n+1];
        //Formula => LPA(a) = LCS(a,b)
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
        int result = new LongestPalindromeSubsequence().LCS_top_down_approach(x,y,m,n);
        //Formula => LPA(a) = LCS(a,b)
        System.out.println(result);
    }
}
