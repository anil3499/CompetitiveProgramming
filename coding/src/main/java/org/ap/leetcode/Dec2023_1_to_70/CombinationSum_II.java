package org.ap.leetcode.Dec2023_1_to_70;
//https://leetcode.com/problems/combination-sum-ii/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Input: candidates = [10,1,2,7,6,1,5], target = 8
 * Output:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 * */
public class CombinationSum_II {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        findCombinations(candidates, target, result,temp, 0);
        return result;
    }
    public void findCombinations(int[] candidates, int target,List<List<Integer>> result,List<Integer> temp,int index){
        if(target==0){
            List<Integer> res = new ArrayList<>(temp);
            result.add(res);
            return;
        }
        int prev = -1;
        for(int i=index; i<candidates.length; i++){
            if(candidates[i]==prev) //this smallest hack to skip duplicates
                continue;
            if(target-candidates[i]>=0) {
                temp.add(candidates[i]);
                findCombinations(candidates, target-candidates[i],result,temp, i+1);//we dont want duplicate element
                temp.remove(temp.size()-1);
                prev=candidates[i];
            }
        }
    }
}
