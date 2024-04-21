package org.ap.datastrutures.dp.lis;

/*
MinPerfectSquare.JPG
first check at x-1 (1^2) position
then check at x-4 (2^2) position and keep doing until x>n^2
and choose one with least length

 */

import java.util.Scanner;

public class MinPerfectSquare {

    public static int solution(int n){
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for(int i = 2; i <= n; i++){
            dp[i] = i; //As every number can be expressed as (1*1)+(1*1)....
            for(int j = 1; j * j <= i; j++){
                int squareVal = j*j;
                if(squareVal > i){
                    break;
                }else{
                    dp[i] = Math.min(dp[i], 1 + dp[i - squareVal]);
                }
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        System.out.println(solution(n));
    }



}



