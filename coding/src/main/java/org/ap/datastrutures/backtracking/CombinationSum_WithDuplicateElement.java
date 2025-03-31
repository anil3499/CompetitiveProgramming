package org.ap.datastrutures.backtracking;

import java.util.*;
//39. Combination Sum
/**
 * Leetcode - https://leetcode.com/problems/combination-sum/
 * YOUTUBE- https://www.youtube.com/watch?v=GBKI9VSKdGg
 * */
public class CombinationSum_WithDuplicateElement {
    //approach1 without for loop
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        //find_combination(candidates,0, target, temp, result);
        find_combination1(candidates,0, target, temp, result);
        return result;
    }
    public void find_combination1(int[] candidates,int idx, int target, List<Integer> temp, List<List<Integer>> result) {
        if(target==0) {
            List<Integer> res = new ArrayList<>(temp);
            result.add(res);
            return;
        }
        if(idx>=candidates.length || target<0) {
            return;
        }
        temp.add(candidates[idx]);
        //we cant pass 0 here otherwise we will get duplicates like [2,2,3] [2,3,2] etc
        //to avoid that we have to pass idx to ensure we have completely considered the 2
        find_combination1(candidates,idx,target-candidates[idx],temp,result);
        temp.remove(temp.size()-1);
        find_combination1(candidates,idx+1,target,temp,result);
    }
    //with for loop
    public void find_combination(int[] candidates,int idx, int target, List<Integer> temp, List<List<Integer>> result) {
        if (target == 0) {
            List<Integer> res = new ArrayList<>(temp);
            result.add(res);
            return;
        }
        for (int i = idx; i < candidates.length; i++) {
            if (target - candidates[i] >= 0) {
                temp.add(candidates[i]);
                find_combination(candidates, i, target - candidates[i], temp, result); // we want to use element twice so use i only
                temp.remove(temp.size() - 1);
            }
        }
    }
}


/**
 * 0 - 6 months
 * Airbnb * 11
 * Google * 7
 * Amazon * 6
 * Microsoft * 6
 * Oracle * 5
 * TikTok * 4
 * Zoho * 3
 * Apple * 2
 * * 6 months - 1 year
 * Facebook * 8
 * Yahoo * 4
 * LinkedIn * 2
 * Salesforce * 2
 * 1 year - 2 years
 * Adobe
 * eBay 4
 * Walmart Labs 3
 * Uber 2
 * Bloomberg 2
 * JPMorgan 2
 * ByteDance 2
 * Reddit 2
 * Snapchat*/

/**
 * Given an array of distinct integers candidates and a target integer target,
 * return a list of all unique combinations of candidates where the chosen numbers sum to target.
 * You may return the combinations in any order.
 * The same number may be chosen from candidates an unlimited number of times. Two combinations are unique if the
 * frequency of at least one of the chosen numbers is different.
 * The test cases are generated such that the number of unique combinations that sum up to target is less than 150 combinations for the given input.
 *
 * Example 1:
 * Input: candidates = [2,3,6,7], target = 7
 * Output: [[2,2,3],[7]]
 * Explanation:
 * 2 and 3 are candidates, and 2 + 2 + 3 = 7. Note that 2 can be used multiple times.
 * 7 is a candidate, and 7 = 7.
 * These are the only two combinations.
 * */