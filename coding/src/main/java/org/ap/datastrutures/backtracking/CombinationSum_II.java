package org.ap.datastrutures.backtracking;

import java.util.*;

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
public class CombinationSum_II {
    static List<List<Integer>> ans = new ArrayList<>();
    //FOR WITHOUT DUPLICATIONS WE HAVE TO REMOVE DUPLICATES FROM input array
    //to avoid result like {1,2} and {2,1}
    static void findCombinations_WithoutDuplicates(int[] arr, int target, Set<Integer> tempAns, int index) {
        if (index == arr.length) return;
        if (target == 0) {
            ans.add(tempAns.stream().toList());
        }
        if (index == arr.length) return;
        for (int i = index; i < arr.length; i++) {
            if (target - arr[i] >= 0) {
                tempAns.add(arr[i]);
                findCombinations_WithoutDuplicates(arr, target - arr[i], tempAns, i);
                i++;
                tempAns.remove(arr[i]);
            }

        }
    }
    public static List<List<Integer>> combinationSumWithoutDuplicates(int[] A, int sum) {
        //sort arrray
        Arrays.sort(A);
        //remove duplicates from array
        HashSet<Integer> set = new LinkedHashSet<>();
        for(int i=0; i< A.length;i++)
            set.add(A[i]);
        //to store temporary ans
        HashSet<Integer> tempAns = new HashSet<>();
        int arr[] = set.stream().mapToInt(Integer::intValue).toArray();
        findCombinations_WithoutDuplicates(arr, sum, tempAns,0);
        return ans;
    }
}
/**
 * Leetcode -https://leetcode.com/problems/combination-sum-ii/
 * */