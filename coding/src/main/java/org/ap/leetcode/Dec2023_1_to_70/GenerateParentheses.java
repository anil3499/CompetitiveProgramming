package org.ap.leetcode.Dec2023_1_to_70;

import java.util.ArrayList;
import java.util.List;

//https://leetcode.com/problems/generate-parentheses/
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
