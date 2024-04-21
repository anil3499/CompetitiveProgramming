package org.ap.datastrutures.metrix;

public class SpiralMetrix {
    /*
    *
top==>   1 2 3 4 5
         6 7 8 9 10
         11 12 13 14 15
         16 17 18 19 20
bootom==>21 22 23 24 25
         left        right

* */
    public static void printSprialMetrix(int n) {
        int[][] a = create_nXn_metrix(n);
        print_metrix(a);
        System.out.println();
        // we will need 4 pointers
        //top will indicate top left corner of metrix
        //bottom will indicate bottom left corner of metrix
        //left will indicate
        int top = 0;
        int bottom = n - 1;
        int left = 0;
        int right = n - 1;

        //we will create a direction pointer
        //if direction is 0 then we weill go left to right
        //if direction is 1 then we weill go top to bottom
        //if direction is 2 then we weill go right to left
        //if direction is 3 then we weill go bottom to left
        int direction = 0;

        while (top <= bottom && left <= right) {
            if (direction == 0) {
                for (int i = left; i <= right; i++)
                    System.out.print(" "  + a[top][i]);
                top++;
            }
            if (direction == 1) {
                for (int i = top; i <= bottom; i++)
                    System.out.print(" "  + a[i][right]);
                right--;
            }
            if (direction == 2) {
                for (int i = right; i >= left; i--)
                    System.out.print(" "  + a[bottom][i]);
                bottom--;
            }
            if (direction == 3) {
                for (int i = bottom; i >= top; i--)
                    System.out.print(" "  + a[i][left]);
                left++;
            }
            direction = (direction + 1) % 4;

        }
    }


    public static int[][] create_nXn_metrix(int n) {
        int[][] a = new int[n][n];
        int count = 1;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                a[i][j] = count;
                count++;
            }
        return a;
    }

    public static void main(String[] args) {
        printSprialMetrix(4);
    }
    public static void print_metrix(int[][] a){
        for (int i = 0; i < a.length; i++) {
            System.out.println();
            for (int j = 0; j < a.length; j++) {
                System.out.print(" " + a[i][j]);
            }
        }
    }
}
