package org.ap.datastrutures.backtracking;

import java.util.Stack;

/*
https://www.techiedelight.com/find-all-paths-from-source-to-destination-in-matrix/
Input:

[ 1  2  3 ]
[ 4  5  6 ]
[ 7  8  9 ]

Output:

1 — 2 — 3 — 6 — 9
1 — 2 — 5 — 6 — 9
1 — 2 — 5 — 8 — 9
1 — 4 — 5 — 6 — 9
1 — 4 — 5 — 8 — 9
1 — 4 — 7 — 8 — 9
 */
public class PrintAllPathFromSourceToDestinationInMatrix {
    public static void findPaths(int[][] mat, Stack<Integer> path,
                                 int i, int j)
    {
        int M = mat.length;
        int N = mat[0].length;
        path.push(mat[i][j]);
        if (i == M - 1 && j == N - 1)
        {
            //path.add(mat[i][j]);
            System.out.println(path);
            path.pop();
            return;
        }

        if ((i >= 0 && i < M && j + 1 >= 0 && j + 1 < N)) {
            findPaths(mat, path, i, j + 1);
        }

        if ((i + 1 >= 0 && i + 1 < M && j >= 0 && j < N)) {
            findPaths(mat, path, i + 1, j);
        }
        path.pop();
    }

    public static void main(String[] args)
    {
        int[][] mat =
                {
                        { 1, 2, 3 },
                        { 4, 5, 6 },
                        { 7, 8, 9 }
                };
        Stack<Integer> path = new Stack<>();
        int x = 0, y = 0;

        findPaths(mat, path, x, y);
    }
}
