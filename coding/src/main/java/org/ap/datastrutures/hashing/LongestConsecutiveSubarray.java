package org.ap.datastrutures.hashing;
/*
https://www.geeksforgeeks.org/length-largest-subarray-contiguous-elements-set-2/

Input:  arr[] = {10, 12, 11};
Output: Length of the longest contiguous subarray is 3

Input:  arr[] = {14, 12, 11, 20};
Output: Length of the longest contiguous subarray is 2

Input:  arr[] = {1, 56, 58, 57, 90, 92, 94, 93, 91, 45};
Output: Length of the longest contiguous subarray is 5
 */

import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSubarray {

    private static Integer findLongestConseqSubseq(int[] arr, int n) {
        int maxLenth=1 , size=arr.length , startIndex=0,endIndex=0;
        for(int i=0 ; i < n-1; i++){
            Set<Integer> set=new HashSet<>();
            set.add(arr[i]);
            int min=arr[i];
            int max=arr[i];
            for(int j=i+1 ; j<n ; j++){
                if(set.contains(arr[j])){
                    break;
                }
                set.add(arr[j]);
                min=Math.min(min,arr[j]);
                max=Math.max(max,arr[j]);

                if((max-min) == (j-i)){
                    maxLenth=Math.max(maxLenth,max-min+1);
                    startIndex=i;
                    endIndex=j;
                }
            }

        }
        System.out.println("index : "+startIndex +" to "+endIndex);
        return maxLenth;
    }

    public static void main(String args[])
    {
        int arr[] = { 10, 12, 12, 10, 10, 11, 10 };
        int n = arr.length;
        System.out.println(
                "Length of the Longest consecutive subsequence is "
                        + findLongestConseqSubseq(arr, n));
    }


}
