package org.ap.datastrutures.array;

public class RemoveDuplicateFromSortedArrray {

    public int remove_duplicates_frm_Sorted_array(int[] arr) {
        int output;
        int i = 0;
        int j = 1;
        while (j < arr.length) {
            //if arr[i] == arr[j] means increament j untillfilnd diffrenet element
            if (arr[i] == arr[j]) {
                j++;
            } else { //when we find then just increase i and write the non duplicate element at ith position then increase j
                i++;
                arr[i] = arr[j];
                j++;
            }
        }
        i++;
        output = i;
        //at the end overwrite remaininig duplicats as -1
        while (i < arr.length) {
            arr[i] = -1;
            i++;
        }
        for (i=0; i< arr.length; i++)
            System.out.print(" " + arr[i]);
        return output;
    }
    public static void main(String[] args) {
        int[] nums = new int[]{0,0,1,1,1,2,2,3,3,4};

        /*int[] nums = new int[]{0,1,2,2,3,0,4,2};
        int val = 2;*/

        int result = new RemoveDuplicateFromSortedArrray().remove_duplicates_frm_Sorted_array(nums);
        System.out.println("\n\n result " + result);
    }
}
/**
 * leetcode - https://leetcode.com/problems/remove-duplicates-from-sorted-array/
 * */