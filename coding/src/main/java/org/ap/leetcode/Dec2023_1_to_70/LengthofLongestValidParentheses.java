package org.ap.leetcode.Dec2023_1_to_70;

import java.util.Stack;

public class LengthofLongestValidParentheses {
    //https://leetcode.com/problems/longest-valid-parentheses/description/
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1); //this we are pushing to handle scenario where string starts with ()
        int result =0;
        for(int i =0;i< s.length(); i++){
            Character ch = s.charAt(i);
            if(ch=='(') {
                stack.push(i);
            }else {
                stack.pop();
                if(stack.isEmpty()) {
                    stack.push(i);
                }else {
                    //since we have already poped from stack so this will help in getting value as 2 for ()
                    int res = i-stack.peek();
                    result = Math.max(result,res);
                }
            }
        }
        return result;
    }
}
