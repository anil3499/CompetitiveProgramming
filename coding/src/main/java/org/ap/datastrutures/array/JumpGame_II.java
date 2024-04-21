package org.ap.datastrutures.array;

import java.lang.reflect.Array;
import java.util.Arrays;

import static java.lang.Math.max;

/**
 * You are given a 0-indexed array of integers nums of length n. You are initially positioned at nums[0].
 *Each element nums[i] represents the maximum length of a forward jump from index i. In other words
 *  if you are at nums[i], you can jump to any nums[i + j] where:
 *
 * 0 <= j <= nums[i] and
 * i + j < n
 * Return the minimum number of jumps to reach nums[n - 1]. The test cases are generated such that you can reach nums[n - 1].
 *
 * Example 1:
 * Input: nums = [2,3,1,1,4]
 * Output: 2
 * Explanation: The minimum number of jumps to reach the last index is 2. Jump 1 step from index 0 to 1, then 3 steps to the last index.
 *
 * Example 2:
 * Input: nums = [2,3,0,1,4]
 * Output: 2
 *
 */
public class JumpGame_II {

    //its dp problem can be solved using dp array of O(n) space complexity
 public int jump_greedy(int[] arr){
     int n = arr.length;
     int dp[] = new int[n];
     //initialize dp array with max int value
     Arrays.fill(dp,Integer.MAX_VALUE);
     //initialize last index with 0 since we can't move any more
     dp[n-1] = 0;

     for (int i=n-2; i>0; i--){
         int min =Integer.MAX_VALUE;
         for (int j = i+1; j< Math.min(n-1,i+arr[i]);j++){
             min = Math.min(min,dp[j]);
         }
         if(min !=Integer.MAX_VALUE){
             dp[i] = min+1;
         }
     }
     return dp[0];
    }

    //below code is to more optimize the dp array
    public int jump(int arr[]){
        int jumps = 0; //no of jumps required
        int farthest = 0;
        //int begin = 0;
        int end = 0;
        int current =0 ;//helps to keep track of cuurent position we are at
        for (int i =0; i < arr.length; i++){

            //to tarack farthest we can reach
            farthest = Math.max(farthest, i + arr[i]);

            if (i==current){
                //here end represents end of previous jump
                jumps++;
                current = farthest;
            }
        }
        return jumps;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3,2,1,0,4};
        System.out.println(new JumpGame_II().jump(nums));
    }
}

/**
 * Leetcode - https://leetcode.com/problems/jump-game-ii/
 *youtube - https://www.youtube.com/watch?v=wLPdkLM_BWo&t=666s
 * */