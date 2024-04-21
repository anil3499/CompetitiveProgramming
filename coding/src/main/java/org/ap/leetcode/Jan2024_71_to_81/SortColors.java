package org.ap.leetcode.Jan2024_71_to_81;
//https://leetcode.com/problems/sort-colors/
public class SortColors {
    public void sortColors(int[] nums) {
        int left =0, right=nums.length-1;

        for(int i=0; i<=right;i++) {

            if(nums[i]==0) {
                swap(nums,i,left);
                left++;
            }
            if(nums[i]==2) {
                swap(nums,i,right);
                right--;
                i--; // this is decremented so the next iterration runs for same i
                /*
                for ex 1,2,0 arr
                in iteration 1 sinceits 1 so will skip
                in iteration 2 its 2 so will get swap with right and our array will become 1,0,2
                since we don't know what is at ith position  after swapping wiwth right'
                so we will rerun this iteration

                we knew what was at left position so we dont need to do i-- while swapping with left
                */
            }
        }
    }
    public void swap(int[] nums,int i, int j){
        int temp = nums[i];
        nums[i]=nums[j];
        nums[j]=temp;
    }
}
