package org.ap.leetcode.Jan2024_71_to_81;
//https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
public class RemoveDuplicatesFromSortedArray_II {
    public int removeDuplicates(int[] nums) {
        int occr =0;
        int j=0;
        int prev = nums[0];
        for(int i=0; i<nums.length; i++){
            if(nums[i]==prev) {
                occr++;
            }else {
                prev = nums[i];
                occr=1;
            }

            if(occr<=2) {
                nums[j]= nums[i];
                j++;
            }
        }
        return j;
    }
}
