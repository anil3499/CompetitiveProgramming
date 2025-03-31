package org.ap.datastrutures.backtracking;

import java.util.*;

/**
 * leetcode prob - 40
 * Leetcode -https://leetcode.com/problems/combination-sum-ii/
 * */
public class CombinationSum_II_WithDuplicate {

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        //we sort the input, which will also group all the same numbers together then we can gurantee that every elemnt will be used once only
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        findCombinations(candidates, target, result,temp, 0);
        return result;
    }
    public void findCombinations(int[] candidates, int target,List<List<Integer>> result,List<Integer> temp,int index) {
        if (target == 0) {
            List<Integer> res = new ArrayList<>(temp);
            result.add(res);
            return;
        }
        int prev = -1;
        for (int i = index; i < candidates.length; i++) {
            //to do not use element twice we put below hack to check prev
            if (candidates[i] == prev) //this smallest hack to skip duplicates
                continue;
            if (target - candidates[i] >= 0) {
                temp.add(candidates[i]);
                findCombinations(candidates, target - candidates[i], result, temp, i + 1);//we dont want duplicate element so i+1
                temp.remove(temp.size() - 1);
                prev = candidates[i];
            }
        }
    }
}
/*
0 - 6 months
TikTok 3
Facebook 2
Amazon 2
Adobe 2
Bloomberg
2 6 months - 1 year
Uber 2
Google 2
1 year - 2 years
Airbnb 4
Reddit 4
Apple 2
Snapchat
**/
/**
 * Given a collection of candidate numbers (candidates) and a target number (target),
 * find all unique combinations in candidates where the candidate numbers sum to target.
 * Each number in candidates may only be used once in the combination.
 * Note: The solution set must not contain duplicate combinations.
 * Example 1:
 *
 * Input: candidates = [10,1,2,7,6,1,5], target = 8
 * Output:
 * [
 * [1,1,6],
 * [1,2,5],
 * [1,7],
 * [2,6]
 * ]
 * */
