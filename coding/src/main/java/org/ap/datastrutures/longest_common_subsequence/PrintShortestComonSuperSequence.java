package org.ap.datastrutures.longest_common_subsequence;

import static java.lang.Math.max;

public class PrintShortestComonSuperSequence {
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
        String x = "abc";
        String y = "abe";
        int m = x.length();
        int n = y.length();
        dp = new int[m + 1][n + 1];

        //initilize dp metrix
        for (int i = 0; i < m + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                //FOR MEMOIZE APPROACH
                //dp[m][n] = -1;
                //fOR TOP DOWN APPROACH
                if (i == 0 || j == 0)
                    dp[m][n] = 0;
            }
        }
        int result = new SortestComonSuperSequence().LCS_top_down_approach(x, y, m, n);
        //shortest super sequence m+n -LCS
        //result will be
        System.out.println(m + n - result);

        //Print ShortestCSuper sequence
        String lcs = "";
        int i = m;
        int j = n;
        while (i > 0 && j > 0) {
            if (x.toCharArray()[i - 1] == y.toCharArray()[j - 1]) {
                i--;
                j--;
                lcs = lcs + x.toCharArray()[i - 1];
            } else {
                if (dp[i - 1][j] > dp[i][j - 1]) {
                    lcs = lcs + y.toCharArray()[j - 1];
                    j--;
                } else {
                    lcs = lcs + x.toCharArray()[i - 1];
                    i--; //if (dp[i][j-1] >dp[i-1][j])

                }
            }
        }
        while(i>0){
            lcs = lcs + x.toCharArray()[i - 1];
            i--; //if (dp[i][j-1] >dp[i-1][j])
        }
        while(j>0){
            lcs = lcs + y.toCharArray()[j - 1];
            j--; //if (dp[i][j-1] >dp[i-1][j])
        }
    }
}
