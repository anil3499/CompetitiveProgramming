package org.ap.leetcode.Dec2023_1_to_70;

import java.util.HashSet;
import java.util.Set;

///https://leetcode.com/problems/first-missing-positive/
public class FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int i=0;i<nums.length; i++){
            set.add(nums[i]);
        }
        int smallestPositive=1;
        while(set.contains(smallestPositive)){
            smallestPositive++;
        }
        return smallestPositive;
    }
    public int firstMissingPositive1(int[] nums) {
        //segregate negetives at the last of array so we just need to work with 0 to jpositives only
        int i,j=0,num,n=nums.length;
        for(i=0; i<n; i++){
            if(nums[i]>0){
                nums[j]=nums[i];
                j++;
            }
        }
        //now our boundary is till j only
        n=j;
        //printArray(nums);
        //now keep marking element as negetive of th indexes if its in the array range
        for(i=0; i<n;i++){
            num = Math.abs(nums[i]);
            if(num<=n) //check if this no os less than our boundary
                if(nums[num-1] > 0 ) { //no-1 should be positive, to avoid already marked
                    nums[num-1] = -1 * nums[num-1]; //mark negetive
                }
        }
        // printArray(nums);
        //now whichever fist positive element we find ,its index+1 will be our ans
        for(i=0;i<n;i++){
            if(nums[i]>0)
                return i+1;
        } //if all elmenet are like1,n are there in array thenn+1willbe our ans
        return n+1;
    }
    public void printArray(int[] nums) {
        System.out.print("[");
        for(int i=0; i<nums.length;i++){
            System.out.print(nums[i] +" ");
        }
        System.out.println("]");
    }
}
