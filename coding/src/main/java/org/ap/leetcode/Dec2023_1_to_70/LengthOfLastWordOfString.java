package org.ap.leetcode.Dec2023_1_to_70;
///https://leetcode.com/problems/length-of-last-word/description/
public class LengthOfLastWordOfString {
    public int lengthOfLastWord(String s) {
        int result=0;
        int i=s.length()-1;
        while(s.charAt(i)==' ')
            i--;
        while(i>=0){
            if(s.charAt(i)!=' ')
                result++;
            else
                break;
            i--;
        }
        return result;
    }
}
