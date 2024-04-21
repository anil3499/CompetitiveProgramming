package org.ap.leetcode.Dec2023_1_to_70;

import java.util.ArrayList;
import java.util.List;
//https://leetcode.com/problems/n-queens/description/
public class NQueens {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        for(int i=0; i<n;i++)
            for(int j=0;j<n;j++)
                board[i][j] ='.';
        solve(board,0,n,result,0);
        return result;
    }
    public void solve(char[][] board, int row,int n, List<List<String>> result, int queenPlaced){
        if(row>=n && queenPlaced==n){
            List<String> res= new ArrayList<>();
            for(int i=0;i<n;i++){
                StringBuilder rowstr = new StringBuilder();
                for(int j=0; j<n;j++){
                    rowstr.append(board[i][j]);
                }
                res.add(rowstr.toString());
            }
            result.add(res);
        }
        for(int i=0; i<n; i++){
            if(isSafe(board,n,row,i)){
                board[row][i] = 'Q';
                solve(board,row+1,n,result, queenPlaced+1);
                board[row][i] = '.';
            }
        }
    }
    public boolean isSafe(char[][] board,int n, int row, int col){
        if(row>=n) return false;
        //check row
        for(int i=0;i<n;i++){
            if(board[row][i]=='Q') return false;
        }
        //check column
        for(int i=0;i<n;i++){
            if(board[i][col]=='Q') return false;
        }

        //check diagonal
        for(int i=row, j=col; i<n && j<n;i++,j++){
            if(board[i][j]=='Q') return false;
        }
        for(int i=row, j=col; i>=0 && j>=0;i--,j--){
            if(board[i][j]=='Q') return false;
        }
        for(int i=row, j=col; i<n && j>=0;i++,j--){
            if(board[i][j]=='Q') return false;
        }
        for(int i=row, j=col; i>=0 && j<n; i--,j++){
            if(board[i][j]=='Q') return false;
        }
        return true;
    }
}
