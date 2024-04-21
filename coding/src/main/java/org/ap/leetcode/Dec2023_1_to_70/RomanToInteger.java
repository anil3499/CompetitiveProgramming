package org.ap.leetcode.Dec2023_1_to_70;

import java.util.HashMap;
import java.util.Map;

//https://leetcode.com/problems/roman-to-integer/
public class RomanToInteger {
    public int romanToInt(String s) {
        Map<String,Integer> map= new HashMap<>();
        map.put("M",1000);
        map.put("CM",900);
        map.put("D",500);
        map.put("CD",400);
        map.put("C",100);
        map.put("XC",90);
        map.put("L",50);
        map.put("XL",40);
        map.put("X",10);
        map.put("IX",9);
        map.put("V",5);
        map.put("IV",4);
        map.put("I",1);
        int result = 0;
        int i =0;
        while(i< s.length()) {
            if(i+1<s.length()){
                String key2Chars = "" + s.charAt(i) + s.charAt(i+1);
                if(map.containsKey(key2Chars)){
                    result+=map.get(key2Chars);
                    i+=2;
                } else {
                    String key1Char = "" + s.charAt(i);
                    result+=map.get(key1Char);
                    i++;
                }
            } else {
                String key1Char = "" + s.charAt(i);
                result+=map.get(key1Char);
                i++;
            }
        }
        return result;
    }
}
