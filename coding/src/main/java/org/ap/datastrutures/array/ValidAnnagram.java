package org.ap.datastrutures.array;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
 * typically using all the original letters exactly once.
 * Example 1:
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 * Example 2:
 * Input: s = "rat", t = "car"
 * Output: false
 * */
public class ValidAnnagram {
    public static boolean isStringsAreValidAnnagramStrings(String s1, String s2){
        Map<Character,Integer> map = new HashMap<>();
        for(Character ch :s1.toCharArray())
            map.put(ch,map.getOrDefault(ch,0)+1);
        for(Character ch: s2.toCharArray()) {
            if(map.containsKey(ch) ==false)
                return false;
            map.put(ch,map.get(ch)-1);
            if(map.get(ch)==0)
                map.remove(ch);

        }
        if(map.size()!=0)
            return false;
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isStringsAreValidAnnagramStrings("anagram","nagaram"));
    }
}
/*
* leetcode - https://leetcode.com/problems/valid-anagram/
 * */