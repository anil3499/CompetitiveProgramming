package org.ap.datastrutures.dp;
/*
LongestPalindromicSubstring.JPG
logic:
just compare first and last character and middle substring result will get from
c-1 and r+1 cell
here length of longest substring will : last true gap+1
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class LongestPalindromicSubstring {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        boolean[][] dp = new boolean[str.length()][str.length()];
        int len = 0;
        for(int g = 0; g < dp.length; g++){
            for(int i = 0, j = g; j < dp[0].length; i++, j++){
                if(g == 0){
                    dp[i][j] = true;
                } else if(g == 1){
                    if(str.charAt(i) == str.charAt(j)){
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = false;
                    }
                } else {
                    if(str.charAt(i) == str.charAt(j)){
                        dp[i][j] = dp[i + 1][j - 1];
                    } else {
                        dp[i][j] = false;
                    }
                }

                if(dp[i][j] && (g + 1) > len){
                    len = g + 1;
                }
            }
        }

        System.out.println(len);
    }
}



