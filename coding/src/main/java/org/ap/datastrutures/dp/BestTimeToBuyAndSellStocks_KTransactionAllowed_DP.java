package org.ap.datastrutures.dp;
/*
https://www.youtube.com/watch?v=3YILP-PdEJA&list=PL-Jc9J83PIiG8fE6rj9F5a6uyQ5WPdqKy&index=35
https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/
 */

import java.io.*;
import java.util.*;

public class BestTimeToBuyAndSellStocks_KTransactionAllowed_DP {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int k = Integer.parseInt(br.readLine());

        int[][] dp = new int[k + 1][n];

        for(int i = 1; i <= k; i++){
            int fadd = Integer.MIN_VALUE;

            for(int j = 1; j < n; j++){
                if(dp[i - 1][j - 1] - arr[j - 1] > fadd){
                    fadd = dp[i - 1][j - 1] - arr[j - 1];
                }

                if(fadd + arr[j] > dp[i][j - 1]){
                    dp[i][j] = fadd + arr[j];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }

        System.out.println(dp[k][n - 1]);
    }

}





