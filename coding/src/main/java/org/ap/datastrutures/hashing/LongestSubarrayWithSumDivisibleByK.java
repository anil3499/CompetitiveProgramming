package org.ap.datastrutures.hashing;

import java.util.*;
/*
Input : arr[] = {2, 7, 6, 1, 4, 5}, k = 3
Output : 4
The subarray is {7, 6, 1, 4} with sum 18,
which is divisible by 3.

Input : arr[] = {-2, 2, -5, 12, -11, -1, 7}
Output : 5
 */
public class LongestSubarrayWithSumDivisibleByK {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = scn.nextInt();
        }
        int k = scn.nextInt();
        System.out.println(solution(arr, k));
    }

    public static int solution(int[] arr, int k) {
        int sum = 0, maxLen = 0;
        int[] marr = new int[arr.length];
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            sum = sum + arr[i];
            marr[i] = (((sum % k) + k) % k);
        }

        for (int i = 0; i < arr.length; i++) {
            if (marr[i] == 0) {
                maxLen = i + 1;
            } else if (map.containsKey(marr[i]) == false)
                map.put(marr[i], i);
            else {
                maxLen = Math.max(maxLen, i - map.get(marr[i]));
            }
        }

        return maxLen;
    }

}




