package org.ap.datastrutures.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
/**
 * leetcode - 254 premium
 * leetcode- https://leetcode.com/problems/factor-combinations/
 * https://leetcode.ca/all/254.html
 * */

public class FactorCombinations {
    static List<List<Integer>> ans = new ArrayList<>();
    public static List<List<Integer>> findFactorCombinations(int n){
        if(n<=1)
            return ans;
        List<Integer> temp = new ArrayList<>();
        backtrack(n,temp,1, 2);
        return ans;
    }
    public static void backtrack(int n, List<Integer> temp, int product, int index){
        if(product ==n) {
            ans.add(new ArrayList<>(temp));
            //temp = new ArrayList<>();
            return;
        }
        // n is target multiplication and product is current multiplication
        for(int i = index; i<= n/product ; i++) {
            // this condition says for n=12 i should not be 12 because we dont want to provide same number
            // also n should be divisible by i for ex 12 / 2 or 3 or 4 or 6
            //also next product should be <= n
            if(product*i<=n && n%i ==0 && i != n) {
                temp.add(i);
                backtrack(n, temp, product * i, i);
                temp.remove(temp.size()-1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(findFactorCombinations(12));
    }
}


/**
 * 1 year - 2 years
 * LinkedIn 6
 * TikTok 3
 * Uber
 * */

/**
 * Numbers can be regarded as product of its factors. For example,
 * 8 = 2 x 2 x 2;
 *   = 2 x 4.
 *Write a function that takes an integer n and return all possible combinations of its factors.
 *
 * Note:
 *
 * You may assume that n is always positive.
 * Factors should be greater than 1 and less than n.
 * Input: n = 1
 * Output: []
 *
 * Input: n = 37
 * Output: []
 *
 * Input: 12
 * Output:
 * [
 *   [2, 6],
 *   [2, 2, 3],
 *   [3, 4]
 * ]
 * */