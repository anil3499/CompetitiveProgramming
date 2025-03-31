package org.ap.datastrutures.twopointers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ReverseWordsInString {
    public String reverseWords1(String s) {
        String[] word = s.split(" ");
        StringBuffer sb = new StringBuffer();
        for(int i =word.length-1; i>=0; i--) {
            if (!word[i].trim().isEmpty()) {
                sb.append(word[i]);
                if (i != 0) sb.append(" ");
            }
        }
        return sb.toString().trim();
    }
    public String reverseWords(String s) {
        List<String> words = Arrays.asList(s.split("\\s+"));
        Collections.reverse(words);
        return String.join(" ", words).trim();
    }
}
