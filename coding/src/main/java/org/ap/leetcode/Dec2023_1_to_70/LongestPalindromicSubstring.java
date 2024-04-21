package org.ap.leetcode.Dec2023_1_to_70;
//https://leetcode.com/problems/longest-palindromic-substring/
public class LongestPalindromicSubstring {
    //https://www.youtube.com/watch?v=XYQecbcd6_c
    public static void main(String[] args) {
        //System.out.println(longestPalindrome2("babad"));
    }
    public String longestPalindrome2(String s) {
        int n= s.length();
        int maxLen = 0;
        String result = "";
        for(int i=0;i<n;i++) {
            //for odd length palindrome
            int left =i; //to moveleft of i
            int right = i; //to moveright of i
            while(left >= 0 && right < n && s.charAt(left)==s.charAt(right)) {
                int len = right - left +1;
                if(maxLen <len) {
                    maxLen = len;
                    result= s.substring(left,right+1);
                }
                left--;
                right++;
            }

            //for even length c
            //copy abovee code as is just start left from i and right from i+1
            left = i;
            right = i+1;
            while(left>=0 && right <n && s.charAt(left)==s.charAt(right)) {
                int len = right - left +1;
                if(maxLen<len){
                    maxLen = len;
                    result = s.substring(left,right+1);
                }
                left--;
                right++;
            }
        }
        return result;
    }
    public String longestPalindrome1(String s) {
        int maxLen = Integer.MIN_VALUE;
        String subs = "";
        for(int i=0;i<s.length(); i++) {
            for(int j=i; j<s.length();j++) {
                if(isPalindrome(s,i,j)) {
                    int len = j-i+1;
                    if(maxLen < len) {
                        maxLen = len;
                        subs = s.substring(i,j+1);
                    }
                }
            }
        }
        return subs;
    }
    public boolean isPalindrome(String s,int i, int j){
        while(i<j) {
            if(s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;j--;
        }
        return true;
    }
}
