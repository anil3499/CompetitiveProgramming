package org.ap.leetcode.Dec2023_1_to_70;

import java.util.HashMap;
import java.util.Map;
//https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
public class LengthOfLongestSubstringWithoutRepeatingCharacters {
    public int lengthOfLongestSubstring(String s) {
        int lonestSubStrSize = 0;
        int charCount = 0;
        Map<Character,Integer> map= new HashMap<>();
        for(int i=0,j=0; j< s.length();){
            Character ch = s.charAt(j);
            charCount++;
            map.put(ch, map.getOrDefault(ch,0)+1);
            System.out.println(ch +" " + map.size() +" " + charCount + " "+ (j-i+1));
            if(map.size() == charCount){
                lonestSubStrSize = Math.max(lonestSubStrSize,j-i+1);
                j++;
            }else {
                while(map.size()!= charCount)  {
                    map.put(s.charAt(i),map.get(s.charAt(i))-1);
                    if(map.get(s.charAt(i))==0) {
                        map.remove(s.charAt(i));
                    }
                    charCount--;
                    i++;
                }
                j++;
            }
        }
        return lonestSubStrSize;
    }
}
