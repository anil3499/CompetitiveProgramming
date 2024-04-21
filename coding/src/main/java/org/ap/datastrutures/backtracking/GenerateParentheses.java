package org.ap.datastrutures.backtracking;

/*
Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
For example, given n = 3, a solution set is:
[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
*/

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {

    public static void backtrack(List<String> ans, String cur, int open, int close, int max) {
        if (cur.length() == max * 2) { //string op length will me n*2
            ans.add(cur);
            return;
        }

        if (open < max)
            backtrack(ans, cur+"(", open+1, close, max);
        if (close < open)
            backtrack(ans, cur+")", open, close+1, max);
    }

    public static List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList();
        backtrack(ans, "", 0, 0, n);
        return ans;
    }


    public static void main(String[] args) {
        generateParenthesis(3).stream().forEach(System.out::println);
    }
}

/**
 * Leetcode - https://leetcode.com/problems/generate-parentheses/
 * Youtube-  https://www.youtube.com/watch?v=eyCj_u3PoJE
 **/