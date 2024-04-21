package org.ap.datastrutures.backtracking;

import java.util.Scanner;

/*
Given two positive integers M and K, find the maximum integer possible by doing at-most K swap operations on its digits.


Input: M = 129814999, K = 4
Output: 999984211
Swap 9 with 1 so number becomes 929814991
Swap 9 with 2 so number becomes 999814291
Swap 9 with 8 so number becomes 999914281
Swap 1 with 8 so number becomes 999984211

Input: M = 254, K = 1
Output: 524
Explanation: Swap 5 with 2 so number becomes 524

Input: M = 254, K = 2
Output: 542
Explanation: Swap 5 with 2 so number becomes 524, Swap 4 with 2 so number becomes 542
*/
public class LargestNumberAfterKSwap {

    static String max;

    public static String swap(String str, int i, int j) {
        char ith = str.charAt(i);
        char jth = str.charAt(j);

        String left = str.substring(0, i);
        String middle = str.substring(i + 1, j);
        String right = str.substring(j + 1);

        return left + jth + middle + ith + right;
    }
    public static void findMaximum(String str, int k) {
        // Time Complexity: O((N power 2) power k). For every digit, N2 recursive calls are generated until the value of k is 0 Thus O((N to power 2) to power k).
        if (k == 0) {
            return;
        }
        for (int i = 0; i < str.length() - 1; i++) {
            for (int j = i + 1; j < str.length(); j++) {
                if (str.charAt(i) < str.charAt(j)) { //when ever j th nuber is bigger than ith then we will do swap
                    //swapping
                    str = swap(str, i, j);
                    if (Integer.parseInt(str) > Integer.parseInt(max)) {
                        max = str;
                    }
                    findMaximum(str, k - 1);
                    //reverting
                    str = swap(str, i, j);
                }
            }
        }
    }
    //==========================================================================================================================
    //Time Complexity: O(Nk), For every recursive call N recursive calls are generated until the value of k is 0, Thus O((N to power k).
    // Function to find maximum integer possible by doing at-most K swap  operations on its digits

    public static char[] swap(char[] arr,int i , int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
        return arr;
    }
    public static void findMaximumNum(char ar[], int k)
    {
        if (k == 0)
            return;
        int n = ar.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                // if digit at position i is less than digit at position j, we swap them and check for maximum number so far.
                if (ar[j] > ar[i]) {
                    swap(ar,i,j);

                    String st = new String(ar);

                    // if current number is more than  maximum so far
                    if (Integer.parseInt(new String(ar)) > Integer.parseInt(max)) {
                        max = new String(ar);
                    }
                    // calling recursive function to set the next digit.
                    findMaximumNum(ar, k - 1);

                    // backtracking
                    swap(ar,i,j);
                }
            }
        }
    }



    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String str = scn.next();
        int k = scn.nextInt();
        max = str;
        //findMaximum(str, k);
        findMaximumNum(str.toCharArray(), k);

        System.out.println(max);
    }
}
/**
 * gfg - https://www.geeksforgeeks.org/find-maximum-number-possible-by-doing-at-most-k-swaps/
 * */