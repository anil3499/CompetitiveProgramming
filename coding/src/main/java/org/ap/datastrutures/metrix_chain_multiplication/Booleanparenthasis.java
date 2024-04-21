package org.ap.datastrutures.metrix_chain_multiplication;

import java.util.HashMap;

public class Booleanparenthasis {  //EvaluateExpressionToTrue  also called this

    public int mcm_rec(String arr,int i,int j,int flag) {
        if (i > j) { // if string is empty or size 1 then also we can create 0 partition only so return will be 0
            return 0;
        }
        if (i == j) {
            if (flag == 1 & arr.toCharArray()[i] == 'T') {  //jo nikalna h vo and char me b same T/F h to true return kro
                return 1;
            } else if (flag == 0 & arr.toCharArray()[j] == 'F') {
                return 1;
            } else {
                return 0;
            }
        }
        String key = i + "_" + j + "_" + flag;
        if (map.get(key) != null)
            return map.get(key);
        else {
            int ans = 0;
            for (int k = i; k <= j - 1; k++) {
                int lt = mcm_rec(arr, i, k - 1, 1);
                /*key = i + "_" + (k - 1) + "_" + 1;
                int lt;
                if (map.get(key) != null) lt = map.get(key);
                else {
                    lt = mcm_rec(arr, i, k - 1, 1);
                    map.put(key, lt);
                }*/

                int lf = mcm_rec(arr, i, k - 1, 0);
                /*key = i + "_" + (k - 1) + "_" + 0;
                int lf;
                if (map.get(key) != null) lf = map.get(key);
                else {
                    lf = mcm_rec(arr, i, k - 1, 0);
                    map.put(key, lf);
                }*/

                int rt = mcm_rec(arr, k + 1, j, 1);
                /*key = k + 1 + "_" + j + "_" + 1;
                int rt;
                if (map.get(key) != null) rt = map.get(key);
                else {
                    rt = mcm_rec(arr, k + 1, j, 1);
                    map.put(key, rt);
                }*/

                int rf = mcm_rec(arr, k + 1, j, 0);
                /*key = k + 1 + "_" + j + "_" + 0;
                int rf;
                if (map.get(key) != null) rf = map.get(key);
                else {
                    rf = mcm_rec(arr, k + 1, j, 0);
                    map.put(key, rf);
                }*/

                //OPERAORS & | ^
                //& ==>true left and right both should be true
                //& ==>false left=true right=false, left=false right=true, left=false right=false
                if (arr.toCharArray()[k] == '&') {
                    if (flag == 1) {
                        ans = ans + lt * rf + lf * rt + lf * rf;
                    } else {
                        ans = ans + lt * rt;
                    }
                }

                //| ==>true left=true right=false, left=false right=true, left=true right=true
                //| ==>false left=false right=false
                if (arr.toCharArray()[k] == '&') {
                    if (flag == 1) {
                        ans = ans + lt * rt + lt * rf + lf * rt;
                    } else {
                        ans = ans + lf * rf;
                    }
                }
                //^ ==>true left=true right=false, left=false right=true
                //^ ==>false left=true right=true, left=false right=false
                if (arr.toCharArray()[k] == '^') {
                    if (flag == 1) {
                        ans = ans + lt * rf + lf * rt;
                    } else {
                        ans = ans + lt * rt + lf * rf;
                    }
                }

            }
            map.put(key, ans);
            return ans;
        }
    }
    //static int[][][] dp;
    static HashMap<String,Integer> map = new HashMap<String,Integer>();
    public static void main(String[] args) {
        String arr = "T|F&T^";
        //Find number of ways to add bracket so we get expression output as True
        int n = arr.length();
        //dp = new int[n+1][n+1][2];
        //initialize
        //for (int i =0; i<n; i++){
        //    for (int j =0 ; j<n; j++){
        //        for (int l =0 ; l<2; l++){
        //            dp[i][j][l] = -1;
        //    }
        //}
        int res =new Booleanparenthasis().mcm_rec(arr,0,n-1,1); //true =1, false =0
        System.out.println(res);
    }
}
