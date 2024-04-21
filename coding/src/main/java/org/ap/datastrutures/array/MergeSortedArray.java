package org.ap.datastrutures.array;


public class MergeSortedArray {
    public int[] merge_array_2_pointer_approach(int[] nums1 , int m, int[] nums2, int n){

        int i = m-1;
        int j = n-1;
        int k = m+n-1;

        while(j>=0){
            if (i>=0 && nums1[i]>nums2[j]){
                nums1[k] = nums1[i];
                k--;i--;
            }else {
                nums1[k] = nums2[j];
                j--;k--;
            }

        }
        while(j >= 0) {
            nums1[k--] = nums2[j--];
        }
        return nums1;
    }

    public static void main(String[] args) {
        int[] nums1 = new int[]{1,2,3,0,0,0};
        int m = 3;
        int [] nums2 = new int[]{2,5,6};
        int n = 3;

        /*int[] nums1 = new int[]{1};
        int m = 1;
        int [] nums2 = new int[]{};
        int n = 0;*/

       /* int[] nums1 = new int[]{0};
        int m = 0;
        int [] nums2 = new int[]{1};
        int n = 1;
*/
        int[] result= new MergeSortedArray().merge_array_2_pointer_approach(nums1,m,nums2,n);
        for (int i=0; i<result.length; i++)
            System.out.println(result[i]);
    }
}

/*
* leetcode -https://leetcode.com/problems/merge-sorted-array/
 * */