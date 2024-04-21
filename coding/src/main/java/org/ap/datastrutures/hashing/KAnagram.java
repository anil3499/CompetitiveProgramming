package org.ap.datastrutures.hashing;
/*
Given two strings of lowercase alphabets and a value k, the task is to find
if two strings are K-anagrams of each other or not.
Two strings are called k-anagrams if following two conditions are true.
Both have same number of characters.
Two strings can become anagram by changing at most k characters in a string.
Input:  str1 = "anagram" , str2 = "grammar" , k = 3
Output:  Yes
Explanation: We can update maximum 3 values and
it can be done in changing only 'r' to 'n'
and 'm' to 'a' in str2.

Input:  str1 = "geeks", str2 = "eggkf", k = 1
Output:  No
Explanation: We can update or modify only 1
value but there is a need of modifying 2 characters.
i.e. g and f in str 2.


K Anagram : where upto k replacement in string will convert to given string anagram
only replace operation is allowed so length sud be same always
 */


import java.util.*;

public class  KAnagram {
    public static boolean areKAnagrams(String str1, String str2, int k) {
        if (str1.length() != str2.length()) {
            return false;
        }
        int[] farr = new int[26];
        for (int i = 0; i < str1.length(); i++) {
            char ch = str1.charAt(i);
            farr[ch - 'a']++;
        }
        for (int i = 0; i < str2.length(); i++) {
            char ch = str2.charAt(i);
            if (farr[ch - 'a'] != 0)
                farr[ch - 'a']--;
        }
        int diff = 0;
        for (int i = 0; i < 26; i++) {
            diff += farr[i];
        }

        if (diff <= k) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        String str1 = s.next();
        String str2 = s.next();
        int k = s.nextInt();
        System.out.println(areKAnagrams(str1, str2, k));

    }

}




