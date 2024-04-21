package org.ap.leetcode.Dec2023_1_to_70;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/combination-sum/
public class CombinationSum {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp =new ArrayList<>();
        backtrack(candidates, target, temp,result,0);
        return result;
    }
    public void backtrack(int[] candidates, int target,List<Integer> temp, List<List<Integer>> result,int index){
        if(target==0) {
            List<Integer> res = new ArrayList<>(temp);
            result.add(res);
            return;
        }
        for(int i=index; i<candidates.length; i++) {
            if(target-candidates[i]>=0 ) {
                temp.add(candidates[i]);
                backtrack(candidates, target-candidates[i], temp,result,i);//// not i + 1 because we can reuse same elements
                temp.remove(temp.size()-1);
            }
        }
    }
}
