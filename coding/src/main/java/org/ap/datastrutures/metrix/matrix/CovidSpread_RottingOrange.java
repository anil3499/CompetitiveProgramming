package org.ap.datastrutures.metrix.matrix;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
https://www.geeksforgeeks.org/minimum-time-required-so-that-all-oranges-become-rotten/
https://leetcode.com/problems/rotting-oranges/
0: Empty cell
1: Cells have fresh oranges
2: Cells have rotten oranges
Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten,
because rotting only happens 4-directionally.

Input:
3 5
2 1 0 2 1
1 0 1 2 1
1 0 0 2 1
Output:
2
Explanation:
Patients at positions {0,0}, {0, 3}, {1, 3}
and {2, 3} will infect patient at {0, 1},
{1, 0},{0, 4}, {1, 2}, {1, 4}, {2, 4} during 1st
unit time. And, during 2nd unit time, patient at
{1, 0} will get infected and will infect patient
at {2, 0}. Hence, total 2 unit of time is
required to infect all patients.

 */
public class CovidSpread_RottingOrange {

    public static int orangesRotting(int[][] grid) {
        int rows = grid.length, cols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int freshCount = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    freshCount++;
                } else if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int step = 0;
        while (!queue.isEmpty() && freshCount > 0) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                for (int[] dir : DIRS) {
                    int nextX = curr[0] + dir[0];
                    int nextY = curr[1] + dir[1];
                    if (nextX < 0 || nextX >= rows || nextY < 0 || nextY >= cols || grid[nextX][nextY] != 1) {
                        continue;
                    }
                    queue.offer(new int[]{nextX, nextY});
                    grid[nextX][nextY] = 2;
                    freshCount--;
                }
            }
            step++;
        }

        return freshCount == 0 ? step : -1;
    }

    // Driver Code
    public static void main(String[] args) {
        int v[][] = {{2, 1, 0, 2, 1},
                     {1, 0, 1, 2, 1},
                     {1, 0, 0, 2, 1}};

        System.out.println("Max time incurred: " +
                orangesRotting(v));
    }
}