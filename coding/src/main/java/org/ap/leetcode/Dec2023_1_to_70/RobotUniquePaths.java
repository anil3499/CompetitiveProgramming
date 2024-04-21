package org.ap.leetcode.Dec2023_1_to_70;
//https://leetcode.com/problems/unique-paths/
public class RobotUniquePaths { public int uniquePaths2(int m, int n) {
    int[][] dp = new int[m+1][n+1];
    for(int i=0; i<=m; i++)
        for(int j=0; j<=n; j++)
            dp[i][j]=-1;

    return  move(0,0,m, n, dp);
}

    public int move(int row, int col, int m, int n, int[][] dp){
        if(row <0 || row >= m || col<0 ||col>=n ) {
            dp[row][col]=0;
            return dp[row][col];
        }
        if(row==m-1 && col==n-1) {
            dp[row][col]=1;
            return dp[row][col];
        }

        if(dp[row][col]!=-1)
            return dp[row][col];

        dp[row][col] = move(row+1, col, m,n ,dp) +  move(row, col+1, m,n, dp);

        return dp[row][col];
    }

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        for (int i = 0; i < n; i++) {
            dp[0][i] = 1; // fill first row with 1
            dp[i][0] = 1; //fill first columnwith 1
        }


        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.max(dp[i][j - 1] + 1, dp[i][j - 1] + dp[i - 1][j]);
            }
        }

        return dp[m - 1][n - 1];
    }
}
