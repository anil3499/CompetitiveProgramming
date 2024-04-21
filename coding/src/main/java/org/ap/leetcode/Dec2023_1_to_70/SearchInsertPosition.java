package org.ap.leetcode.Dec2023_1_to_70;
//https://leetcode.com/problems/search-insert-position/
public class SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        if(target<nums[0]) return 0;
        return findElement( nums, target);

    }
    public int findElement(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;
        int searchIndex = -1;
        while(left<=right) {

            int mid= left + (right-left)/2;
            if(target==nums[mid]) {
                return mid;
            }else if(target < nums[mid]) {
                right= mid-1;
            }else {
                left = mid+1;
            }

        }
        //last meleft jaha rhega vhi insert index banega
        return left;
    }
}
