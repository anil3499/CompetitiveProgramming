package org.ap.datastrutures.string;
//https://leetcode.com/problems/valid-word-abbreviation/description/

/**
 * A string can be abbreviated by replacing any number of non-adjacent, non-empty substrings with their lengths. The lengths should not have leading zeros.
 * <p>
 * For example, a string such as "substitution" could be abbreviated as (but not limited to):
 * <p>
 * "s10n" ("s ubstitutio n")
 * "sub4u4" ("sub stit u tion")
 * "12" ("substitution")
 * "su3i1u2on" ("su bst i t u ti on")
 * "substitution" (no substrings replaced)
 * The following are not valid abbreviations:
 * <p>
 * "s55n" ("s ubsti tutio n", the replaced substrings are adjacent)
 * "s010n" (has leading zeros)
 * "s0ubstitution" (replaces an empty substring)
 * Given a string word and an abbreviation abbr, return whether the string matches the given abbreviation.
 * <p>
 * A substring is a contiguous non-empty sequence of characters within a string.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: word = "internationalization", abbr = "i12iz4n"
 * Output: true
 * Explanation: The word "internationalization" can be abbreviated as "i12iz4n" ("i nternational iz atio n").
 */

//word = "internationalization", abbr = "i12iz4n"
public class ValidWordAbberviation {
    public static boolean validWordAbbreviation(String word, String abbr) {
        int i = 0, j = 0;
        while (true) {
            if (i < word.length() && j < abbr.length() && word.charAt(i) == abbr.charAt(j)) {
                i++;
                j++;
                continue;
            } else if (j < abbr.length() && Character.isDigit(abbr.charAt(j))) {
                StringBuilder digitS = new StringBuilder("");
                while (j < abbr.length() && Character.isDigit(abbr.charAt(j))) {
                    digitS.append(abbr.charAt(j));
                    j++;
                }
                if (digitS.charAt(0) == '0') return false; //if leading zero return false;
                Integer digit = Integer.parseInt(digitS.toString());
                i+=digit;
                if (i > word.length()) return false; // if i breaches the limit return false
            } else if (i == word.length() && j == abbr.length()) { // if both reached at the end return true
                return true;
            } else { // if i and j does not finishes the string return false
                return false;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(validWordAbbreviation("internationalization", "i5a11o1"));
    }
}
