package org.ap.leetcode.Dec2023_1_to_70;

import java.util.Arrays;

//https://leetcode.com/problems/3sum-closest/
public class ThreeSumClosestTarget {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int sumCloset = 0;
        int closetDiff = Integer.MAX_VALUE;
        int n=nums.length;
        for(int i=0; i<n-2;i++){
            int left = i+1;
            int right = n-1;
            int sum=nums[i] + nums[left] + nums[right];
            while(left < right) {
                if(closetDiff > Math.abs(target-sum)) {
                    closetDiff = Math.abs(target-sum);
                    sumCloset =sum;
                }
                if(target> sum) {
                    sum-=nums[left]; //remove previous value
                    left++;
                    sum+=nums[left]; //add new value
                }else if(target < sum){
                    sum-=nums[right];//remove previous value
                    right--;
                    sum+=nums[right];//add new value
                }else {
                    return sum;
                }
            }

        }
        return sumCloset;
    }
}
