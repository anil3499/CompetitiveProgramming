package org.ap.datastrutures.twopointers;

// leetcode 1768
//https://leetcode.com/problems/merge-strings-alternately/description/?envType=study-plan-v2&envId=leetcode-75

public class MergeStringsAlternately {
    public String mergeAlternately(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int i=0, j=0;
        StringBuffer sb = new StringBuffer();
        while(i<m && j<n) {
            sb.append(word1.charAt(i));
            sb.append(word2.charAt(j));
            i++;j++;
        }
        while(i<m) {
            sb.append(word1.charAt(i));
            i++;
        }
        while(j<n) {
            sb.append(word2.charAt(j));
            j++;
        }
        return sb.toString();
    }
}
