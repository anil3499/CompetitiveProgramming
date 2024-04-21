package org.ap.datastrutures.binarysearch;

public class SearchInRowWiseAndColumnwiseSortedArray {
    public static void main(String[] args) {
        /**
         * 10  20  30  40
         * 15  25  35  45
         * 27  29  37  48
         * 32  33  39  50
         *
         * */
    }
    class Pair{
        int i=-1;
        int j=-1;
    }
    public Pair findElementInSortedMatrix(int[][] mat, int m, int n, int key){
        int i=0, j=m-1;
        Pair pair = new Pair();
        while(i>=0 && j>=0 && i<m&& j<n){
            if(mat[i][j] ==key) {
                pair.i = i; pair.j = j;
                return pair;
            }else if(mat[i][j] >key) {
                j--;
            }else if(mat[i][j] <key){
                i++;
            }

        }
        return pair;
    }
}
