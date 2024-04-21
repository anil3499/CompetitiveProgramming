package org.ap.leetcode.Dec2023_1_to_70;

//https://leetcode.com/problems/next-permutation/
public class NextPermutation {
    //152432
    public void nextPermutation(int[] nums) {
        int greaterFromLAstElement = -1;
        int i=nums.length-1;
        int prev= nums[nums.length-1];
        //find non increasing element from last
        while(i>=0){
            if(nums[i]<prev) {
                greaterFromLAstElement = i;
                break;
            }
            prev = nums[i];
            i--;
        }
        //find greater than non increasing element from last then swap both
        i= nums.length-1;
        while(i>=0 && greaterFromLAstElement!=-1){
            if(nums[i]> nums[greaterFromLAstElement]) {
                swap(nums,greaterFromLAstElement,i);
                break;
            }
            i--;
        }
        //System.out.println("after first whileloop");
        printArray(nums);
        //reverse array after greaterFromLAstElement index
        if(greaterFromLAstElement==-1) //then reverse whole array
            reverse(nums, 0);
        else
            reverse(nums, greaterFromLAstElement+1);
        //System.out.println("after second whileloop");
        printArray(nums);
    }
    public void reverse(int[] nums, int start) {
        for(int i=start,j= nums.length-1;i<j;) {
            swap(nums, i, j);
            i++; j--;
        }
    }
    public void swap(int[] nums, int i , int index) {
        int temp =nums[i];
        nums[i]= nums[index];
        nums[index]= temp;
    }
    public void printArray(int nums[] ) {
        System.out.print("[");
        for(int i=0; i<nums.length;i++) {
            System.out.print(nums[i] +" ");
        }
        System.out.println("]");
    }





}
