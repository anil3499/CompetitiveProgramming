package org.ap.datastrutures.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
public class FactorCombinations {
    static List<List<Integer>> ans = new ArrayList<>();
    public static List<List<Integer>> findFactorCombinations(int n){
        if(n<1)
            return ans;

        LinkedList<Integer> temp = new LinkedList<>();
        backtrack(n,temp,1, 2);
        return ans;

    }
    public static void backtrack(int n, LinkedList<Integer> temp, int product, int index){
        if(product > n)
            return;

        if(product ==n) {
            ans.add(new ArrayList<>(temp));
            //temp = new ArrayList<>();
            return;
        }
        for(int i = index; i<= n/product ; i++) { // n is target multiplication and product is current multiplication
            // this condition says for n=12 i should not be 12 because we dont want to provide same number also n should be divisible by i
            if(n%i ==0 && i != n) {
                temp.addLast(i);
                backtrack(n, temp, product * i, i);
                temp.removeLast();
            }
        }

    }

    public static void main(String[] args) {
        System.out.println(findFactorCombinations(12));
    }
}
/**
 * leetcode- https://leetcode.com/problems/factor-combinations/
 * https://leetcode.ca/all/254.html
 * */