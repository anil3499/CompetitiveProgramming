package org.ap.datastrutures.slidingwindow.fixedsize;
//https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/description/
//1456
public class MaximumNumberOfVowelsInASubstringOfGivenLength {
    public int maxVowels(String s, int k) {
        int countOfVowels = 0, maxVowelsInSubstring = 0;
        for(int i=0, j=0; j<s.length();) {
            if(isVowel(s.charAt(j))) {
                countOfVowels++;
            }

            if(j - i + 1 < k) {
                j++;
            } else {
                //calculate
                maxVowelsInSubstring = Math.max(maxVowelsInSubstring, countOfVowels);
                //move window
                if(isVowel(s.charAt(i))) {
                    countOfVowels--;
                }
                i++;
                //increment window
                j++;
            }
        }
        return maxVowelsInSubstring;
    }
    private boolean isVowel(char ch) {
        return ch=='a' || ch=='e' || ch=='i' || ch=='o' || ch=='u';
    }
}
