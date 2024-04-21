package org.ap.leetcode.Dec2023_1_to_70;

import java.util.Arrays;

//https://leetcode.com/problems/longest-common-prefix/
public class LongestCommonPrefix {
    //O(nlongn)
    public String longestCommonPrefix(String[] strs) {
        StringBuilder result =new StringBuilder();
        Arrays.sort(strs);
        String first = strs[0];
        String last = strs[strs.length-1];
        int min=Math.min(first.length(),last.length());
        for(int i =0; i<min;i++) {
            if(first.charAt(i)!=last.charAt(i))
                return result.toString();
            result.append(first.charAt(i));
        }
        return result.toString();
    }

    //O(m*n)
    public String longestCommonPrefix1(String[] strs) {
        String lcp = "";
        int min=Integer.MAX_VALUE;;
        for(int i =0; i<strs.length;i++)
            min = Math.min(min,strs[i].length());
        int j=0;
        while(j<min){
            Character ch = strs[0].charAt(j);
            for (int i=0; i<strs.length; i++){
                if(ch != strs[i].charAt(j))
                    return lcp;
            }
            lcp+=ch;
            j++;
        }
        return lcp;
    }
}
