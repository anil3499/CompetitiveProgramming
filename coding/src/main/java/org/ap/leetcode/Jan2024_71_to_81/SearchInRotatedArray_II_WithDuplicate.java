package org.ap.leetcode.Jan2024_71_to_81;
//https://leetcode.com/problems/search-in-rotated-sorted-array-ii/description/
public class SearchInRotatedArray_II_WithDuplicate {
    public boolean search(int[] nums, int target) {
        return findInRotatedArray(nums,target);
    }
    public boolean findInRotatedArray(int[] nums,int target){
        int N = nums.length;
        int left = 0;
        int right = nums.length-1;
        while(left<=right) {
            int mid = left + (right-left)/2;
            int prev = (mid+ N-1) % N;
            int next = (mid+1) % N;
            //123  231
            System.out.println(left +" "+ right +" " + mid);

            if(nums[mid]==target) { //mid will be smalller fromboth of its neightbour
                return true;
            }else if(nums[left] < nums[mid]){ //left is sorted ,search in right
                if(nums[left] <= target && target < nums[mid]){
                    right=mid-1;
                }else{
                    left= mid+1;
                }
            }else if(nums[left] > nums[mid]) { //right is sorted, search in left
                if(nums[mid] < target && target <= nums[right]){
                    left = mid+1;
                }else {
                    right = mid-1;
                }
            }else {
                //duplicates, we know nums[mid] != target, so nums[start] != target
                //based on current information, we can only move left pointer to skip one cell
                //thus in the worest case, we would have target: 2, and array like 11111111, then
                //the running time would be O(n)
                left++;
            }
        }
        return false;
    }
}
