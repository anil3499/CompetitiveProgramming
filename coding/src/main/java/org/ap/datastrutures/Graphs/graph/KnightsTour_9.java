package org.ap.datastrutures.Graphs.graph;


import java.io.*;
import java.util.*;

public class KnightsTour_9 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int r = Integer.parseInt(br.readLine());
        int c = Integer.parseInt(br.readLine());

        int[][] chess = new int[n][n];
        printKnightsTour(chess, r, c, 1);
    }

    public static void printKnightsTour(int[][] chess, int r, int c, int upcomingMove){
        if(r < 0 || c < 0 || r >= chess.length || c >= chess.length || chess[r][c] != 0){
            return;
        } else if(upcomingMove == chess.length * chess.length){
            chess[r][c] = upcomingMove;
            displayBoard(chess);
            chess[r][c] = 0;
            return;
        }

        chess[r][c] = upcomingMove;
        printKnightsTour(chess, r - 2, c + 1, upcomingMove + 1);
        printKnightsTour(chess, r - 1, c + 2 , upcomingMove + 1);
        printKnightsTour(chess, r + 1, c + 2 , upcomingMove + 1);
        printKnightsTour(chess, r + 2, c + 1 , upcomingMove + 1);
        printKnightsTour(chess, r + 2, c - 1 , upcomingMove + 1);
        printKnightsTour(chess, r + 1, c - 2 , upcomingMove + 1);
        printKnightsTour(chess, r - 1, c - 2 , upcomingMove + 1);
        printKnightsTour(chess, r - 2, c - 1 , upcomingMove + 1);
        chess[r][c] = 0;
    }

    public static void displayBoard(int[][] chess){
        for(int i = 0; i < chess.length; i++){
            for(int j = 0; j < chess[0].length; j++){
                System.out.print(chess[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();
    }
}






