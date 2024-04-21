package org.ap.leetcode.Dec2023_1_to_70;
///https://leetcode.com/problems/climbing-stairs/
public class ClimbingStairs {


    public int climbStairs(int n) {
        int[] memo = new int[n+1];
        for(int i=0; i<n+1;i++)
            memo[i]=-1;
        return climbStairs1(n,memo);
    }

    public int climbStairs1(int n,int[] memo) {
        if(n==0){
            memo[n]=1;
            return 1;
        }
        if(n<0){
            return 0;
        }
        if(memo[n]!=-1) return memo[n];
        memo[n]= climbStairs1(n-1,memo) + climbStairs1(n-2,memo);
        return memo[n];
    }
    //1 1
    //2 2
    //3 111 12 21 3
    //4 1111 22 211 122 121

}
