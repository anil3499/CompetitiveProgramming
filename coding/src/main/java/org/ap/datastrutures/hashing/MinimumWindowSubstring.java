package org.ap.datastrutures.hashing;

import java.util.HashMap;

/*
sheet code : LB92
https://www.geeksforgeeks.org/find-the-smallest-window-in-a-string-containing-all-characters-of-another-string/
smallest-substring-of-a-string-containing-all-characters-of-another-string
Input: string = “this is a test string”, pattern = “tist”
Output: Minimum window is “t stri”
Explanation: “t stri” contains all the characters of pattern.
Input: string = “geeksforgeeks”, pattern = “ork”
Output: Minimum window is “ksfor”

1 : copy target substring in hashmap with ele and value as count
2: keep acquiring  ele and count until all found
3: keep release ele from start position and decrease
 */
public class MinimumWindowSubstring {

    public static String findMinimumWindowSubstring(String s1, String s2){
        String answer=s1;
        HashMap<Character, Integer> map1=new HashMap<>();
        HashMap<Character, Integer> map2=new HashMap<>();
        for(Character ch : s2.toCharArray()){
            map2.put(ch,map2.getOrDefault(ch,0)+1);
        }
        int i=-1, j=-1;
        int matchCount=0;
        int desiredMatchCount=s2.length();

        while(true) {
            boolean flag1=false;
            boolean flag2=false;
            //acquire
            while (j < s1.length()-1 && matchCount < desiredMatchCount) {
                j++;
                Character ch = s1.charAt(j);
                map1.put(ch, map1.getOrDefault(ch, 0) + 1);
                if (map1.getOrDefault(ch, 0) <= map2.getOrDefault(ch, 0)) {
                    matchCount++;
                }
                flag1=true;
            }
            //release
            while (i < j && matchCount == desiredMatchCount) {
                String possibleAnswer = s1.substring(i + 1, j + 1);
                if (answer.length() > possibleAnswer.length()) {
                    answer = possibleAnswer;
                }
                i++;
                Character ch = s1.charAt(i);
                if (map1.get(ch) == 1) {
                    map1.remove(ch);
                } else {
                    map1.put(ch, map1.getOrDefault(ch, 0) - 1);
                }
                if (map1.getOrDefault(ch, 0) < map2.getOrDefault(ch, 0)) {
                    matchCount--;
                }
                flag2=true;
            }
            if(!flag1 && !flag2){
                break;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        String s1="geeksforgeeks";
        String s2="ork";
        System.out.println(findMinimumWindowSubstring(s1,s2));
    }
}
