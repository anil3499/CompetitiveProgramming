package org.ap.datastrutures.array;

/**
 * You are given an integer array nums. You are initially positioned at the array's first index,
 * and each element in the array represents your maximum jump length at that position.
 * Return true if you can reach the last index, or false otherwise.
 * Example 1:
 * Input: nums = [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 * */
public class JumpGame {
    public boolean jump(int arr[]){
        int i =0;
        int previ = -1;
        boolean flag = false;
        while(i<arr.length){
            i = i+ arr[i];
            if (i==previ){
                flag = false;
                break;
            }
            previ = i;
        }
        if (i>= arr.length-1) return true;
        else return false;
    }
    public static void main(String[] args) {
        int[] nums = new int[]{3,2,1,0,4};
        System.out.println(new JumpGame().jump(nums));
    }
}

/**
 * https://leetcode.com/problems/jump-game/?envType=study-plan-v2&envId=top-interview-150
 * */