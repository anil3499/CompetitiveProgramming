package org.ap.datastrutures.recursion;
//https://leetcode.com/problems/maximum-subarray/
//leetcode 53

public class MaximumSubArray {
    /*
   Time complexity:O(N), whereNis the length ofnums.
   Space complexity:O(1)
   */public int maxSubArray1(int[] nums) {
        int currSum = nums[0];
        int maxSum = nums[0];
        for(int ele : nums) {
            currSum = Math.max(currSum + ele, ele);
            maxSum = Math.max(maxSum,currSum);
        }
        return maxSum;
    }

    public int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE, currSum=0;
        boolean allNeg=true;
        for(int i=0; i<nums.length; i++) {
            if(nums[i] > 0)
                allNeg=false;

            currSum = currSum + nums[i];
            maxSum = Math.max(maxSum,currSum);
            if(currSum<0)
                currSum=0;
        }

        if(allNeg){
            maxSum = nums[0];
            for(int i=0; i < nums.length; i++)
                maxSum = Math.max(maxSum, nums[i]);
        }
        return maxSum;
    }
}
