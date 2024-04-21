package org.ap.datastrutures.metrix;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathInGridWithObstacle {
    public static void main(String[] args) {

        int[][] metrix = new int[][]{{0,0,0},{0,1,1},{0,0,0}};
        //int[][]  metrix = new int[][] {{0,0},{1,0}};
        System.out.println(shortestPath(metrix));
        //System.out.println(shortestPathBinaryMatrix(metrix));
    }
    public static int shortestPathBinaryMatrix(int[][] grid) {
        if(grid[0][0] == 1) return -1;
        int m = grid.length;
        int n = grid[0].length;

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0,0,1});
        grid[0][0] =1;

        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        while(!q.isEmpty()) {
            int size = q.size();
            while(size-- > 0) {
                int[] point = q.poll();

                if(point[0] == m-1 && point[1] == n-1)
                    return point[2];

                for(int[] d : dir) {
                    int r = point[0] + d[0];
                    int c = point[1] + d[1];

                    if(r >= 0 && c >= 0 && r < m && c < n && grid[r][c] == 0){
                        q.add(new int[]{r, c, point[2]+1});
                        grid[r][c] = 1;
                    }
                }
            }
        }

        return -1;
    }
    public static int shortestPath(int[][] metrix){
        int m = metrix.length;
        int n = metrix[0].length;
        LinkedList<int[]> queue = new LinkedList<>();
        //create list for marking visited elements
        boolean[][] visited = new boolean[m][n];
        // instead of creating visited array we can mark the index as 1 as well in grid itself,
        // but this is a approach which will help us in next problem where we can remove the obstacles
        int[][] directions = new int[][]{{0,1},{1,0},{0,-1},{-1,0}};
        //create quueue fpr BFS
        queue.offer(new int[]{0,0,0});
        visited[0][0] = true;

        while(!queue.isEmpty()){
            int size = queue.size();
            while(size-- > 0){
                int[] curr = queue.poll();
                int curr_i = curr[0];
                int curr_j = curr[1];
                int curr_step = curr[2];
                if(curr_i == m-1 && curr_j == n-1)
                    return curr_step;

                for (int[] dir : directions){
                    int new_curr_i = curr_i + dir[0];
                    int new_curr_j = curr_j + dir[1];
                    int new_curr_step = curr_step + 1;

                    if(new_curr_i < 0 || new_curr_i >= m || new_curr_j < 0 || new_curr_j >= n) {
                        continue;
                    }
                    if(metrix[new_curr_i][new_curr_j] == 0 && visited[new_curr_i][new_curr_j] == false) {
                        queue.push(new int[]{new_curr_i,new_curr_j, new_curr_step});
                        visited[new_curr_i][new_curr_j] = true; // mark visited
                        //metrix[new_curr_i][new_curr_j] =1;
                    } //else if (metrix[curr_i][curr_j] ==1 && visited[new_curr_j][new_curr_j] == false){
                        //we cant go if there is obstacle
                    //}
                }
            }
        }
        return -1;
    }
}
