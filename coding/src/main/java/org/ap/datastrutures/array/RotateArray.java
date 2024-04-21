package org.ap.datastrutures.array;

import org.ap.utils.Print;
/*
* Given an integer array nums, rotate the array to the right by k steps, where k is non-negative.
* Example 1:
* Input: nums = [1,2,3,4,5,6,7], k = 3
* Output: [5,6,7,1,2,3,4]
* Explanation:
* rotate 1 steps to the right: [7,1,2,3,4,5,6]
* rotate 2 steps to the right: [6,7,1,2,3,4,5]
* rotate 3 steps to the right: [5,6,7,1,2,3,4]
* */
public class RotateArray {
    public void rotate_array(int[] arr, int k) {
        while(k>0) {
            int hold = arr[0];
            for (int i = 1; i < arr.length; i++){
                int temp = arr[i];
                arr[i] = hold;
                hold = temp;
            }
            arr[0] = hold;
            k--;
        }
        Print.print_array(arr);
    }
    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,4,5,6,7};
        new RotateArray().rotate_array(nums,3);
    }
}

/*
*leetcde -https://leetcode.com/problems/rotate-array/
 * */