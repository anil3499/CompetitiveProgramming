package org.ap.datastrutures.backtracking;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent. Return the answer in any order.
 *
 * A mapping of digits to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.
 *Example 1:
 *
 * Input: digits = "23"
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * Example 2:
 *
 * Input: digits = ""
 * Output: []
 * Example 3:
 *
 * Input: digits = "2"
 * Output: ["a","b","c"]
 *
 * */
public class LetterCombinationsOfAPhoneNumber {
    static List<String> ans = new ArrayList<>();
    public static List<String> getLetterCombinationOfPhoneNumber(String s){
        //create map of character<Integer> to possible charactes
        Map<Character,String> map = new HashMap<>();
        map.put('2',"abc");
        map.put('3',"def");
        map.put('4',"ghi");
        map.put('5',"jkl");
        map.put('6',"mno");
        map.put('7',"pqrs");
        map.put('8',"tuv");
        map.put('9',"wxyz");

        backtarack(s,0,new LinkedList<>(),map);


        return ans;
    }
    //Time compplexity O((4 to power N )* N), n is length of inpput string and 4 is for any key worst case can be 4 chaaracter present like pqrs
    public static void backtarack(String s, int index, LinkedList<Character> temp, Map<Character,String> map){
        if(index > s.length()) {
            String string = temp.stream().map(String::valueOf).collect(Collectors.joining());
            ans.add(string);
            return;
        }
        //getString from Map
        char ch = s.charAt(index);
        String str = map.get(ch);

        for(int i=0; i<str.length();i++){
           temp.addLast(str.charAt(i));
            backtarack(s,index+1,temp,map);
            temp.removeLast();
        }
    }
}
/**
 * Leetcode -https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 * youtube - https://www.youtube.com/watch?v=vgnhZzw-kfU&t=1139s
 * */