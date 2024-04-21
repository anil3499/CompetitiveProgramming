package org.ap.datastrutures.metrix_chain_multiplication;

import java.util.HashMap;

public class ScrambleString {
    public boolean solve_rec(String a, String b){
        //Base condition
        //think of smallest input

        //if both empty then its scrambles
        //if both string are equal then strings are scramble
        //if any one has length has <=1 thren cant be scramble

        if (a.equals(b)) return true;
        if (a.length()<=1) return false;
        boolean flag = false;
        for (int i =1; i<a.length(); i++) {
            //swapping ho rhi h
            //gr eat    & eat gr
            if (solve_rec(a.substring(0, i),b.substring(b.length() - i, i)) &&
                    solve_rec(a.substring(i, a.length() - i) , b.substring(0, b.length() - i))) {
                flag = true;
            }
            //swapping nhi ho rhi h
            //gr eat    & gr eat
            if (solve_rec(a.substring(0, i) , b.substring(0, i)) &&
                    solve_rec(a.substring(i, a.length() - i) , b.substring(i, b.length() - i))) {
                flag = true;
            }
            if (flag ==true) break;
        }
        return flag;
    }

    public boolean solve(String a, String b){
        //Base condition
        //think of smallest input

        //if both empty then its scrambles
        //if both string are equal then strings are scramble
        //if any one has length has <=1 thren cant be scramble

        if (a.equals(b)) return true;
        if (a.length()<=1) return false;
        if (map.get(a+"_"+b) == null) {
            boolean flag = false;
            for (int i = 1; i < a.length(); i++) {
                //swapping ho rhi h
                //gr eat    & eat gr
                if (solve(a.substring(0, i), b.substring(b.length() - i, i)) &&
                        solve(a.substring(i, a.length() - i), b.substring(0, b.length() - i))) {
                    flag = true;
                }
                //swapping nhi ho rhi h
                //gr eat    & gr eat
                if (solve(a.substring(0, i), b.substring(0, i)) &&
                        solve(a.substring(i, a.length() - i), b.substring(i, b.length() - i))) {
                    flag = true;
                }
                if (flag == true) break;
            }
            map.put(a+"_"+b,flag);
        }
        return map.get(a+"_"+b);
    }

    static HashMap<String,Boolean> map = new HashMap<String,Boolean>();
    public static void main(String[] args) {
        String a = "great";
        String b = "rgeat";
        //if length is diffrent then cant be scramble string
        if (a.length() != b.length())
            System.out.println("Strings are not scrambled");

        //are a nd b are scrambed strings
        //constrain 1 - crreate binary tree of string
        //Constraint 2 no child can be empty
        //      great
        //       /   \
        //     gr     eat
        //     / \      / \
        //    g    r    e   at             //after swapping gr can be rg --> when it will go up rgeat and another given string should be same then its called scrambeled string
        //                  /  \
        //                  a   t
        //non leaf node  -- we can do swapping  any n of

        // int n = arr.length();
        //dp = new int[n+1][n+1][2];
        //initialize
        //for (int i =0; i<n; i++){
        //    for (int j =0 ; j<n; j++){
        //        for (int l =0 ; l<2; l++){
        //            dp[i][j][l] = -1;
        //    }
        //}
        boolean res =new ScrambleString().solve(a,b); //true =1, false =0
        System.out.println(res);
    }
}
