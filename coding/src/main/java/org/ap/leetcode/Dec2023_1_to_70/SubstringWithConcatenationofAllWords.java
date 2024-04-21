package org.ap.leetcode.Dec2023_1_to_70;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//https://leetcode.com/problems/substring-with-concatenation-of-all-words/description/
public class SubstringWithConcatenationofAllWords {
    public List<Integer> findSubstring(String s, String[] words) {
        int count= words.length;
        List<Integer> result = new ArrayList<>();
        Map<String,Integer> map=new HashMap<>();
        for(String word: words)
            map.put(word,map.getOrDefault(word,0)+1);
        int n= s.length();
        String substr = "";
        for(int i=0,j=0;j<n ;) {
            //substr+=s.charAt(j);
            if(j-i+1 < words[0].length() * count) {
                j++;
            }else {
                //System.out.println(j);
                Map<String,Integer> mapValidation = new HashMap<>();
                //window become equal to number of chars in array of words
                for(int k=i,l=i+words[0].length()-1 ; l<=j;){

                    String key = s.substring(k,l+1);
                    mapValidation.put(key,mapValidation.getOrDefault(key,0)+1);
                    k=l+1;
                    //k+=words[0].length();
                    l+=words[0].length();
                }
                if(map.equals(mapValidation)) {
                    result.add(i);
                }
                i++;
                j++;
            }
        }
        return result;
    }
}
