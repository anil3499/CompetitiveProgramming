package org.ap.datastrutures.twopointers;

//https://leetcode.com/problems/is-subsequence/description
//Leetcode 392
public class IsSubsequence {
    public boolean isSubsequence(String s, String t) {
        int i=0, j=0;
        while(i<s.length() && j<t.length()) {
            if(s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }
        if(s.length()==i) {
            return true;
        }
        return false;
    }
}
