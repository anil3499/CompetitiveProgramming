package org.ap.datastrutures.longest_common_subsequence;

import static java.lang.Math.max;

public class LongestPalindromeSubstrings {
    int resultStart;
    int resultLength;

    public String longestPalindrome(String s) {
        int strLength = s.length();
        if (strLength < 2) {
            return s;
        }
        for (int start = 0; start < strLength - 1; start++) {
            expandRange(s, start, start);
            expandRange(s, start, start + 1);
        }
        return s.substring(resultStart, resultStart + resultLength);
    }

    private void expandRange(String str, int begin, int end) {
        while (begin >= 0 && end < str.length() &&
                str.charAt(begin) == str.charAt(end)) {
            begin--;
            end++;
        }

        if (resultLength < end - begin - 1) {
            resultStart = begin + 1;
            resultLength = end - begin - 1;
        }
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
        int result = new LongestPalindromeSubstrings().longestPalindrome(x,y,m,n);
        //Formula => LPA(a) = LCS(a,b)
        System.out.println(result);
    }

        public int longestPalindrome(String x, String y, int m, int n){
            if (m==0 || n==0){
                return 0;
            }
            for (int i= 1; i<m+1; i++){
                for( int j=1; j<n+1; j++){
                    if (x.toCharArray()[i - 1] == y.toCharArray()[j - 1]) {
                        return dp[i][j] = 1 + longestPalindrome(x, y, i-1, j-1);
                    } else {
                        return dp[i][j] = 0 + longestPalindrome(x, y, i-1, j-1); //if does not match we will add 0
                    }
                }
            }

            return dp[m][n];
        }
}
