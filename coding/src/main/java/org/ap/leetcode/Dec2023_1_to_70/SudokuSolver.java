package org.ap.leetcode.Dec2023_1_to_70;
//https://www.youtube.com/watch?v=8lWxaRviJBA
//https://leetcode.com/problems/sudoku-solver/description/
public class SudokuSolver {
    public void solveSudoku(char[][] board) {
        char[] chars = new char[]{'1','2','3','4','5','6','7','8','9'};
        solve(board, chars);
    }
    public boolean isSafe(char[][] board, int row, int col, char ch){
        for(int i=0; i<board.length; i++){
            //row check
            if(board[row][i]==ch) return false;
            //col check
            if(board[i][col]==ch) return false;
            // 3*3 board check
            int rowChk = 3 *(row/3) + i/3;
            int colChk = 3*(col/3) + i%3;
            if(board[rowChk][colChk]==ch)
                return false;
        }
        return true;
    }

    public boolean solve(char[][] board, char[] chars) {
        for(int i=0;i<board.length; i++){
            for(int j=0; j<board.length; j++){
                if(board[i][j]!='.')
                    continue;
                for(char ch:chars){
                    if(isSafe(board,i,j,ch)){
                        board[i][j]=ch;
                        boolean solunPossible = solve(board,chars);
                        if(solunPossible) return true;
                        else
                            board[i][j]='.';

                    }
                }
                return false;
            }
        }
        return true;
    }
}
