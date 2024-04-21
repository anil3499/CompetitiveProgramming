package org.ap.leetcode.Dec2023_1_to_70;
///https://leetcode.com/problems/remove-element/
public class RemoveElementsFromArray {
    public int removeElement(int[] nums, int val) {
        int j=0;
        for(int i=0;i<nums.length; i++) {
            if(nums[i]!=val){
                nums[j] =nums[i];
                j++;
            }
        }
        return j;
    }
}
