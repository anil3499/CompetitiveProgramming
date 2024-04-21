package org.ap.leetcode.Jan2024_71_to_81;

//https://leetcode.com/problems/simplify-path/
public class EditDistance {
    public int minDistance(String word1, String word2) {
        int[][] memo = new int[word1.length()][word2.length()];
        for(int i=0;i<word1.length(); i++)
            for(int j=0; j<word2.length(); j++)
                memo[i][j]=-1;
        return findMinDistance(word1, word2, 0, 0,memo);
    }

    public int findMinDistance(String word1, String word2,int i, int j,int[][] memo) {

        if(j==word2.length()) { //means we need remaining chars of word1 no of delete operations
            return word1.length() -i;
        }

        if(i==word1.length()) { //meansweneedremaining chars of word2 no of instert operations.
            return word2.length()-j;
        }

        if(memo[i][j]!=-1)
            return memo[i][j];

        if(word1.charAt(i)==word2.charAt(j)) {
            memo[i][j] =  findMinDistance(word1, word2, i+1, j+1,memo);
        }else {
            memo[i][j] = Math.min(
                    1+ findMinDistance(word1, word2, i, j+1,memo),  //insert
                    Math.min(
                            1+ findMinDistance(word1, word2, i+1, j+1, memo),  //replace
                            1 + findMinDistance(word1, word2, i+1, j, memo)));  //delete
        }
        return memo[i][j];
    }
}
