package org.ap.datastrutures.string;

import java.util.HashSet;
import java.util.Set;

/*

https://www.techiedelight.com/find-interleavings-of-given-strings/

Given two strings str1 and str2, write a function that prints all interleavings of the given two strings.
You may assume that all characters in both strings are different

Example:

Input: str1 = "AB",  str2 = "CD"
Output:
    ABCD
    ACBD
    ACDB
    CABD
    CADB
    CDAB

Input: str1 = "AB",  str2 = "C"
Output:
    ABC
    ACB
    CAB
 */
public class FindAllInterleavingsOfGivenTwoStrings {

    public static void interleavings(String curr, String X, String Y, Set<String> result)
    {

        if (X.length() == 0 && Y.length() == 0)
        {
            result.add(curr);
            return;
        }
        // if String X is not empty, append its first character in the
        // result and recur for remaining substring
        if (X.length() > 0) {
            interleavings(curr + X.charAt(0), X.substring(1), Y, result);
        }

        // if String Y is not empty, append its first character in the
        // result and recur for remaining substring
        if (Y.length() > 0) {
            interleavings(curr + Y.charAt(0), X, Y.substring(1), result);
        }
    }

    public static void main(String[] args)
    {
        String X = "ABC";
        String Y = "ACB";
        Set<String> result = new HashSet<>();
        interleavings("", X, Y, result);

        result.stream().forEach(System.out::println);
    }
}
