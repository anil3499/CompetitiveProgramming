package org.ap.datastrutures.recursion;
/*
https://www.youtube.com/watch?v=R1URUB6_y2k&list=PL-Jc9J83PIiFxaBahjslhBD1LiJAV7nKs&index=47
//https://leetcode.com/problems/flood-fill/description/
 */
public class FloodFillPath {

    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int color = image[sr][sc];
        if(color != newColor) {
            dfs(image, sr,sc, color, newColor);
        }
        return image;
    }


    /*
    Time Complexity:O(N), whereNis the number of pixels in the image. We might process every pixel.
    Space Complexity:O(N), the size of the implicit call stack when callingdfs.
*/
    public void dfs(int[][] image, int r, int c , int color, int newColor) {
        if(r < 0 || c < 0 || r >= image.length || c >= image[0].length) {
            return;
        }
        // we have condition that it should change only color if [r][c] has old color no other value
        if (image[r][c]==color) {
            image[r][c] = newColor;
            dfs(image, r - 1, c, color, newColor);
            dfs(image, r, c - 1, color, newColor);
            dfs(image, r + 1, c, color, newColor);
            dfs(image, r, c + 1, color, newColor);
        }
    }
    public void dfs1(int[][] image, int r, int c , int color, int newColor) {
        if (image[r][c] == color) {
            image[r][c] = newColor;

            if (r-1 >= 0) {
                dfs1(image, r - 1, c, color, newColor);
            }
            if (c-1 >= 0) {
                dfs1(image, r, c - 1, color, newColor);
            }
            if (r+1 < image.length) {
                dfs1(image, r + 1, c, color, newColor);
            }
            if (c+1 < image[0].length) {
                dfs1(image, r, c + 1, color, newColor);
            }
        }
    }
    public static void floodFill(int [][]arr, int row, int col, String ans, boolean[][]visited){

        if(row<0 || col<0 || row==arr.length || col==arr[0].length || arr[row][col]==1 || visited[row][col]==true){
            return;
        }
        if(row == arr.length-1 && col==arr[0].length-1){
            System.out.println(ans);
            return;
        }

        visited[row][col]=true;
        floodFill(arr, row-1, col, ans+"T",visited);
        floodFill(arr, row, col-1, ans+"L",visited);
        floodFill(arr, row+1, col, ans+"D",visited);
        floodFill(arr, row, col+1, ans+"R",visited);

    }

    public static void main(String[] args) {
        int [][]arr=new int[][]{
                {0,1,0,0,0,0,0},
                {0,1,0,1,1,1,0},
                {0,0,0,0,0,0,0},
                {1,0,1,1,0,1,1},
                {1,0,1,1,0,1,1},
                {1,0,0,0,0,0,0}
        };
        boolean [][]visited=new boolean[6][7];
        floodFill(arr,0,0,"",visited);


    }
}
