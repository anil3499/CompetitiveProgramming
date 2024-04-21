package org.ap.datastrutures.hashing;

import java.util.*;
/*
Example 1:

Input: s = "cbaebabacd", p = "abc"
Output: [0,6]
Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
Example 2:

Input: s = "abab", p = "ab"
Output: [0,1,2]
Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".
 */
public class FindAllAnagramsInAString {
    public static void findAnagrams(String s, String p) {
        int[] pfq = new int[26];
        int[] sfq = new int[26];
        ArrayList<Integer> ans = new ArrayList<Integer>();
        int i;
        for (i = 0; i < p.length(); i++) {
            char ch = p.charAt(i);
            pfq[ch - 'a']++;
            ch=s.charAt(i);
            sfq[ch - 'a']++;
        }
        if(isAnagrams(pfq, sfq)) {
            ans.add(0);
        }
        sfq[s.charAt(0) - 'a']--;
        int start = 1;
        for (; i < s.length(); i++) {
            char ch = s.charAt(i);
            sfq[ch - 'a']++;
            if(isAnagrams(pfq, sfq)) {
                ans.add(start);
            }
            sfq[s.charAt(start) - 'a']--;
            start++;
        }

        System.out.println(ans.size());
        for(int a : ans){
            System.out.print(a + " ");
        }

    }

    private static boolean isAnagrams(int[] pfq, int[] sfq) {
        for (int i = 0; i < 26; i++) {
            if (pfq[i] != sfq[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        String s = scn.next();
        String p = scn.next();
        findAnagrams(s, p);
    }

}




