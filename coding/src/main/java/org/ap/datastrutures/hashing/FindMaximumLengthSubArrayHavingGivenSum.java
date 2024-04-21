package org.ap.datastrutures.hashing;

import java.util.HashMap;
import java.util.Map;
/*
https://www.techiedelight.com/find-subarray-having-given-sum-given-array/

Input:  {2, 6, 0, 9, 7, 3, 1, 4, 1, 10}, sum 15
Output: Subarray {6, 0, 9} exists with sum 15
Input:  {0, 5, -7, 1, -4, 7, 6, 1, 4, 1, 10}, sum 15
Output:
Subarray {1, -4, 7, 6, 1, 4} exists with sum 15
or
Subarray {4, 1, 10} exists with sum 15
Input:  {0, 5, -7, 1, -4, 7, 6, 1, 4, 1, 10}, sum -3
Output: Subarray {1, -4} exists with sum -3
 */

class FindMaximumLengthSubArrayHavingGivenSum
{
    public static void maxLengthSubArray(int[] A, int K)
    {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;
        int maxLen = 0;
        int ending_index = -1;
        for (int i = 0; i < A.length; i++)
        {
            sum = sum + A[i];
            map.putIfAbsent(sum, i);
            if (map.containsKey(sum - K) )
            {
                if(i-map.get(sum-K) > maxLen) {
                    maxLen = i - map.get(sum - K);
                    ending_index = i;
                }
            }
        }

        // print the max length
        System.out.println("Array with max length for given sum is "+ maxLen);
        // print the sub-array
        System.out.println("[" + (ending_index - maxLen + 1) + ", " + ending_index + "]");
    }

    public static void main (String[] args)
    {
        int[] A = { 5, 6, -5, 5, 3, 5, 3, -2, 0 };
        int sum = 8;

        maxLengthSubArray(A, sum);
    }
}
