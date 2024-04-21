package org.ap.leetcode.Dec2023_1_to_70;
//Search in Rotated Sorted Array
//https://leetcode.com/problems/search-in-rotated-sorted-array/description/
public class SearchInRotatedArray {
    public int search(int[] nums, int target) {
        if (nums.length==0) return -1;
        int mid = findStartingPointOfArray(nums);
        System.out.println(mid);
        if(target >= nums[mid] && target <=nums[nums.length-1])
            return  binarySearch(nums,mid,nums.length-1, target);
        else
            return binarySearch(nums,0, mid-1, target);
    }
    public int binarySearch(int[] nums, int left,int right, int target){
        while(left<=right) {
            int mid = left +(right-left)/2;
            if(nums[mid]==target)
                return mid;
            if(nums[mid]< target)
                left = mid+1;
            else
                right = mid-1;
        }
        return -1;
    }
    public int findStartingPointOfArray(int[] nums) {
        int n = nums.length;
        int left = 0;
        int right = n-1;
        int res;
        //if(nums[left]<=nums[right]) return 0;  //array is sorted
        while(left<=right) {
            int mid = left + (right-left)/2;
            int prev = (mid-1+n)%n;
            int next =  (mid+1) % n;
            //System.out.println(left + " " +mid+" "+ right);

            //mid element will be always lower than its left and right element
            if(nums[mid]<=nums[prev] && nums[mid]<= nums[next]){
                return mid;
            } else {
                //below is very imp case and not shown in aaditya's video
                if(nums[mid]>=nums[left] && nums[mid] <=nums[right]) //means subarray is sorted
                {
                    return left;

                } else if(nums[left]<=nums[mid]) { //left is sorted so searchin right
                    left = mid+1;
                }else { //nums[mid]<=nums[right] //right is sorted so search inleft
                    right = mid-1;
                }
            }
        }
        return 0;
    }
}
