package org.ap.datastrutures.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome Return all possible palindrome partitioning of s.
 * Example 1:
 *
 * Input: s = "aab"
 * Output: [["a","a","b"],["aa","b"]]
 * Example 2:
 *
 * Input: s = "a"
 * Output: [["a"]]
 * */
public class PalindromePartitioning {
    static List<List<String>> ans = new ArrayList<>();
    public static List<List<String>>  palindromePartition(String str){
        List<String> temp = new ArrayList<>();
        backtrack(str,0,temp);
        return ans;
    }
    public static boolean isPalindrome(String str, int left, int right) {
        while(left < right) {
            if(str.charAt(left) != str.charAt(right))
                return false;
            left++;
            right--;
        }
        return true;
    }
    public static void backtrack(String str, int index, List<String> temp ) {
        if (index == str.length()){
            ans.add(new ArrayList<>(temp));
            return;
        }
        for(int i=index; i<str.length(); i++) {
            if(isPalindrome(str,index , i)) {
                temp.add(str.substring(index,i+1));
                backtrack(str,i+1, temp);
                temp.remove(temp.size()-1);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(palindromePartition("aab"));
    }
}
/**
 * LeetCode - https://leetcode.com/problems/palindrome-partitioning/
 * Youtube -https://www.youtube.com/watch?v=jHR7KUAAEzw
 * */