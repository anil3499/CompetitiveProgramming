package org.ap.leetcode.Dec2023_1_to_70;
//https://leetcode.com/problems/remove-duplicates-from-sorted-array/description/
public class RemoveDuplicatesFromSortedArray {
    public int removeDuplicates(int[] nums) {
        int j = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                nums[j] = nums[i];
                j++;
            }
        }
        return j;
    }
    public int removeDuplicates1(int[] nums) {
        int n= nums.length;
        if(n==0) return 0;
        if(n ==1) return 1;

        int j=0;
        int prev = nums[0];
        for(int i =1;i<n; ){
            if(prev == nums[i]){
                i++;
            } else {
                j++;
                nums[j]= nums[i];
                prev = nums[i];
            }
        }
        return j+1;
    }
}
