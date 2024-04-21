package org.ap.datastrutures.string;
///https://leetcode.com/problems/regular-expression-matching/description/
public class RegularExpressionMatching {
    public static void main(String[] args) {
        String s = "ab", p = ".*";
        s = "aa"; p = "a*";
        s = "aa"; p = "a";
        s = "aab"; p="c*a*b"; //couldn't handle this case
        System.out.println(matchhStringPattern(s,p));
    }
    public static boolean matchhStringPattern(String s, String p){
        boolean ans = false;
        int i=0,j=0;
        char prevCh =' ';
        char prevPCh=' ';
        while(j<s.length() && i<p.length()){
            System.out.println("str " + s.charAt(j) + " pattern "+ p.charAt(j));
            if(s.charAt(j) == p.charAt(i)){
                i++;j++;
                prevCh = s.charAt(i-1);
                prevPCh = p.charAt(j-1);
            }
            else if(s.charAt(j) == prevCh && p.charAt(i)=='*'){
                j++;
                prevCh = s.charAt(i);
                prevPCh = p.charAt(j-1);
            }
            else if(p.charAt(i)=='.'){
                i++;j++;
                prevCh = s.charAt(i-1);
                prevPCh = p.charAt(j-1);
            }
            else if(p.charAt(j)=='*' && prevPCh=='.') {
                j++;
            } else if(i==0 && s.charAt(j) != p.charAt(i)) {
                prevPCh = p.charAt(j);
                j++;
            } else {
                return false;
            }
        }
        if(j<s.length())
            return false;
        return true;
    }
}


