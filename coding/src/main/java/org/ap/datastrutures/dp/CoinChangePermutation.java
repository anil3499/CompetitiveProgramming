package org.ap.datastrutures.dp;
/*
Total Number of Ways to Get Amount
https://youtu.be/yc0LunmJA1A?list=PL-Jc9J83PIiG8fE6rj9F5a6uyQ5WPdqKy
{2 3 5} - coin
sum - 7
combination
[2 2 3]
[2 5]
permutation (here order matter)
[2 2 3]
[3 2 2]
[2 3 2]
[2 5]
[5 2]

logic diff:
combination:
here try 1 coin denomination for all sum at a time
and try same for other coins
permutation:
here try all coin on one sum then move
 */

import java.io.*;
import java.util.*;

public class CoinChangePermutation {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] coins = new int[n];

        for (int i = 0; i < n; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }

        int amt = Integer.parseInt(br.readLine());

        int[] dp = new int[amt + 1];
        dp[0] = 1;

        for (int i = 1; i < dp.length; i++) {
            for (int coin : coins) {
                if (i >= coin) {
                    dp[i] += dp[i - coin];
                }
            }
        }

        System.out.println(dp[amt]);
    }
}

















