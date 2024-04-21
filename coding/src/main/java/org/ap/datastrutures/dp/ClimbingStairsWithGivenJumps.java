package org.ap.datastrutures.dp;

import java.util.Scanner;

/*
https://www.pepcoding.com/resources/online-java-foundation/dynamic-programming-and-greedy/climb-stairs-with-variable-jumps-official/ojquestion
step1: storage and meaning
step2: identify direction
step3: travel and solve

here for each stair we will get no. of allowed jumps
 */
public class ClimbingStairsWithGivenJumps {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter stair count :");
        int n=sc.nextInt();
        int []jumps = new int[n];
        for(int i=0 ; i<n ; i++){
            jumps[i] = sc.nextInt();
        }
        int[] dp = new int[n + 1];
        dp[n] = 1;
        for (int i = n - 1; i >= 0; i--) {
            if (jumps[i] > 0) {
                for (int j = 1; j <= jumps[i] && i + j < dp.length; j++) {
                    dp[i] += dp[i + j];
                }
            }
        }

        System.out.println(dp[0]);
    }
}
