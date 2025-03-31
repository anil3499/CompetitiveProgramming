package org.ap.datastrutures.stack;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

//https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/description/
public class MinimumRemoveToMakeValidParenthesis {
    public String minRemoveToMakeValid(String s) {
        int count = 0;
        Set<Integer> set = isValidString(s);
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < s.length(); i++) {
            if (!set.contains(i)) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

    private Set<Integer> isValidString(String s) {
        Set<Integer> set = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else if(s.charAt(i) ==')') {
                if (stack.isEmpty()) {
                    set.add(i);
                } else {
                    stack.pop();
                }
            }
        }
        while (!stack.isEmpty()) {
            set.add(stack.pop());
        }
        return set;
    }
}
