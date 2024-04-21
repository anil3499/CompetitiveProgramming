package org.ap.datastrutures.longest_common_subsequence;

import static java.lang.Math.max;

public class LongestRepeatingSubsequence {
    public int LCS_top_down_approach(String x, String y, int m, int n) {
        if (m == 0 || n == 0) {
            return 0;
        }
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                //only need to add i!=j condition
                if (x.toCharArray()[i - 1] == y.toCharArray()[j - 1] && i!=j) {
                    return dp[i][j] = 1 + LCS_top_down_approach(x, y, i - 1, j - 1);
                } else {
                    return dp[i][j] = max(LCS_top_down_approach(x, y, i - 1, j), LCS_top_down_approach(x, y, i, j - 1));
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
                if (i == 0 || j == 0)
                    dp[m][n] = 0;
            }
        }
        int res =new PrintLongestComonSubsequence().LCS_top_down_approach(x, y, m, n);
        System.out.println(res);
        }
}
