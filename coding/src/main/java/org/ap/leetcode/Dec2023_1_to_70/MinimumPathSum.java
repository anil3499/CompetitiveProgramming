package org.ap.leetcode.Dec2023_1_to_70;
//https://leetcode.com/problems/minimum-path-sum/
public class MinimumPathSum {
    Integer minimumSum = Integer.MAX_VALUE;
    public int minPathSum(int[][] grid) {
        int m= grid.length;
        int n = grid[0].length;

        int[][] memo = new int[m+1][n+1];
        for(int i=0; i<=m; i++)
            for(int j=0; j<=n; j++)
                memo[i][j]=-1;
        return findMinimimPathSum(grid,m,n,0,0,0,memo);
        //return minimumSum;
    }
    public int findMinimimPathSum(int[][]grid, int m, int n, int i, int j, int sum, int[][] memo){
        if(i<0 || j<0 || i>=m || j>=n) {
            memo[i][j]=Integer.MAX_VALUE;
            return memo[i][j];
        }

        //    // Check if we have already calculated the minimum path sum for this cell

        if(memo[i][j]!=-1)
            return memo[i][j];

        // Base case: we have reached the bottom right corner
        if(i==m-1 && j==n-1) {
            memo[i][j] =grid[i][j];
            //minimumSum =  memo[i][j];
            return memo[i][j];
        }
        // Store the minimum path sum for this cell in the memoization matrix
        memo[i][j] = grid[i][j]+ Math.min(findMinimimPathSum(grid,m,n,i+1,j,sum,memo), findMinimimPathSum(grid,m,n,i,j+1,sum,memo));

        // Return the minimum path sum
        return memo[i][j];
    }
}
