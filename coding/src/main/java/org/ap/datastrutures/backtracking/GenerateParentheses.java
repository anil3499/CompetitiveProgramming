package org.ap.datastrutures.backtracking;

import java.util.ArrayList;
import java.util.List;
/**
 * leetcode -22
 * Leetcode - https://leetcode.com/problems/generate-parentheses/
 * Youtube-  https://www.youtube.com/watch?v=eyCj_u3PoJE
 **/

public class GenerateParentheses {
    public List<String> generateParenthesis(int n) {
        int open= n;
        int close= n;
        open--;
        String op = "(";
        List<String> result = new ArrayList<>();
        generateParenthesesHelper(open, close,  op, result) ;
        return result;
    }
    public void generateParenthesesHelper(int open, int close, String op, List<String> result) {
        if(open==0 && close ==0) {
            result .add(op);
            return;
        }
        if(open!=0) { // we can choose open until it becomes zero
            String op1 = op + "(";
            generateParenthesesHelper(open-1, close,  op1, result) ;
        }
        if(close > open) { // we can choose close only if its greater than open
            String op2 =op + ")";
            generateParenthesesHelper(open, close-1,  op2, result) ;
        }
    }
}

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

/**
 * 0 - 6 months
 * Amazon * 16
 * Adobe * 5
 * Google * 4
 * Bloomberg * 3
 * Uber * 3
 * Facebook * 3
 * ServiceNow * 3
 * Oracle * 3
 * Microsoft * 2
 * Apple * 2
 * Yandex * 2
 * 6 months - 1 year
 * Yahoo * 5
 * TikTok * 5
 * DE Shaw * 2
 * Zoho * 2
 * JPMorgan * 2
 * Lucid * 2
 * 1 year - 2 years
 * turing * 5
 * Lyft * 4
 * Visa * 3
 * Intuit * 2
 * Walmart Labs * 2
 * Salesforce * 2
 * ByteDance * 2
 * Infosys * 2
 * Tesla * 2
 * TripAdvisor * 2
 * Zenefits * */