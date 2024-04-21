package org.ap.datastrutures.recursion;
//https://leetcode.com/problems/maximum-subarray/
public class MaximumSubArray {
    public int maxSubArray(int[] nums) {
        int maxSum=Integer.MIN_VALUE,currSum=0;
        boolean allNeg=true;
        for(int i=0; i<nums.length; i++) {
            if(nums[i]>0) allNeg=false;
            currSum+=nums[i];
            if(maxSum<currSum)
                maxSum=currSum;
            if(currSum<0)
                currSum=0;
        }

        if(allNeg){
            maxSum=nums[0];
            for(int i=0; i<nums.length; i++)
                maxSum=Math.max(maxSum,nums[i]);
        }
        return maxSum;
    }
}
