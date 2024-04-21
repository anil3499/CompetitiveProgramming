package org.ap.datastrutures.array;
/*
*Given an integer array nums and an integer val, remove all occurrences of val in nums in-place.
*  The order of the elements may be changed. Then return the number of elements in nums which are not equal to val.
Consider the number of elements in nums which are not equal to val be k, to get accepted, you need to do the following things:
Change the array nums such that the first k elements of nums contain the elements which are not equal to val.
* The remaining elements of nums are not important as well as the size of nums.
Return k.
*
* Input: nums = [3,2,2,3], val = 3
Output: 2, nums = [2,2,_,_]
Explanation: Your function should return k = 2, with the first two elements of nums being 2.
It does not matter what you leave beyond the returned k (hence they are underscores).
* */
public class RemoveElements {

    public int remove_elements(int[] nums, int val) {
        int expc_ans = 0;
        int i = 0, j = 0;
        while(j < nums.length) {
            if (nums[j] != val) {
                nums[i] = nums[j];
                j++;
                i++;
            } else {
                nums[i] = nums[j];
                j++;
            }
        }
        expc_ans = i;
        while (i < nums.length ) {
            System.out.println("index " + i + " "+nums.length);
            nums[i] = -1;
            i++;
        }
        for (int k = 0; k < nums.length; k++)
            System.out.println(nums[k]);
        return expc_ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0,1,2,2,3,0,4,2};
        int val = 2;

        /*int[] nums = new int[]{0,1,2,2,3,0,4,2};
        int val = 2;*/

        int result = new RemoveElements().remove_elements(nums, val);
        System.out.println(result);
    }
}
/*
*leetcode - https://leetcode.com/problems/remove-element/
* */
