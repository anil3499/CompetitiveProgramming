package org.ap.datastrutures.stack;

import java.util.Stack;

public class ValidParentheses {
    //https://leetcode.com/problems/valid-parentheses/description/
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        int i =0;
        while(i<s.length()) {
            Character ch = s.charAt(i);
            if(ch == '(' || ch=='[' || ch=='{') {
                stack.push(ch);
                i++;
            }else {
                if(stack.isEmpty()) return false;
                Character stkCh = stack.pop();
                if(
                        (ch==')' && stkCh=='(') ||
                                (ch==']' && stkCh=='[') ||
                                (ch=='}' && stkCh=='{')
                )
                    i++;
                else return false;
            }
        }
        if(!stack.isEmpty()) return false;
        return true;

    }
}
