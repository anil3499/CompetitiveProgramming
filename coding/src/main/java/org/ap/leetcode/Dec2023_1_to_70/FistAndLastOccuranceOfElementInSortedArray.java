package org.ap.leetcode.Dec2023_1_to_70;
///https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/description/
public class FistAndLastOccuranceOfElementInSortedArray {
    public int[] searchRange(int[] nums, int target) {
        int firstPos = findFirstPositionInSortedArray(nums,target);
        int lastPos = findLastPositionInSortedArray(nums,target);
        return new int[]{firstPos , lastPos};
    }
    public int findFirstPositionInSortedArray(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;
        int firstPosition = -1;
        while(left<=right) {
            int mid= left + (right-left)/2;
            if(nums[mid]==target) {
                firstPosition =mid;
                right = mid-1;
            }
            else if(target<=nums[mid]) {
                right = mid-1;
            }else{
                left = mid+1;
            }
        }
        return firstPosition;
    }
    public int findLastPositionInSortedArray(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;
        int lastPosition = -1;
        while(left<=right) {
            int mid= left + (right-left)/2;
            if(nums[mid]==target) {
                lastPosition =mid;
                left = mid+1;
            }
            else  if(target < nums[mid]) {
                right = mid-1;
            }else{
                left = mid+1;

            }

        }
        return lastPosition;
    }
}
