package org.ap.leetcode.Dec2023_1_to_70;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//https://leetcode.com/problems/permutations-ii/
public class ArrayPermutations_II {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        List<Integer> temp = new ArrayList<>();
        Arrays.sort(nums);
        findPermutations(nums, temp,used,result);
        return result;
    }
    public void findPermutations(int[] nums,List<Integer> temp,boolean[] used , List<List<Integer>> result){
        if(temp.size() == nums.length){
            List<Integer> res = new ArrayList<>(temp);
            result.add(res);
            return;
        }
        for(int i=0;i<nums.length; i++){
            // Skip if the number has already been used or if it's a duplicate that has already been used
            if(i>0 && nums[i]==nums[i-1] && used[i-1]) continue;
            if(used[i]) continue;
            temp.add(nums[i]);
            used[i]=true;
            findPermutations(nums, temp,used,result);
            temp.remove(temp.size()-1);
            used[i]=false;
        }
    }
}
