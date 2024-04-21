package org.ap.leetcode.Dec2023_1_to_70;

import java.util.HashSet;
import java.util.Set;

public class SudokuValidate {
    //[["5","3",".",".","7",".",".",".","."],
    // ["6",".",".","1","9","5",".",".","."],
    // [".","9","8",".",".",".",".","6","."],
    // ["8",".",".",".","6",".",".",".","3"],
    // ["4",".",".","8",".","3",".",".","1"],
    // ["7",".",".",".","2",".",".",".","6"],
    // [".","6",".",".",".",".","2","8","."],
    // [".",".",".","4","1","9",".",".","5"],
    // [".",".",".",".","8",".",".","7","9"]]

    public boolean isValidSudoku(char[][] board) {
        Set<String> set =new HashSet<>();
        for(int i=0; i<board.length; i++){
            for(int j=0;j<board.length;j++) {
                char ch=board[i][j];
                if(ch!='.'){
                    if(set.add( ch + " in row:"+i)==false) return false;
                    if(set.add(ch + " in column:"+j)==false) return false;
                    if(set.add(ch + " in block: " +i/3 +"-" +j/3)==false) return false;
                }
            }
        }
        return true;
    }

    //TC O n to power 2

    public boolean isValidSudoku1(char[][] board) {
        int validSum =9*(9+1)/2;

        int leftStart =0;
        int leftEnd = 3;

        while(leftEnd<=board.length){
            int rightStart=0;
            int rightEnd = 3;
            while(rightEnd<=board.length){
                //for validating each 3x3 matrix
                Set<Character> set = new HashSet<>();
                for(int i=leftStart; i < leftEnd; i++) {
                    for(int j=rightStart; j< rightEnd; j++) {
                        char num =board[i][j];
                        if(num!='.' && set.add(num)==false) return false;
                    }
                }
                rightStart+=3;
                rightEnd+=3;
            }
            leftStart+=3;
            leftEnd+=3;
        }
        //validate each row and each column
        for(int i=0; i<board.length;i++) {
            Set<Character> colset = new HashSet<>();
            Set<Character> rowset = new HashSet<>();
            for(int j=0;j<board.length; j++) {
                if(board[i][j]!='.' && rowset.add(board[i][j])==false) return false;
                if(board[j][i]!='.' && colset.add(board[j][i])==false) return false;
            }
        }
        return true;
    }
}
