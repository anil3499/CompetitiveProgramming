package org.ap.leetcode.Dec2023_1_to_70;
//https://leetcode.com/problems/find-the-index-of-the-first-occurrence-in-a-string/
public class FindIndexOfFirstOccurrenceInString {
    public int strStr(String s1, String s2) {
        if(s1.length() <s2.length()) return-1;
        int j=0;
        for(int i=0; i<s1.length(); i++){
            if(doesMatch(s1,i, s2)) {
                return i;
            }
        }
        return -1;
    }
    public boolean doesMatch(String s1, int i, String s2){
        int j= 0;
        while(j<s2.length()) {
            if(i>=s1.length() || s1.charAt(i)!=s2.charAt(j)) {
                return false;
            }
            i++; j++;
        }
        return true;
    }
}
