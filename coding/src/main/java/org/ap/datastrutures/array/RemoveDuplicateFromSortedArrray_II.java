package org.ap.datastrutures.array;
/**
 * Given an integer array nums sorted in non-decreasing order,
 * remove some duplicates in-place such that each unique element appears at most twice. The relative order of the elements should be kept the same.
 * Since it is impossible to change the length of the array in some languages,
 * you must instead have the result be placed in the first part of the array nums.
 * More formally, if there are k elements after removing the duplicates, then the first k elements of nums should hold the final result.
 * It does not matter what you leave beyond the first k elements.
 * Return k after placing the final result in the first k slots of nums.
 * Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra memory.
 * Example 1:
 *
 * Input: nums = [1,1,1,2,2,3]
 * Output: 5, nums = [1,1,2,2,3,_]
 * Explanation: Your function should return k = 5, with the first five elements of nums being 1, 1, 2, 2 and 3 respectively.
 * It does not matter what you leave beyond the returned k (hence they are underscores).
 * */
public class RemoveDuplicateFromSortedArrray_II {

    public int remove_duplicates_frm_Sorted_array(int[] arr, int k) {
        int res = -1;
        int occurance = 0;
        int m =1;
        for (int i =1; i<arr.length; i++){
            if (arr[i-1]==arr[i]) {
                if (occurance < k) {
                    arr[m] = arr[i];
                    m++;
                }
                occurance ++;
            }else {
                occurance =1;
                arr[m] = arr[i] ;
                m++;
            }
        }
        res = m;
        while(m<arr.length) {
            arr[m] = -1;
            m++;
        }

        for (int i =0; i<arr.length; i++)
            System.out.print(" " + arr[i]);
        return res;
    }
    public static void main(String[] args) {
        int[] nums = new int[]{0,0,1,1,1,2,2,3,3,3};

        /*int[] nums = new int[]{0,1,2,2,3,0,4,2};
        int val = 2;*/

        int result = new RemoveDuplicateFromSortedArrray_II().remove_duplicates_frm_Sorted_array(nums,2);
        System.out.println("\n\n result " + result);
    }
}
/**
 * leetcode - https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/
 * */