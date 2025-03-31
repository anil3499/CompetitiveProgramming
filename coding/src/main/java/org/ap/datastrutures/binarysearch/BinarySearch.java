package org.ap.datastrutures.binarysearch;
//https://leetcode.com/problems/binary-search/description/
// https://www.youtube.com/watch?v=YbkELwnGRdo&list=PL_z_8CaSLPWeYfhtuKHj-9MpYb6XQJ_f2&index=3
//Concept
/*
Generally we use ==> mid = (starr+end)/2
but this can cuse overflow , suppose start and end both are max of int then this will break
so we shouodld use start + (end-start)/2
* **/

public class BinarySearch {
    public static int search(int[] nums, int target) {
        int start=0, end = nums.length-1;
        while(start<=end) {
            int mid = (start+end)/2;
            if(nums[mid] < target) {
                start = mid +1;
            } else if(target < nums[mid]) {
                end = mid-1;
            } else { //if(nums[mid] ==target) {
                return mid;
            }
        }
        return -1;
    }
    public static void main(String[] args) {
        System.out.println(search(new int[]{-1,0,3,5,9,12}, 9));
    }
}
/*
Complexity Analysis

Letnbe the size of the input arraynums.

    Time complexity:O(logn)
     nums is divided into half each time. In the worst-case scenario, we need to cutnumsuntil the range has no element, and it takes logarithmic time to reach this break condition.

    Space complexity:O(1)
        During the loop, we only need to record three indexes,left,right, andmid, they take constant space.

* */