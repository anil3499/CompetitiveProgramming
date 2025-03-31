package org.ap.datastrutures.array.array;

import java.util.Arrays;

/*
time complexity : n
space complexity : 1
maintain non zero sequence also
 */
public class MoveZero {


    public static void movezero(int []arr){
        int j=0;
        for(int i=0;i<arr.length;i++){
            if(arr[i] != 0){
                int temp=arr[i];
                arr[i]=arr[j];
                arr[j]=temp;
                j++;
            }
        }

    }
    public void moveZeroes(int[] nums) {
        int lastNonZeroAt = 0;
        // If the current element is not 0, then we need to
        // append it just in front of last non 0 element we found.
        for(int i=0; i< nums.length; i++) {
            if(nums[i]!=0) {
                nums[lastNonZeroAt++] = nums[i];
            }
        }
        // After we have finished processing new elements,
        // all the non-zero elements are already at beginning of array.
        // We just need to fill remaining elements in the array with 0's.
        for(int i=lastNonZeroAt; i<nums.length; i++){
            nums[i] = 0;
        }
    }
    public static void main(String[] args) {
        int []arr=new int[]{5,6,0,4,3,0,7,0,1,9};
        movezero(arr);
        System.out.println(Arrays.toString(arr));
    }
}
