package org.ap.datastrutures.metrix_chain_multiplication;

import static java.lang.Math.*;

public class EggDroppingProblem {
    public int egg_problem_rec(int e, int f){
        //base condition
        if (e==1) return f;
        //if only 1 egg given then we have to start from 1st floor and chek for every one so number of attempt will be same as number of floors
        if (f==0|| f==1) return f;
        //if f=1 the we have to attempt will be f only

        // from k floor egg dropped
        //   /    \
        //  break     dint break
        // if break then we have to chek below k (k-1) only and egg will be e-1
        //dint break then we have to check above k (k+1) and egg = e
        int min = Integer.MAX_VALUE;
        for( int k=1; k<=f; k++) {
            int temp =max(egg_problem_rec(e - 1, k - 1) ,egg_problem_rec(e, k + 1)); //ax because hame worst case me nikalna h
            min = min(min,temp);
        }
        return min;
    }
    public int egg_problem(int e, int f){
        //base condition
        if (e==1) return f;
        //if only 1 egg given then we have to start from 1st floor and chek for every one so number of attempt will be same as number of floors
        if (f==0|| f==1) return f;
        //if f=1 the we have to attempt will be f only

        if(dp[e][f]==-1) {
            // from k floor egg dropped
            //   /    \
            //  break     dint break
            // if break then we have to chek below k (k-1) only and egg will be e-1
            //dint break then we have to check above k (k+1) and egg = e
            int min = Integer.MAX_VALUE;
            for (int k = 1; k <= f; k++) {
                int temp = max(egg_problem(e - 1, k - 1), egg_problem(e, k + 1)); //ax because hame worst case me nikalna h
                min = min(min, temp);
            }
            dp[e][f] = min;
        }
        return dp[e][f];
    }

    public int egg_problem_optimized(int e, int f){
        //base condition
        if (e==1) return f;
        //if only 1 egg given then we have to start from 1st floor and chek for every one so number of attempt will be same as number of floors
        if (f==0|| f==1) return f;
        //if f=1 the we have to attempt will be f only

        if(dp[e][f]==-1) {
            // from k floor egg dropped
            //   /    \
            //  break     dint break
            // if break then we have to chek below k (k-1) only and egg will be e-1
            //dint break then we have to check above k (k+1) and egg = e
            int min = Integer.MAX_VALUE;
            for (int k = 1; k <= f; k++) {
                int temp = max(egg_problem_optimized(e - 1, k - 1), egg_problem_optimized(e, k + 1)); //ax because hame worst case me nikalna h
                min = min(min, temp);
            }
            dp[e][f] = min;
        }
        return dp[e][f];
    }
    static int[][] dp;
    public static void main(String[] args) {
        int e = 3;
        int f =10;
        dp = new int[e+1][f+1];
        for (int i=0; i<e+1; i++){
            for (int j=0;j<f+1;j++){
                dp[i][j]=-1;
            }
        }
        //if length is diffrent then cant be scramble string
        if (e==0)
            System.out.println("invalid scenario");


        int res =new EggDroppingProblem().egg_problem_optimized(e,f); //true =1, false =0
        System.out.println(res);
    }
}
