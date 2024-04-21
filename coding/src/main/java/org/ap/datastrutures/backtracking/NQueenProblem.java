package org.ap.datastrutures.backtracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/*
logic :
number of diagonal 2*n-1
normal diagonal (/) : row+column
reverse diagonal (\) : row - column + (array.length -1)
 */

public class NQueenProblem {

    /*private static void placeQueen(boolean[][] board, int row,
                                   boolean[] columns, boolean[] ndiag, boolean[] rdiag, String ans) {
        if(row == board.length){
            System.out.println(ans+".");
            return;
        }
        for(int col=0; col<board[0].length;col++){
            if(columns[col] == false &&  ndiag[row+col] == false &&  rdiag[row-col+board.length-1] == false){
                board[row][col]=true;
                columns[col] = true;
                ndiag[row+col] = true ;
                rdiag[row-col+board.length-1] = true;
                placeQueen(board,row+1,columns,ndiag,rdiag,ans+row+"-"+col+",");
                board[row][col] = false;
                columns[col] = false;
                ndiag[row+col] = false ;
                rdiag[row-col+board.length-1] = false;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter board size: ");
        int n=sc.nextInt();
        boolean[][] board=new boolean[n][n];
        boolean []columns=new boolean[n];
        boolean [] ndiag=new boolean[2*n-1];
        boolean [] rdiag=new boolean[2*n-1];
        placeQueen(board, 0, columns,ndiag,rdiag, "");
    }*/
//==========================================================================================================================
    static List<List<String>> ans = new ArrayList<>();
    public static boolean inbound(int i, int j , int n) {
        if(i<0 || j<0 || i>=n || j>=n ) return false;
        return true;
    }
    public static boolean isSafe(int row, int col, char[][] temp, int n) {
        //check same row and col
        for(int k=0; k<n; k++) {
            if(temp[k][col]=='Q' || temp[row][k] =='Q')
                return false;
        }
        //check diagonal
        for(int l=0;l < n; l++) {
                if(inbound(row-l,col-l,n) && temp[row-l][col-l] =='Q') return false;
                if(inbound(row+l,col-l,n) && temp[row+l][col-l] =='Q') return false;
                if(inbound(row+l,col+l,n) && temp[row+l][col+l] =='Q') return false;
                if(inbound(row-l,col+l,n) && temp[row-l][col+l] =='Q') return false;
            }
        return true;
    }


    //we will go row by row to place queen
    public static void backtrack(char[][] temp, int n, int row){
        if(row==n) {
            List<String> board = new ArrayList<>();
            for(int i=0; i<n;i++) {
                String rowstr = "";
                for(int j=0; j<n;j++) {
                    rowstr += temp[i][j];
                }
                board.add(rowstr);
            }
            ans.add(board);
            return;
        }

        for(int i=0;i<n;i++) {
            if(isSafe(row,i,temp,n)) {
                temp[row][i] = 'Q';
                backtrack(temp,n,row+1);
                temp[row][i] = '.';
            }
        }
    }

    public static List<List<String>> placeQueens(int n) {
        char[][] temp = new char[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                temp[i][j] = '.';

        backtrack(temp,n,0);

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(placeQueens(4));
    }
}
/*
* leetcode- https://leetcode.com/problems/n-queens/
* youtube- https://www.youtube.com/watch?v=vDNVIJz9RUg&t=715s
* */