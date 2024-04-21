package org.ap.datastrutures.dp.longestcommonsubsequence;
/*
https://www.geeksforgeeks.org/longest-palindromic-subsequence-dp-12/
Input : agbcba
Output : 5

trick:
find LCS of given string S1 and reverse(S1)

 */

public class LongestPalindromicSubsequence_6 {


    int lpss(String s1, String s2, int ls1, int ls2) {
        int t[][]=new int[ls1+1][ls2+1];
        for(int i=0 ; i < ls1+1 ; i++) {
            for (int j = 0; j < ls2+1; j++) {
                if (i == 0 || j == 0) {
                    t[i][j]= 0;
                }
            }
        }
        for(int i=1 ; i < ls1+1 ; i++) {
            for (int j = 1; j < ls2 + 1; j++) {
                if(s1.charAt(i-1) == s2.charAt(j-1)) {
                    t[i][j]= 1 + t[i - 1][j - 1];
                }
                else{
                    t[i][j]= Integer.max(t[i-1][j] ,t[i][j - 1]);
                }
            }
        }
        return t[ls1][ls2];

    }

    public int findLPSLength(String st) {
        return findLPSLengthRecursive(st, 0, st.length()-1);
    }

    private int findLPSLengthRecursive(String st, int startIndex, int endIndex) {
        if(startIndex > endIndex)
            return 0;

        // every sequence with one element is a palindrome of length 1
        if(startIndex == endIndex)
            return 1;

        // case 1: elements at the beginning and the end are the same
        if(st.charAt(startIndex) == st.charAt(endIndex))
            return 2 + findLPSLengthRecursive(st, startIndex+1, endIndex-1);

        // case 2: skip one element either from the beginning or the end
        int c1 =  findLPSLengthRecursive(st, startIndex+1, endIndex);
        int c2 =  findLPSLengthRecursive(st, startIndex, endIndex-1);
        return Math.max(c1, c2);
    }

    public static void main(String[] args) {
        LongestPalindromicSubsequence_6 longestPalindromicSubsequence = new LongestPalindromicSubsequence_6();
        String s1 = "agbcba";
        String s2 = new StringBuilder(s1).reverse().toString();
        System.out.println("==>" + longestPalindromicSubsequence.lpss(s1, s2, s1.length(),s2.length()));

    }

}
