package org.ap.datastrutures.array;

import java.util.HashSet;
import java.util.Set;

/**
 * Given an integer array nums, return true if any value appears at least twice in the array, and return false if every element is distinct.
 * Example 1:
 * Input: nums = [1,2,3,1]
 * Output: true
 * */
public class ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int i=0;i< nums.length; i++){
            if(set.contains(nums[i])){
                System.out.println(set);
                return true;
            }
            set.add(nums[i]);
        }
        return false;
    }
}

/**
 * LeetCode - https://leetcode.com/problems/contains-duplicate/
 * */