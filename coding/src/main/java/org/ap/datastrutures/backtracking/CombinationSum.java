package org.ap.datastrutures.backtracking;

import java.util.*;
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
public class CombinationSum {
    static List<List<Integer>> ans = new ArrayList<>();

    public static List<List<Integer>> combinationSum(int[] A, int sum){
        //sort arrray
        Arrays.sort(A);
        //remove duplicates from array
        HashSet<Integer> set = new LinkedHashSet<>();
        for(int i=0; i< A.length;i++)
            set.add(A[i]);
        //to store temporary ans
        HashSet<Integer> tempAns = new HashSet<>();
        //int arr[] = Arrays.asList(set.stream().toArray()).toArray(new Integer[]{new Integer(0)});
        findCombinations(set ,sum,tempAns);
        return ans;
    }
    //below code will give us duplicate array so refer another method where we are passing int index
    static void findCombinations(HashSet<Integer>  arr, int sum, HashSet<Integer> tempAns){
        if(sum==0){
            ans.add(tempAns.stream().toList());
        }
        for(int item : arr){
            if(sum-item >=0){
                tempAns.add(item);
                findCombinations(arr, sum-item, tempAns);
                tempAns.remove(item);
            }
        }
    }

}

/**
 * Leetcode - https://leetcode.com/problems/combination-sum/
 * YOUTUBE- https://www.youtube.com/watch?v=XluVO8rlRf4
 * */
