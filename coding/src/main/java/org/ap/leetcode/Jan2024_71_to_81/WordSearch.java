package org.ap.leetcode.Jan2024_71_to_81;
//https://leetcode.com/problems/word-search/
public class WordSearch {
    public boolean exist(char[][] board, String word) {
        int m= board.length;
        int n= board[0].length;

        int[][] dirs = {{0,1},{1,0},{-1,0},{0,-1}};
        for(int i=0;i<m;i++) {
            for(int j=0;j<n; j++){
                if(word.charAt(0) == board[i][j]  &&  wordExist(board, i,j, 0, word,dirs))
                    return true;
            }
        }
        return false;
    }
    public boolean wordExist(char [][] board, int i, int j,int wordIdx, String word,int[][] dirs){
        if(wordIdx==word.length())
            return true;
        if(i>=board.length ||j>=board[0].length ||i <0 || j<0 ||  board[i][j] == '$')
            return false;

        if(board[i][j]!=word.charAt(wordIdx))
            return false;

        char ch = board[i][j];
        board[i][j] = '$';

        for(int [] dir : dirs) {
            int new_i = i + dir[0];
            int new_j = j + dir[1];
            if(wordExist(board, new_i,new_j,wordIdx+1,word,dirs))
                return true;
        }
        board[i][j]= ch;

        return false;
    }
}
