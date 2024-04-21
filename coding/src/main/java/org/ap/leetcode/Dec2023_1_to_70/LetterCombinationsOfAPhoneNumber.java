package org.ap.leetcode.Dec2023_1_to_70;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//https://leetcode.com/problems/letter-combinations-of-a-phone-number/
public class LetterCombinationsOfAPhoneNumber {
    public List<String> letterCombinations(String digits) {
        Map<Character,String> map=new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");

        List<String> result= new ArrayList<>();

        letterCombinations(digits, 0, map, result, "");

        return result;
    }
    public void letterCombinations(String digits, int index,  Map<Character,String> map, List<String> result,String op) {
        if(index >= digits.length()) {
            if(op!="")
                result.add(op);
            return;
        }
        Character ch = digits.charAt(index);
        index++;
        String word = map.get(ch);
        for(int i=0; i<word.length();i++) {
            op+=word.charAt(i);
            letterCombinations(digits, index, map, result,op);
            op =op.substring(0,op.length()-1);
        }
    }
}
