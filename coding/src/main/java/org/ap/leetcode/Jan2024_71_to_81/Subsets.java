package org.ap.leetcode.Jan2024_71_to_81;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/subsets/
public class Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> temp = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        findSubsets(nums,0,temp,result);
        return result;
    }

    public void findSubsets(int[] nums, int index, List<Integer> temp, List<List<Integer>> result) {
        if(index>=nums.length){
            List<Integer> res = new ArrayList<>(temp);
            result.add(res);
            return;
        }

        temp.add(nums[index]);
        findSubsets(nums,index+1,temp,result);
        temp.remove(temp.size()-1);
        findSubsets(nums,index+1,temp,result);

    }
}
