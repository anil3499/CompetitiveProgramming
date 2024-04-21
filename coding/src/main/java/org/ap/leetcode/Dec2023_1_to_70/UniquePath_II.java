package org.ap.leetcode.Dec2023_1_to_70;
//https://leetcode.com/problems/unique-paths-ii/description/
public class UniquePath_II {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // cases

        int m=obstacleGrid.length;
        int n = obstacleGrid[0].length;
        System.out.println("m "+m +" n "+n);
        if(obstacleGrid[m-1][n-1] == 1) return 0;

        int[][] memo = new int[m+1][n+1];
        for(int i=0;i<m; i++)
            for(int j=0; j<n;j++)
                memo[i][j]=-1;
        return findPath(obstacleGrid, m, n, 0, 0, memo);
    }
    public int  findPath(int[][]obstacleGrid, int m, int n,int i, int j, int[][] memo){
        if(i<0 || i>=m || j<0 || j>=n){
            memo[i][j]=0;
            return memo[i][j];
        }
        if(memo[i][j]!=-1)
            return memo[i][j];
        System.out.println(i+" "+j);

        if(i==m-1 && j==n-1){
            memo[i][j]=1;
            return memo[i][j];
        }
        if(obstacleGrid[i][j] == 1) {
            memo[i][j]=0;
            return memo[i][j];
        }

        memo[i][j] =  findPath(obstacleGrid, m, n, i+1, j, memo) +  findPath(obstacleGrid, m, n, i, j+1, memo);
        return memo[i][j];

    }
}
