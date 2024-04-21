package org.ap.datastrutures.dp.knapsack;
/*

logic :
cricket analogy
current will have 2 choices with condition run sud be greater than my capacity
1. if I contribute my run,  will team can do remaining run
2. or if I do not contribute. will check team run only
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class PrintAllPathsWithTargetSumSubset {

    public static class Pair{
        int i;
        int j;
        String psf;

        public Pair(int i, int j, String psf){
            this.i = i;
            this.j = j;
            this.psf = psf;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int tar = Integer.parseInt(br.readLine());

        boolean[][] dp = new boolean[arr.length + 1][tar + 1];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = true;
                } else if (i == 0) {
                    dp[i][j] = false;
                } else if (j == 0) {
                    dp[i][j] = true;
                } else {
                    if(dp[i - 1][j] == true){
                        dp[i][j] = true;
                    } else {
                        int val = arr[i - 1];
                        if (j >= val && dp[i - 1][j - val] == true) {
                            dp[i][j] = true;
                        }
                    }
                }
            }
        }

        System.out.println(dp[dp.length - 1][tar]);
        Queue<Pair> q = new ArrayDeque<>();
        q.add(new Pair(n,tar,""));


        while (q.size() > 0) {
            Pair rp = q.remove();
            if (rp.i == 0 || rp.j == 0) {
                System.out.println(rp.psf);
            } else {
                boolean exc = dp[rp.i - 1][rp.j];
                boolean inc = rp.j - arr[rp.i - 1] >= 0 ? dp[rp.i - 1][rp.j - arr[rp.i - 1]] : false;

                if (inc == true) {
                    q.add(new Pair(rp.i - 1, rp.j - arr[rp.i - 1], (rp.i - 1) + " " + rp.psf));
                }
                if (exc == true) {
                    q.add(new Pair(rp.i - 1, rp.j, rp.psf));
                }
            }
        }
    }
}












