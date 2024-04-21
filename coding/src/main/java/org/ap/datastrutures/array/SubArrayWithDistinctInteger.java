package org.ap.datastrutures.array;

import java.util.*;

/**
 * Given an integer array nums and an integer k, return the number of good subarrays of nums.
 * A good array is an array where the number of different integers in that array is exactly k.
 * For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.
 * A subarray is a contiguous part of an array.
 * Example 1:
 * Input: nums = [1,2,1,2,3], k = 2
 * Output: 7
 * Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2]
 * **/
public class SubArrayWithDistinctInteger {

    public static Integer countOfSubArrayWithDistinctInteger(int[] arr,int distinctIntegerCount){
        Map<Integer,Integer> map = new HashMap<>();
        int count = 0;
        List<List<Integer>> output = new ArrayList<>();
        for(int i=0, j=0; j< arr.length;){
            map.put(arr[j],map.getOrDefault(arr[j],0)+1);

            //if(map.size()< distinctIntegerCount){
            //    j++;
            //} else {
                //calculate answer

                //reduce thec computation , move the window
                while(map.size()>distinctIntegerCount) {
                    map.put(arr[i], map.get(arr[i]) - 1);
                    if (map.get(arr[i]) == 0)
                        map.remove(arr[i]);
                    i++;
                }
                //System.out.println(count);
                count = count + (j-i+1);
                //output.add(new ArrayList<Integer>(Arrays.asList(arr[i],arr[j])));
                j++;
           // }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{2, 1, 2, 1, 6};
        int k=2;
        System.out.println(countOfSubArrayWithDistinctInteger(arr,k));
        System.out.println(countOfSubArrayWithDistinctInteger(arr,k-1));
        System.out.println(countOfSubArrayWithDistinctInteger(arr,k)-countOfSubArrayWithDistinctInteger(arr,k-1));
    }
}
/**
 * leetcode - https://leetcode.com/problems/subarrays-with-k-different-integers/
 * */
