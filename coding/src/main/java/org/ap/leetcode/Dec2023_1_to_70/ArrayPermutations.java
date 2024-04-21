package org.ap.leetcode.Dec2023_1_to_70;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/permutations/
public class ArrayPermutations {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp= new ArrayList<>();
        findPermutations(nums,0,result);
        //findPermutationsByPattern(nums,temp,result);
        return result;
    }

    public void findPermutationsByPattern(int[] nums, List<Integer> temp, List<List<Integer>> result) {
        if(temp.size() ==nums.length) {
            List<Integer> res = new ArrayList<>(temp);
            result.add(res);
            return;
        }
        for(int i=0; i<nums.length; i++) {
            if(temp.contains(nums[i])) continue; //// element already exists, skip
            temp.add(nums[i]);
            findPermutationsByPattern(nums,temp,result);
            temp.remove(temp.size()-1);
        }
    }
    //if you are doing thru array start with indx and pass index as well
    public void findPermutations(int[] nums, int index,List<List<Integer>> result) {
        if(index >= nums.length) {
            List<Integer> res = new ArrayList<>();
            for(int i=0; i<nums.length;i++)
                res.add(nums[i]);
            result.add(res);
            return;
        }
        for(int i=index;i<nums.length;i++){
            swap(nums,i,index);
            findPermutations(nums, index+1,result);
            swap(nums,i,index);
        }
    }
    public void swap(int[] nums, int i, int index) {
        int temp = nums[i];
        nums[i] = nums[index];
        nums[index]=temp;
    }
}
